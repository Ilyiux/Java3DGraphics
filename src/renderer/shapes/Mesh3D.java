package renderer.shapes;

import renderer.utils.Face;
import renderer.utils.Point3D;
import renderer.utils.PointConverter;
import renderer.utils.Vertice;

import java.awt.*;
import java.util.ArrayList;

public class Mesh3D extends Shape3D {
    public Vertice[] vertices;
    public Face[] faces;
    private ArrayList<Point3D[]> lines = new ArrayList<>();

    public Mesh3D(Vertice[] vertices, Face[] faces) {
        this.vertices = new Vertice[vertices.length];
        System.arraycopy(vertices, 0, this.vertices, 0, vertices.length);
        this.faces = new Face[faces.length];
        System.arraycopy(faces, 0, this.faces, 0, faces.length);

        constructLines();
    }

    private void constructLines() {
        for (Face face : faces) {
            for (int i = 0; i < face.vertInds.length; i ++) {
                int si = i + 1;
                if (si == face.vertInds.length) {
                    si = 0;
                }

                Point3D[] coordPair = new Point3D[2];
                coordPair[0] = vertices[face.vertInds[i] - 1].position;
                coordPair[1] = vertices[face.vertInds[si] - 1].position;

                boolean contains = false;
                for (Point3D[] pair : lines) {
                    if ((pair[0] == coordPair[0] && pair[1] == coordPair[1]) || (pair[0] == coordPair[1] && pair[1] == coordPair[0])) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) lines.add(coordPair);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);

        for (Point3D[] pair : lines) {
            if (!PointConverter.pointOnSide(pair[0]) || !PointConverter.pointOnSide(pair[1])) continue;

            Point p1 = PointConverter.convertPointTo2D(pair[0]);
            Point p2 = PointConverter.convertPointTo2D(pair[1]);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public static Mesh3D shiftMesh3D(Mesh3D mesh, double offsetX, double offsetY, double offsetZ) {
        Vertice[] newVerts = new Vertice[mesh.vertices.length];
        for (int i = 0; i < mesh.vertices.length; i ++) {
            newVerts[i] = new Vertice(mesh.vertices[i].id, new Point3D(mesh.vertices[i].position.x + offsetX, mesh.vertices[i].position.y + offsetY, mesh.vertices[i].position.z + offsetZ));
        }
        return new Mesh3D(newVerts, mesh.faces);
    }
}
