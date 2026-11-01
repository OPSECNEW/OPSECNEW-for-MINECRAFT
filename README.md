<p align="center">
<img src="https://github.com/user-attachments/assets/9adba640-2570-4c22-9355-4f13aa5d4507" alt="OpSec logo" width="15%"/>
</p>
<h1 align="center">OpSec New for Minecraft</h1>

<p align="center"><b>🇬🇧 English</b> · <a href="README.de.md">🇩🇪 Deutsch</a></p>

<p align="center">A client-side Minecraft mod that protects you against client fingerprinting, mod detection, tracking exploits and more — continued as a community fork.</p>

<p align="center">
<img alt="Minecraft" src="https://img.shields.io/badge/Minecraft-1.20%20%E2%80%93%2026.3-3C8527">
<img alt="Loader" src="https://img.shields.io/badge/Loader-Fabric-DBD0B4">
<img alt="Version" src="https://img.shields.io/badge/Version-1.1.9--fork-6e5494">
<img alt="License" src="https://img.shields.io/badge/License-GPL--3.0-blue">
</p>

## About this fork

The original **OpSec** by Aurick ([aurickk/OpSec](https://github.com/aurickk/OpSec)) was archived by its author on 10 August 2026. Its last release, v1.1.7.1, supports Minecraft 1.20 – 26.2.

**I'm keeping this project alive.** This fork continues OpSec under the same GPL-3.0 license and brings it to the latest Minecraft versions — starting with **Minecraft 26.3**. Please give it a try, and report anything that doesn't work so it can be fixed.

- ✅ Supports **Minecraft 26.3** (plus every version the original supported)
- ✅ **Works alongside [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer)** — both mods share one whitelist and never cover the same protection twice, see [ExploitPreventer compatibility](#exploitpreventer-compatibility)
- ✅ All original features, plus fixes for the internals Minecraft 26.3 changed
- ✅ Every release is built automatically by GitHub Actions — the newest jars are always on the [Releases](../../releases) page

All credit for the original mod goes to Aurick. Builds from this fork are community builds, **not** official releases of the original project.

> [!WARNING]
> This is a passion project built and maintained with **AI**.

> [!IMPORTANT]
> Fake websites and Discord servers have been distributing **trojanized** builds of OpSec. Only download this fork from the [**Releases**](../../releases) page of this repository. The original (up to MC 26.2) is available on [Modrinth](https://modrinth.com/mod/opsec) and [GitHub](https://github.com/aurickk/OpSec/releases). Builds from anywhere else may be malicious.

## What it does

- **[Spoof as Vanilla](#spoof-as-vanilla)** — Report a vanilla client brand and block all mod detection
- **[Channel Spoofing](#channel-spoofing)** — Conditionally hide mod network channels from the server
- **[Known-Pack Filtering](#known-pack-filtering)** — Strip mod pack identifiers from the configuration handshake
- **[Isolate Pack Cache](#isolate-pack-cache)** — Keep a separate resource pack cache per account to prevent tracking
- **[Block Local URLs](#block-local-urls)** — Block resource pack URLs that point into your local network
- **[Bypass Server Pack Requirement](#bypass-server-pack-requirement)** — Toggle required server resource packs like normal packs
- **[Strip Mod Shader Overrides](#strip-mod-shader-overrides)** — Remove server pack shaders that target your mods
- **[Key Resolution Protection](#key-resolution-protection)** — Stop servers from detecting mods through translation keys and keybinds
- **[Meteor Fix](#meteor-fix)** — Disable Meteor Client's flawed key protection
- **[Mod Whitelist](#mod-whitelist)** — Automatically or manually exempt mods that need the server
- **[Chat Signing Control](#chat-signing-control)** — Decide whether your chat messages are signed
- **[Account Manager](#account-manager)** — Switch between Minecraft accounts without restarting
- **[Telemetry Blocking](#telemetry-blocking)** — Stop data collection sent to Mojang

> Interested in servers or plugins that use tracking exploits? Have a look at the [Hall of Shame](https://github.com/NikOverflow/ExploitPreventer/blob/master/HALL_OF_SHAME.md).

## Requirements

- **Minecraft** 1.20 – 26.3
- **Fabric Loader** 0.16.0+ (0.18.5+ for MC 26.1.x, 0.19.3+ for MC 26.3)
- **Fabric API** matching your Minecraft version
- **Java** 17 / 21 / 25 depending on the Minecraft version (the official launcher handles this for you)

### Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version.
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for your Minecraft version.
3. Download the matching `opsec-<minecraft_version>+v<version>.jar` from the [Releases](../../releases) page.
4. Put both files into your `.minecraft/mods` folder (remove any older OpSec jar first).
5. Launch Minecraft.

## Configuration

Open the settings with the **OpSec** button at the top of the multiplayer server list, or through [Mod Menu](https://modrinth.com/mod/modmenu).

<img width="1465" height="820" alt="OpSec settings screen" src="https://github.com/user-attachments/assets/c69a768b-60ac-4f78-9705-184f6c4e4495" />

If you change settings while connected to a server, reconnect to make sure they take effect.

#### Protection tab

| Setting | Description |
|---------|-------------|
| **Spoof as Vanilla** | Enable/disable [Spoof as Vanilla](#spoof-as-vanilla) |
| **Isolate Pack Cache** | Enable/disable [cache isolation](#isolate-pack-cache) |
| **Block Local Pack URLs** | Enable/disable [local URL blocking](#block-local-urls) |
| **Bypass Server Pack Requirement** | [Server pack bypass](#bypass-server-pack-requirement) behaviour:<br/>• **MANUAL** (default): vanilla behaviour when a pack is pushed; you can still toggle any server pack<br/>• **ASK**: the pack is not applied and a consent screen asks whether to load it<br/>• **ALWAYS ON**: server packs are not applied by default; you can still toggle them on |
| **Strip Mod Shader Overrides** | Enable/disable [shader override stripping](#strip-mod-shader-overrides) |
| **Clear Cache** | Delete all cached server resource packs |
| **Key Resolution Spoofing** | Enable/disable [key resolution protection](#key-resolution-protection) |
| **Fake Default Keybinds** | Report vanilla default keybinds instead of your real bindings |
| **Meteor Fix** | Disable Meteor Client's broken key protection (only shown when Meteor is installed) |
| **Chat Signing** | [Chat signing](#chat-signing-control): **ON** (vanilla behaviour) or **OFF** (maximum privacy) |
| **Disable Telemetry** | Enable/disable [telemetry blocking](#telemetry-blocking) |

#### Whitelist tab

| Setting | Description |
|---------|-------------|
| **Whitelist Mode** | • **AUTO** (default): mods with network channels are whitelisted automatically<br/>• **CUSTOM**: choose the whitelisted mods yourself<br/>• **BLOCK ALL**: set automatically while *Spoof as Vanilla* is on |
| **Installed Mods** | Turn individual mods ON/OFF to exempt them from protection (CUSTOM mode only) |

#### Miscellaneous tab

| Setting | Description |
|---------|-------------|
| **Show Alerts** | Show a chat message when tracking is detected |
| **Show Toasts** | Show pop-up notifications for important events |
| **Log Detections** | Write every detection to the game log for transparency |
| **Debug Alerts** | Show alerts for every probed key, even unchanged ones |
| **Debug Command** | Enable the `/opsec` debug command (off by default) |

#### Accounts tab

| Setting | Description |
|---------|-------------|
| **Saved Accounts** | Your added accounts with login/logout and remove buttons |
| **Refresh All** | Re-check all account tokens (invalid tokens are shown in red) |
| **Add Session Token** | Add an account using a session (access) token |
| **Import** | Import accounts from a JSON file |
| **Export** | Export accounts to a JSON file |

### Debug commands

The `/opsec` command is **off by default** (enable it under Misc → Debug Command).

| Command | Description |
|---------|-------------|
| `/opsec` | Show available commands |
| `/opsec info` | Overview of all tracked mods |
| `/opsec info <mod>` | Details for one mod (translation keys, keybinds, channels, known packs, shaders) |
| `/opsec channels` | All tracked network channels with their whitelist status |

### Understanding alerts

- **Key Resolution Exploit Detected** — the server is probing your keys
- **Resource Pack Fingerprinting Detected** — a suspicious resource pack URL was detected
- **Local URL Scan Detected** — a resource pack is targeting a local/private address

## Feature details

### Spoof as Vanilla

Servers can query your client brand to see whether you are running a modded client. OpSec provides true vanilla spoofing by blocking all mod key resolution, network channels and known-pack identifiers while keeping the vanilla ones.

- **ON** — appear as an unmodified Minecraft client
- **OFF** — appear as a normal Fabric client (default)

It is off by default so that the mod whitelist can automatically allow mods that need network channels.

---

### Isolate Pack Cache

Based on [LiquidBounce](https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/util/MixinDownloadQueue.java).

Required server resource packs can be used to recognise the same computer across different accounts ([write-up](https://alaggydev.github.io/posts/cytooxien/)). Instead of one shared cache (`~/.minecraft/downloads/`), OpSec keeps a separate cache folder for every account UUID.

---

### Block Local URLs

Derived from [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) by [NikOverflow](https://github.com/NikOverflow).

Malicious servers can send resource pack URLs that redirect into your local network to probe for devices and services ([write-up](https://alaggydev.github.io/posts/cytooxien/)). OpSec checks whether a request or redirect targets a local address and blocks it — unless the server you are playing on is itself local.

---

### Bypass Server Pack Requirement

Servers can force resource packs on you. A vanilla client cannot decline or turn off a required pack, and only *pretending* to accept it can be detected by probing the pack's translation keys.

Minecraft still accepts and downloads the pack as usual, but OpSec lets you switch its textures off on your side. The pack's language file is always kept, because servers can probe its translation keys (e.g. `{"translate": "some.pack.key"}`) to check whether the pack is really loaded — and a vanilla client with the pack would resolve them.

With OpSec, server packs show up as normal entries in the resource pack menu, so you can switch between stripped and fully loaded at any time.

**Modes:**
- **MANUAL** (default): required packs load fully, like vanilla. You can still unequip a server pack in the pack menu to strip it (the language file stays loaded).
- **ASK**: required packs are stripped when pushed, and an overlay asks `[Continue]` / `[Load Pack For Real]`.
- **ALWAYS ON**: all server packs are stripped when pushed, without an overlay. You can still switch them back on.

---

### Strip Mod Shader Overrides

Some mods (e.g. [Meteor Client](https://github.com/MeteorDevelopment/meteor-client)) draw their GUI with their own shaders loaded through Minecraft's resource manager. A forced server pack can override those shaders to blank the mod's GUI, crash the client with broken shaders or overload the GPU — and doing so also reveals that the mod is installed.

OpSec removes shader overrides under `assets/<mod>/shaders/` from server packs for every installed mod that isn't whitelisted, so the mod's own shaders are used. The rest of the pack still loads, so this works even when a server makes the pack impossible to bypass. Vanilla (`minecraft`) shaders are never touched, and whitelisting a mod lets the server's override through.

---

### Key Resolution Protection

Servers can send translatable text containing keys such as `key.attack` or `key.hide_icons` in any packet to find out which keys you have bound or which mod texts your client knows. This can reveal your installed mods ([background](https://wurst.wiki/sign_translation_vulnerability)).

OpSec tracks when translation keys are resolved while a server packet is processed and blocks them depending on your settings:

- **Spoof as Vanilla ON** — blocks all mod keys and reports vanilla keybinds with their default values
- **Spoof as Vanilla OFF** — allows Fabric API and whitelisted mod keys, blocks everything else

If **Fake Default Keybinds** is off, vanilla keybinds resolve to their real values.

**Examples**

Mod keybinds (the raw key / fallback is returned instead of the keybind):
```
[key.meteor-client.open-commands] '.'→'key.meteor-client.open-commands'
[key.meteor-client.open-gui] 'Right Shift'→'key.meteor-client.open-gui'
```

Vanilla keybinds with **Fake Default Keybinds** on (defaults are returned):
```
[key.hotbar.6] 'Q'→'6'
[key.hotbar.7] 'E'→'7'
[key.hotbar.8] 'R'→'8'
```

---

### Meteor Fix

Older Meteor Client versions ship their own key protection, which makes them reliably detectable: a server can send a probe with a fallback value. A vanilla client answers with the fallback, but Meteor echoes the raw key.

What Meteor answers:
```
'key.meteor-client.open-gui' 'Right Shift'→'key.meteor-client.open-gui'
```

<img width="847" height="107" alt="Meteor response" src="https://github.com/user-attachments/assets/e157ae3f-6beb-4823-aca0-9c61573264e2" />

What a vanilla client would answer:
```
'key.meteor-client.open-gui' '⟦FALLBACK⟧'→'⟦FALLBACK⟧'
```

OpSec disables Meteor's `AbstractSignEditScreenMixin`, so OpSec's own protection takes over and answers with the fallback, exactly like vanilla.

<img width="901" height="107" alt="OpSec response" src="https://github.com/user-attachments/assets/506b9c73-6747-40f8-9a56-52c0353034b4" />

---

### ExploitPreventer compatibility

OpSec and [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) (EP, tested with 1.2.1) work together. Each protection is handled by exactly one mod, and both use the same mod whitelist, so a server sees one consistent client.

**Handled by ExploitPreventer** (greyed out in OpSec while EP is installed; your OpSec settings are kept):
- [Key Resolution Protection](#key-resolution-protection) for translation keys and mod keybinds
- [Block Local URLs](#block-local-urls)
- [Isolate Pack Cache](#isolate-pack-cache)

**Still handled by OpSec** (EP does not provide these):
- [Spoof as Vanilla](#spoof-as-vanilla) (vanilla brand), [Channel Spoofing](#channel-spoofing) and [Known-Pack Filtering](#known-pack-filtering)
- [Bypass Server Pack Requirement](#bypass-server-pack-requirement) and [Strip Mod Shader Overrides](#strip-mod-shader-overrides)
- **Fake Default Keybinds** — EP answers vanilla keybinds with your real binding; OpSec still reports the defaults
- [Mod Whitelist](#mod-whitelist), chat signing, account manager, telemetry blocking, alerts and the [Meteor Fix](#meteor-fix)

**How they stay in sync:** OpSec passes its mod whitelist to EP through EP's official API. A mod whose channels OpSec shows is allowed in EP, every other mod is blocked — so channels, known packs and translation keys always tell the same story (in vanilla mode everything is blocked). OpSec also tells EP that the language keys of server packs stripped by OpSec belong to the server pack, so they resolve exactly like on a vanilla client. EP's own settings file is never changed.

---

### Channel Spoofing

Servers can read the network channels your mods register to find out which mods you use. OpSec can hide mod channels from the server. What is hidden is controlled by [Spoof as Vanilla](#spoof-as-vanilla) and the [Mod Whitelist](#mod-whitelist).

---

### Known-Pack Filtering

Some mods add their own pack identifiers, which servers can probe to detect a modded client or specific mods. OpSec intercepts the outgoing `ServerboundSelectKnownPacks` response and removes entries from non-whitelisted mods; vanilla and whitelisted packs still pass.

- **Spoof as Vanilla ON** — removes all mod packs
- **Spoof as Vanilla OFF** — keeps whitelisted mod packs, removes the rest

> [!NOTE]
> Only active where Fabric's known-packs hook exists (MC 1.21.11+ with a recent Fabric API).

---

### Mod Whitelist

Some mods need to talk to the server to work (e.g. voice chat, Xaero's Minimap quick travel). The whitelist exempts them from channel spoofing, key resolution protection, known-pack filtering and shader stripping.

<img width="853" height="478" alt="Whitelist settings" src="https://github.com/user-attachments/assets/6ae423de-dd98-47c1-a617-f6df747c9293" />

**Modes:**
- **AUTO** (default): mods that register network channels are whitelisted automatically, as they are the most likely to need the server.
- **CUSTOM**: pick the mods yourself from the list of all installed mods.
- **BLOCK ALL**: everything is blocked. This is set automatically while [Spoof as Vanilla](#spoof-as-vanilla) is on, because a vanilla brand must not expose any mods.

---

### Chat Signing Control

Based on [No Chat Reports](https://modrinth.com/mod/no-chat-reports).

By default every chat message carries a cryptographic signature that ties it to your Minecraft client and therefore your Microsoft account.

- **ON** (default): vanilla behaviour, every message is signed.
- **OFF**: signatures are removed for maximum privacy. On servers that enforce secure chat you won't be able to chat.

---

### Account Manager

Based on [Meteor Client](https://github.com/MeteorDevelopment/meteor-client).

Add your Minecraft accounts and switch between them without restarting the game.

- **Session token login** — add accounts with access tokens
- **Refresh token** — get a new session token for expired accounts
- **Offline account** — add a username-only account without authentication
- **Switching** — click an account to log in, click again to return to your original account
- **Validation** — refresh to check whether tokens are still valid (expired ones are shown in red)
- **Import/Export** — back up and restore your accounts as JSON

> [!NOTE]
> Session tokens expire after a while. Use **Refresh** to check them.

---

### Telemetry Blocking

From [No Chat Reports](https://modrinth.com/mod/no-chat-reports).

Minecraft sends telemetry to Mojang, such as game events, performance metrics, client configuration and usage statistics. With telemetry blocking on, OpSec stops this. Gameplay is not affected.

## Fork changes

### v1.1.9-fork

**ExploitPreventer compatibility (tested with EP 1.2.1 on 26.3):**
- Fixed: with both mods installed, the language keys of server resource packs did not resolve (EP counted keys from OpSec-stripped packs as "OpSec's keys" and blocked them). Servers could detect this; the keys now resolve like on a vanilla client.
- Fixed: OpSec switched off *Spoof as Vanilla*, channel spoofing, known-pack filtering, *Bypass Server Pack Requirement* and the whitelist whenever EP was installed, although EP does not provide any of them. They now stay active.
- Fixed: with EP, your real vanilla keybinds were visible to servers. *Fake Default Keybinds* now keeps working.
- New: OpSec pushes its mod whitelist into EP via EP's API, so both mods expose exactly the same mods.
- Removed the `conflicts: exploitpreventer` entry from the mod metadata.

**Merged from [OpSec Community Edition](https://github.com/tufkan1/OpSec) by tufkan1 (reviewed, adapted):**
- Every mod's `assets/` and `data/` namespaces are mapped to that mod, so channels with namespaces like `plasmo` are attributed to the right mod (shared namespaces such as `c` or `fabric` are skipped; no fuzzy name matching).
- Language files are found in all namespaces of a mod, not only `assets/<modid>/`.
- Account manager: tokens up to 8192 characters, and a pasted launcher JSON is unpacked automatically.
- GitHub Actions: every push builds all versions; a version tag publishes a release with all jars (without the Modrinth/CurseForge upload).
- Not merged: fuzzy namespace matching (could attribute short namespaces to the wrong mod) and the TinyFD build dependency (TinyFD does not exist at runtime on 26.3; this fork uses SDL3 dialogs instead).

**Testing:** headless 26.3 client against a local vanilla 26.3 server, with and without ExploitPreventer: all mixin targets of both mods apply (48 with EP, 31 without), and joining, server packs (MANUAL / ALWAYS ON), chat with signing OFF, vanilla brand, and probes for vanilla, mod, keybind (rebound key) and server-pack keys behave as expected.

### v1.1.8-fork

Minecraft 26.3 replaced several internals that OpSec relies on. Older Minecraft versions are still compiled exactly as before (via Stonecutter conditions).

- **New build target `26.3`** (Fabric API `0.165.0+26.3`, Mod Menu `21.0.0`, Java 25).
- **authlib 10**: `com.mojang.authlib.yggdrasil` became `com.mojang.authlib.services`. Telemetry blocking now targets `MinecraftServicesUserApiService`; the account manager creates its services through `MinecraftServicesDiscoveryService`, like vanilla does.
- **GLFW → SDL3**: 26.3 no longer ships GLFW or LWJGL TinyFD.
  - Account import/export uses SDL3's native file dialogs on 26.3 (`util/FileDialogs`).
  - Mouse polling and opening links go through `util/PlatformCompat` (Minecraft's `MouseHandler` / `Blaze3D.openUri` on 26.3).
- **Server resource packs**: `Pack.ResourcesSupplier` now uses `openMetadata`/`openResources`; the pack-strip wrapper was adapted so *Bypass Server Pack Requirement* keeps working.
- **Chat signing**: `ServerboundChatPacket` now stores an `Optional<MessageSignature>`; *Chat Signing: OFF* was adapted (it would otherwise fail to load).
- **Vanilla translation keys**: on 26.3, `VanillaPackResources` no longer implements `PackResources` (vanilla content now comes from `FixedPathPackResources` layers). Without this fix, OpSec silently stopped recognising vanilla keys and blocked them in server messages.
- **Update checker**: only reports a *newer* upstream release, so the fork is not asked to "update" back to 1.1.7.1.

**Testing:** on 26.3, in a headless client against a local vanilla 26.3 server, all 31 mixin targets apply, and joining, server resource packs (MANUAL and ALWAYS ON), chat with signing OFF, vanilla brand spoofing and key resolution probes (vanilla, mod, keybind and server-pack keys) behave as expected.

## Building from source

### Prerequisites

- **Java 25** (it can compile every target); otherwise Java 17 (1.20.1 – 1.20.4), Java 21 (1.20.6 – 1.21.11), Java 25 (26.1+)
- **Gradle** (included via the wrapper)

### Build

1. **Clone this repository** (green **Code** button → copy the URL)
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. **Build all versions**
   ```bash
   # Windows
   .\gradlew.bat buildAll

   # Linux/Mac
   ./gradlew buildAll
   ```

3. **Build a single version**
   ```bash
   ./gradlew :26.3:build
   ```
   Available targets: `1.20.1`, `1.20.2`, `1.20.4`, `1.20.6`, `1.21.1`, `1.21.4`, `1.21.6`, `1.21.9`, `1.21.11`, `26.1`, `26.2`, `26.3`

The jars end up in `versions/<target>/build/libs/`:

| Target | Supports |
|--------|----------|
| 1.20.1 | 1.20 – 1.20.1 |
| 1.20.2 | 1.20.2 |
| 1.20.4 | 1.20.3 – 1.20.4 |
| 1.20.6 | 1.20.5 – 1.20.6 |
| 1.21.1 | 1.21 – 1.21.1 |
| 1.21.4 | 1.21.2 – 1.21.5 |
| 1.21.6 | 1.21.6 – 1.21.8 |
| 1.21.9 | 1.21.9 – 1.21.10 |
| 1.21.11 | 1.21.11 |
| 26.1 | 26.1 – 26.1.2 |
| 26.2 | 26.2 |
| 26.3 | 26.3 |

## Credits & references

- **[Aurick](https://github.com/aurickk/OpSec)** — creator of the original OpSec
- [tufkan1 / OpSec Community Edition](https://github.com/tufkan1/OpSec) — namespace scan, language file scan, account token input and CI workflows (adapted)
- [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) — local URL blocking and key resolution protection
- [LiquidBounce](https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/util/MixinDownloadQueue.java) — isolated server resource pack cache
- [Meteor Client](https://github.com/MeteorDevelopment/meteor-client) — session token login
- [No Chat Reports](https://modrinth.com/mod/no-chat-reports) — chat signing control and telemetry blocking
- [No Prying Eyes](https://github.com/Daxanius/NoPryingEyes) — secure chat enforcement detection
- [MixinSquared](https://github.com/Bawnorton/MixinSquared) — mixin cancellation for the Meteor Fix
- [Stonecutter](https://stonecutter.kikugie.dev/) — multi-version build system
- [Fabric API](https://github.com/FabricMC/fabric-api) — Fabric translation and keybind keys

## License

GPL-3.0 — see [LICENSE](LICENSE). This fork is a modified version of the original OpSec; the changes are listed under [Fork changes](#fork-changes).

## Disclaimer

OpSec is a privacy tool that protects players from unwanted client fingerprinting and tracking. It is not intended for bypassing server rules, evading bans or gaining unfair advantages. You are responsible for following the rules and terms of service of every server you join.
