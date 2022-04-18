package renderer;

import reader.OBJReader;
import renderer.handlers.FocusHandler;
import renderer.handlers.KeyHandler;
import renderer.handlers.MouseHandler;
import renderer.utils.Point3D;
import renderer.utils.PointConverter;
import renderer.shapes.Line3D;
import renderer.shapes.Shape3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements Runnable{
    public final static int WIDTH = 1080;
    public final static int HEIGHT = 1080;
    private Thread thread;
    private KeyHandler kh;
    private MouseHandler mh;
    private FocusHandler fh;

    private ArrayList<Shape3D> shapes = new ArrayList<>();
    public static Camera camera;

    GraphicsPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(24, 26, 24));
        this.setDoubleBuffered(false);

        this.setFocusable(true);
        init();
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int gameTicks = 120;
        double drawInterval = 1000000000 / (double) gameTicks;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1) {
                update();
                delta --;
            }
            repaint();
        }
    }

    // called on program start
    private void init() {
        kh = new KeyHandler();
        addKeyListener(kh);
        mh = new MouseHandler();
        addMouseListener(mh);
        fh = new FocusHandler();
        addFocusListener(fh);

        // visualization of axes
        shapes.add(new Line3D(new Point3D(-50, 0, 0), new Point3D(50, 0, 0), new Color(255, 0, 0)));
        shapes.add(new Line3D(new Point3D(0, -50, 0), new Point3D(0, 50, 0), new Color(0, 255, 0)));
        shapes.add(new Line3D(new Point3D(0, 0, -50), new Point3D(0, 0, 50), new Color(0, 0, 255)));

        shapes.add(OBJReader.readOBJ(new File("objects/anvil.obj")));

        GraphicsPanel.createCamera();
    }

    private static void createCamera() {
        camera = new Camera(new Point3D(0, 0, 0),0, 0, 0);
    }

    // called before draw
    private void update() {
        int speed = 2;

        if (kh.spacePressed) camera.position.y += speed;
        if (kh.shiftPressed) camera.position.y -= speed;

        if (kh.wPressed) {
            camera.position.x += Math.sin(camera.ry) * speed;
            camera.position.z += Math.cos(camera.ry) * speed;
        }
        if (kh.sPressed) {
            camera.position.x -= Math.sin(camera.ry) * speed;
            camera.position.z -= Math.cos(camera.ry) * speed;
        }
        if (kh.aPressed) {
            camera.position.x -= Math.cos(camera.ry) * speed;
            camera.position.z += Math.sin(camera.ry) * speed;
        }
        if (kh.dPressed) {
            camera.position.x += Math.cos(camera.ry) * speed;
            camera.position.z -= Math.sin(camera.ry) * speed;
        }

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (fh.isFocused) {
            frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisible"));

            mh.updateMousePositions();

            camera.ry += Math.toRadians(mh.horizontalMotion) / 2;

            camera.rx += Math.toRadians(mh.verticalMotion) / 2;
            if (camera.rx > Math.PI) camera.rx = Math.PI;
            if (camera.rx < 0) camera.rx = 0;

            mh.resetMouse();
        } else {
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }

    // called on draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //long timeStart = System.nanoTime();

        for (Shape3D shape : shapes) {
            shape.render(g);
        }


        // debug
        //Point vec = PointConverter.convertPointTo2D(new Point3D(PointConverter.lookVec().x + camera.position.x, PointConverter.lookVec().y + camera.position.y, PointConverter.lookVec().z + camera.position.z));
        //g.setColor(Color.cyan);
        //g.drawLine(vec.x, vec.y, vec.x, vec.y);

        //System.out.println("Render took " + (System.nanoTime() - timeStart) + " nanos");


        g.dispose();
    }
}
