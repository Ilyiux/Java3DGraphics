package reader;

import renderer.utils.Face;
import renderer.utils.Point3D;
import renderer.utils.Vertice;
import renderer.shapes.Mesh3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OBJReader {
    public static Mesh3D readOBJ(File file) {
        ArrayList<String> v = new ArrayList<>();
        ArrayList<String> f = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("v ")) v.add(data.substring(2));
                if (data.startsWith("f ")) f.add(data.substring(2));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

        Vertice[] verts = new Vertice[v.size()];
        Face[] faces = new Face[f.size()];

        for (int i = 0; i < verts.length; i ++) {
            String[] s = v.get(i).split(" ");
            verts[i] = new Vertice(i, new Point3D(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2])));
        }

        for (int i = 0; i < faces.length; i ++) {
            String[] s = f.get(i).split(" ");
            int[] fc = new int[s.length];

            for (int j = 0; j < fc.length; j ++) {
                fc[j] = Integer.parseInt(s[j].split("/")[0]);
            }

            faces[i] = new Face(fc);
        }

        return new Mesh3D(verts, faces);
    }
}
