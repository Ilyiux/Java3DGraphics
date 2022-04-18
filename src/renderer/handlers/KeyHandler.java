package renderer.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean spacePressed = false;
    public boolean shiftPressed = false;
    public boolean wPressed = false;
    public boolean aPressed = false;
    public boolean sPressed = false;
    public boolean dPressed = false;

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) spacePressed = true;
        if (keyCode == KeyEvent.VK_SHIFT) shiftPressed = true;
        if (keyCode == KeyEvent.VK_W) wPressed = true;
        if (keyCode == KeyEvent.VK_A) aPressed = true;
        if (keyCode == KeyEvent.VK_S) sPressed = true;
        if (keyCode == KeyEvent.VK_D) dPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) spacePressed = false;
        if (keyCode == KeyEvent.VK_SHIFT) shiftPressed = false;
        if (keyCode == KeyEvent.VK_W) wPressed = false;
        if (keyCode == KeyEvent.VK_A) aPressed = false;
        if (keyCode == KeyEvent.VK_S) sPressed = false;
        if (keyCode == KeyEvent.VK_D) dPressed = false;
    }
}
