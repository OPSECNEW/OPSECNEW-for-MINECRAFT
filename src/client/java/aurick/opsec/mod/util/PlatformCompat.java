package aurick.opsec.mod.util;

import net.minecraft.client.Minecraft;

/**
 * Platform/input helpers whose underlying API changed in MC 26.3 (GLFW -> SDL3).
 *
 * Up to MC 26.2 this reads GLFW directly (unchanged from the original code).
 * MC 26.3 replaced GLFW with SDL3, so on 26.3+ it reads Minecraft's own
 * MouseHandler state instead, which also handles HiDPI scaling correctly.
 */
public final class PlatformCompat {

    private PlatformCompat() {}

    /** Whether the left mouse button is currently held down. */
    public static boolean isLeftDown() {
        //? if >=26.3 {
        /*return Minecraft.getInstance().mouseHandler.isLeftPressed();*/
        //?} else {
        long window = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
        return org.lwjgl.glfw.GLFW.glfwGetMouseButton(window, org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT) == org.lwjgl.glfw.GLFW.GLFW_PRESS;
        //?}
    }

    /** Cursor position in GUI-scaled coordinates: {x, y}. */
    public static double[] guiCursorPos() {
        Minecraft mc = Minecraft.getInstance();
        //? if >=26.3 {
        /*return new double[] {
                mc.mouseHandler.getScaledXPos(mc.getWindow()),
                mc.mouseHandler.getScaledYPos(mc.getWindow())
        };*/
        //?} else {
        long window = org.lwjgl.glfw.GLFW.glfwGetCurrentContext();
        double[] mx = new double[1], my = new double[1];
        org.lwjgl.glfw.GLFW.glfwGetCursorPos(window, mx, my);
        double scale = mc.getWindow().getGuiScale();
        return new double[] { mx[0] / scale, my[0] / scale };
        //?}
    }

    /** Opens a URL in the system browser. */
    public static void openUri(String url) {
        //? if >=26.3 {
        /*com.mojang.blaze3d.Blaze3D.openUri(java.net.URI.create(url));*/
        //?} elif >=1.21.11 {
        /*net.minecraft.util.Util.getPlatform().openUri(url);*/
        //?} else {
        net.minecraft.Util.getPlatform().openUri(url);
        //?}
    }
}
