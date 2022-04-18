package renderer.shapes;

import renderer.utils.Point3D;
import renderer.utils.PointConverter;

import java.awt.*;

public class Polygon3D extends Shape3D {
    private Point3D[] points;
    private Color color;

    public Polygon3D(Color color, Point3D[] points) {
        this.color = color;
        this.points = new Point3D[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    public void render(Graphics g) {
        Polygon polygon2d = new Polygon();
        for (int i = 0; i < points.length; i ++) {
            Point point = PointConverter.convertPointTo2D(points[i]);
            polygon2d.addPoint(point.x, point.y);
        }
        g.setColor(color);
        g.fillPolygon(polygon2d);

        g.dispose();
    }
}
