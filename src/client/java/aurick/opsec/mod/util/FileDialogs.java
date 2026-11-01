package aurick.opsec.mod.util;

import aurick.opsec.mod.Opsec;
import net.minecraft.client.Minecraft;

import java.nio.file.Path;
import java.util.function.Consumer;

//? if >=26.3 {
/*import org.lwjgl.sdl.SDLDialog;
import org.lwjgl.sdl.SDLError;
import org.lwjgl.sdl.SDL_DialogFileCallback;
import org.lwjgl.sdl.SDL_DialogFileFilter;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;*/
//?} else {
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
//?}

/**
 * Native "open file" / "save file" dialogs for JSON files.
 *
 * Up to MC 26.2 the game ships LWJGL TinyFD, which is used directly.
 * MC 26.3 replaced GLFW with SDL3 and dropped TinyFD entirely, so on 26.3+
 * the dialogs go through SDL3's built-in file dialog API instead.
 *
 * In both implementations {@code onPicked} runs on a background thread and is
 * only called when the user actually picked a file (not on cancel).
 */
public final class FileDialogs {

    private static final String FILTER_DESCRIPTION = "JSON Files (*.json)";

    private FileDialogs() {}

    public static void openJson(String title, Consumer<Path> onPicked) {
        show(false, title, null, onPicked);
    }

    public static void saveJson(String title, String defaultFileName, Consumer<Path> onPicked) {
        show(true, title, defaultFileName, onPicked);
    }

    //? if >=26.3 {
    /*private static final Object LOCK = new Object();
    // SDL may call back on another thread and needs the filter list (and our callback
    // closure) to stay alive until then. Only one dialog may be open at a time; the
    // previous dialog's native memory is released when the next one is opened, which
    // guarantees we never free the callback closure while it is still executing.
    private static boolean inFlight = false;
    private static Runnable releasePrevious = null;

    private static void show(boolean save, String title, String defaultFileName, Consumer<Path> onPicked) {
        Minecraft mc = Minecraft.getInstance();
        // SDL dialogs must be opened from the main (window) thread.
        mc.execute(() -> {
            synchronized (LOCK) {
                if (inFlight) {
                    Opsec.LOGGER.info("[OpSec] A file dialog is already open");
                    return;
                }
                if (releasePrevious != null) {
                    releasePrevious.run();
                    releasePrevious = null;
                }
                inFlight = true;
            }

            List<ByteBuffer> strings = new ArrayList<>();
            ByteBuffer filterName = MemoryUtil.memUTF8(FILTER_DESCRIPTION);
            ByteBuffer filterPattern = MemoryUtil.memUTF8("json");
            strings.add(filterName);
            strings.add(filterPattern);

            SDL_DialogFileFilter.Buffer filters = SDL_DialogFileFilter.calloc(1);
            filters.get(0).name(filterName).pattern(filterPattern);

            ByteBuffer location = null;
            if (defaultFileName != null) {
                String defaultPath = mc.gameDirectory.toPath().resolve(defaultFileName).toAbsolutePath().toString();
                location = MemoryUtil.memUTF8(defaultPath);
                strings.add(location);
            }

            SDL_DialogFileCallback[] callbackHolder = new SDL_DialogFileCallback[1];
            Runnable release = () -> {
                if (callbackHolder[0] != null) callbackHolder[0].free();
                filters.free();
                strings.forEach(MemoryUtil::memFree);
            };

            callbackHolder[0] = SDL_DialogFileCallback.create((userdata, fileList, filter) -> {
                String picked = null;
                try {
                    if (fileList == MemoryUtil.NULL) {
                        Opsec.LOGGER.error("[OpSec] {} dialog failed: {}", title, SDLError.SDL_GetError());
                    } else {
                        long first = MemoryUtil.memGetAddress(fileList);
                        if (first != MemoryUtil.NULL) {
                            picked = MemoryUtil.memUTF8(first);
                        }
                    }
                } catch (Throwable t) {
                    Opsec.LOGGER.error("[OpSec] {} dialog callback failed: {}", title, t.getMessage());
                } finally {
                    synchronized (LOCK) {
                        inFlight = false;
                        releasePrevious = release;
                    }
                }
                if (picked != null) {
                    Path path = Path.of(picked);
                    new Thread(() -> onPicked.accept(path), "OpSec-FileDialog").start();
                }
            });

            try {
                long window = mc.getWindow().handle();
                if (save) {
                    SDLDialog.SDL_ShowSaveFileDialog(callbackHolder[0], MemoryUtil.NULL, window, filters, location);
                } else {
                    SDLDialog.SDL_ShowOpenFileDialog(callbackHolder[0], MemoryUtil.NULL, window, filters, location, false);
                }
            } catch (Throwable t) {
                Opsec.LOGGER.error("[OpSec] Failed to open {} dialog: {}", title, t.getMessage());
                synchronized (LOCK) {
                    inFlight = false;
                    releasePrevious = release;
                }
            }
        });
    }*/
    //?} else {
    private static void show(boolean save, String title, String defaultFileName, Consumer<Path> onPicked) {
        new Thread(() -> {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                PointerBuffer filters = stack.mallocPointer(1);
                filters.put(stack.UTF8("*.json"));
                filters.flip();

                String result = save
                        ? TinyFileDialogs.tinyfd_saveFileDialog(title, defaultFileName, filters, FILTER_DESCRIPTION)
                        : TinyFileDialogs.tinyfd_openFileDialog(title, "", filters, FILTER_DESCRIPTION, false);

                if (result != null) {
                    onPicked.accept(Path.of(result));
                }
            } catch (Throwable t) {
                Opsec.LOGGER.error("[OpSec] {} dialog failed: {}", title, t.getMessage());
            }
        }, save ? "OpSec-Export-Thread" : "OpSec-Import-Thread").start();
    }
    //?}
}
