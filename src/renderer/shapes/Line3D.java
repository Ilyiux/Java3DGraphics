package renderer.shapes;

import renderer.utils.PointConverter;
import renderer.utils.Point3D;

import java.awt.*;

public class Line3D extends Shape3D {
    public Point3D start;
    public Point3D stop;
    private Color color;

    public Line3D(Point3D start, Point3D stop, Color color) {
        this.start = start;
        this.stop = stop;
        this.color = color;
    }

    public void render(Graphics g) {

        if (!PointConverter.pointOnSide(start) || !PointConverter.pointOnSide(stop)) return;

        g.setColor(color);
        Point p1 = PointConverter.convertPointTo2D(start);
        Point p2 = PointConverter.convertPointTo2D(stop);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public static Line3D shiftLine3D(Line3D line, double offsetX, double offsetY, double offsetZ) {
        return new Line3D(new Point3D(line.start.x + offsetX, line.start.y + offsetY, line.start.z + offsetZ), new Point3D(line.stop.x + offsetX, line.stop.y + offsetY, line.stop.z + offsetZ), line.color);
    }
}
