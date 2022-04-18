package renderer.handlers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public double horizontalMotion, verticalMotion;

    public void updateMousePositions() {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        horizontalMotion = mouseLocation.x - (sSize.width / 2);
        verticalMotion = mouseLocation.y - (sSize.height / 2);
    }

    public void resetMouse() {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            Robot r = new Robot();
            r.mouseMove(sSize.width / 2, sSize.height / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
