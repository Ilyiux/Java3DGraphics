package renderer;

import renderer.utils.Point3D;

public class Camera {
    public Point3D position;
    public double rx, ry, rz;

    public Camera(Point3D position, double rx, double ry, double rz) {
        this.position = position;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
    }
}
