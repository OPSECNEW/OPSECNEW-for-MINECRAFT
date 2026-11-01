package aurick.opsec.mod.config;

import aurick.opsec.mod.Opsec;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Async update checker that queries the GitHub releases API to detect new versions.
 * All state fields are volatile for cross-thread visibility since the check runs
 * on a background thread and results are read on the render thread.
 */
public final class UpdateChecker {

    private static final String RELEASES_URL = "https://api.github.com/repos/aurickk/OpSec/releases/latest";
    private static final String FALLBACK_RELEASE_URL = "https://github.com/aurickk/OpSec/releases/latest";

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private static volatile String latestVersion = null;
    private static volatile String releaseUrl = null;
    private static volatile boolean updateAvailable = false;
    private static volatile boolean checkComplete = false;
    private static volatile boolean shownThisSession = false;

    private UpdateChecker() {
        // Utility class
    }

    /**
     * Fires an async HTTP GET to the GitHub releases API to check for updates.
     * Non-blocking: runs on a daemon thread via CompletableFuture.
     */
    public static void checkForUpdate() {
        CompletableFuture.runAsync(() -> {
            try {
                String currentVersion = Opsec.getVersion();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(RELEASES_URL))
                        .header("User-Agent", "OpSec-Mod/" + currentVersion)
                        .header("Accept", "application/vnd.github.v3+json")
                        .timeout(Duration.ofSeconds(10))
                        .GET()
                        .build();

                HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

                    String tagName = json.has("tag_name") ? json.get("tag_name").getAsString() : null;
                    String htmlUrl = json.has("html_url") ? json.get("html_url").getAsString() : null;

                    if (tagName != null) {
                        // Strip leading "v" or "V" if present (e.g., "V1.0.5" -> "1.0.5")
                        String version = (tagName.startsWith("v") || tagName.startsWith("V")) ? tagName.substring(1) : tagName;
                        latestVersion = version;
                        releaseUrl = htmlUrl != null ? htmlUrl : FALLBACK_RELEASE_URL;

                        // Only a strictly newer upstream release counts as an update. The
                        // community fork runs ahead of the archived upstream (last: 1.1.7.1),
                        // so a plain "differs" check would push users back to an older
                        // release without support for the newest Minecraft version.
                        if (isNewer(version, currentVersion)) {
                            updateAvailable = true;
                            Opsec.LOGGER.info("[OpSec] Update available: {} -> {} ({})", currentVersion, version, releaseUrl);
                        } else {
                            Opsec.LOGGER.debug("[OpSec] Mod is up to date ({})", currentVersion);
                        }
                    }
                } else {
                    Opsec.LOGGER.debug("[OpSec] GitHub API returned status {}", response.statusCode());
                }
            } catch (Exception e) {
                Opsec.LOGGER.debug("[OpSec] Update check failed: {}", e.getMessage());
            } finally {
                checkComplete = true;
            }
        });
    }

    /**
     * Returns true if an update is available, the check is complete, the
     * update screen has not been shown this session, and the user hasn't
     * skipped this specific version.
     */
    public static boolean isUpdateAvailable() {
        return checkComplete && updateAvailable && !shownThisSession
                && !OpsecConfig.getInstance().getSettings().isVersionSkipped(latestVersion);
    }

    /**
     * Returns the latest version string from GitHub, or null if not yet checked.
     */
    public static String getLatestVersion() {
        return latestVersion;
    }

    /**
     * Returns the URL to the latest release page on GitHub.
     * Falls back to the generic latest release URL if not available.
     */
    public static String getReleaseUrl() {
        return releaseUrl != null ? releaseUrl : FALLBACK_RELEASE_URL;
    }

    /**
     * Marks the update notification as shown for this session.
     * Prevents the screen from appearing again even if user navigates back to title screen.
     */
    public static void markShown() {
        shownThisSession = true;
    }

    /**
     * Resets the session-shown flag so the update screen can appear again.
     * Called when the user resets all settings from the config screen.
     */
    public static void resetShown() {
        shownThisSession = false;
    }

    /**
     * Returns the current mod version. Delegates to {@link Opsec#getVersion()}.
     */
    public static String getCurrentVersion() {
        return Opsec.getVersion();
    }

    /** True when the latest known release is strictly newer than the running version. */
    public static boolean isOutdated() {
        return latestVersion != null && isNewer(latestVersion, getCurrentVersion());
    }

    /**
     * Numeric comparison of the dotted version core ("1.1.7.1", "1.1.8-fork", "V1.2"):
     * any pre-release/build suffix after '-' or '+' is ignored, missing parts count as 0.
     */
    static boolean isNewer(String candidate, String current) {
        int[] a = versionCore(candidate);
        int[] b = versionCore(current);
        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int x = i < a.length ? a[i] : 0;
            int y = i < b.length ? b[i] : 0;
            if (x != y) return x > y;
        }
        return false;
    }

    private static int[] versionCore(String version) {
        if (version == null) return new int[0];
        String v = version.trim();
        if (v.startsWith("v") || v.startsWith("V")) v = v.substring(1);
        int cut = v.length();
        for (char sep : new char[] {'-', '+'}) {
            int idx = v.indexOf(sep);
            if (idx >= 0) cut = Math.min(cut, idx);
        }
        String[] parts = v.substring(0, cut).split("\\.");
        int[] out = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try { out[i] = Integer.parseInt(parts[i]); } catch (NumberFormatException e) { out[i] = 0; }
        }
        return out;
    }
}
