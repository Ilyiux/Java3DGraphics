package renderer.utils;

import renderer.GraphicsPanel;

import java.awt.*;

public class PointConverter {
    private static final double e = (GraphicsPanel.WIDTH + GraphicsPanel.HEIGHT) / 4.0;

    // converts a point in 3d space to a 2d point on the screen
    // based on the camera's position and rotation
    public static Point convertPointTo2D(Point3D p3d) {
        double X = p3d.x - GraphicsPanel.camera.position.x;
        double Y = p3d.y - GraphicsPanel.camera.position.y;
        double Z = p3d.z - GraphicsPanel.camera.position.z;

        double dx = Math.cos(GraphicsPanel.camera.ry) * (Math.sin(GraphicsPanel.camera.rz) * Y + Math.cos(GraphicsPanel.camera.rz) * X) - Math.sin(GraphicsPanel.camera.ry) * Z;
        double dy = Math.sin(GraphicsPanel.camera.rx) * (Math.cos(GraphicsPanel.camera.ry) * Z + Math.sin(GraphicsPanel.camera.ry) * (Math.sin(GraphicsPanel.camera.rz) * Y + Math.cos(GraphicsPanel.camera.rz) * X)) + Math.cos(GraphicsPanel.camera.rx) * (Math.cos(GraphicsPanel.camera.rz) * Y - (Math.sin(GraphicsPanel.camera.rz) * X));
        double dz = Math.cos(GraphicsPanel.camera.rx) * (Math.cos(GraphicsPanel.camera.ry) * Z + Math.sin(GraphicsPanel.camera.ry) * (Math.sin(GraphicsPanel.camera.rz) * Y + Math.cos(GraphicsPanel.camera.rz) * X)) - Math.sin(GraphicsPanel.camera.rx) * (Math.cos(GraphicsPanel.camera.rz) * Y - (Math.sin(GraphicsPanel.camera.rz) * X));

        int bx = (int)((e / dy) * dx + e);
        int by = (int)((e / dy) * dz + e);

        return new Point(bx, by);
    }

    // returns true if the point is visible based on the camera's position and rotation
    public static boolean pointOnSide(Point3D p3d) {
        double vecX = -(-Math.cos(GraphicsPanel.camera.rz) * Math.sin(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx) - Math.sin(GraphicsPanel.camera.rz) * Math.cos(GraphicsPanel.camera.rx));
        double vecY = -Math.sin(GraphicsPanel.camera.rz) * Math.sin(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx) + Math.cos(GraphicsPanel.camera.rz) * Math.cos(GraphicsPanel.camera.rx);
        double vecZ = Math.cos(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx);

        double px = vecX + GraphicsPanel.camera.position.x;
        double py = vecY + GraphicsPanel.camera.position.y;
        double pz = vecZ + GraphicsPanel.camera.position.z;

        double a = vecX;
        double b = vecY;
        double c = vecZ;
        double d = vecX * px + vecY * py + vecZ * pz;

        double ps = a * p3d.x + b * p3d.y + c * p3d.z - d;
        double cs = a * GraphicsPanel.camera.position.x + b * GraphicsPanel.camera.position.y + c * GraphicsPanel.camera.position.z - d;

        return (ps / cs < 0);
    }

    // returns a vector pointing in the direction the camera is facing
    public static Point3D lookVec() {
        double vecX = -(-Math.cos(GraphicsPanel.camera.rz) * Math.sin(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx) - Math.sin(GraphicsPanel.camera.rz) * Math.cos(GraphicsPanel.camera.rx));
        double vecY = -Math.sin(GraphicsPanel.camera.rz) * Math.sin(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx) + Math.cos(GraphicsPanel.camera.rz) * Math.cos(GraphicsPanel.camera.rx);
        double vecZ = Math.cos(GraphicsPanel.camera.ry) * Math.sin(GraphicsPanel.camera.rx);

        return new Point3D(vecX, vecY, vecZ);
    }
}
