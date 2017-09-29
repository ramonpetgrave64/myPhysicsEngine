/*
 * Decompiled with CFR 0_118.
 */
import RamonPhysLib.engine.Engine;
import RamonPhysLib.entity.CircleEntity;
import RamonPhysLib.entity.Entity;
import RamonPhysLib.math.Coordinate;
import RamonPhysLib.math.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MainMan {
    public static ArrayList<Entity> living;
    public static JPanel panel;
    public static Engine eng;

    MainMan() {
    }

    public static void main(String[] args) {
        MainMan main = new MainMan();
        main.go();
    }
/*
    public void makeBalls() {
        Vector vA = new Vector(90.0, 80.0);
        Vector vB = new Vector(-90.0, 80.0);
        Coordinate locA = new Coordinate(100.0, 100.0);
        Coordinate locB = new Coordinate(500.0, 100.0);
        CircleEntity entA = new CircleEntity(locA, 30.0);
        CircleEntity entB = new CircleEntity(locB, 30.0);
        entA.setVel(vA);
        entB.setVel(vB);
        living.add(entA);
        living.add(entB);
    }
*/
    public void makeBalls() {
            for (int i = 0; i < 4; ++i) {
            int rndXLoc = (int)(Math.random() * 600.0);
            int rndYLoc = (int)(Math.random() * 600.0);
            int rndYVel = (int)(Math.random() * 400.0) - 200;
            int rndXVel = (int)(Math.random() * 400.0) - 200;
            Coordinate loc = new Coordinate(rndXLoc, rndYLoc);
            Vector vel = new Vector(rndXVel, rndYVel);
            CircleEntity ent = new CircleEntity(loc, 30.0);
            ent.setVel(vel);
            living.add(ent);
        }
    }
    public void go() {
        living = new ArrayList();
        eng = new Engine(living);
        this.makeBalls();
        this.setupGUI();
        long startingTime = System.currentTimeMillis();
        long runningTime = 0;
        boolean numUpdates = false;
        boolean fps = false;
        Thread graphThread = new Thread(new PaintJob());
        Thread engThread = new Thread(new EngineJob());
        graphThread.start();
        engThread.start();
    }

    public void setupGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        panel = new GraphicsPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class PaintJob
    implements Runnable {
        PaintJob() {
        }

        public void run() {
            do {
                MainMan.panel.repaint();
                try {
                    Thread.sleep(50);
                }
                catch (Exception ex) {
                }
            } while (true);
        }
    }

    class EngineJob
    implements Runnable {
        EngineJob() {
        }

        public void run() {
            do {
                MainMan.eng.go();
                try {
                    Thread.sleep(50);
                }
                catch (Exception ex) {
                }
            } while (true);
        }
    }

    public class GraphicsPanel
    extends JPanel {
        public void paintComponent(Graphics g) {
            double radius = 30.0;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, MainMan.panel.getWidth(), MainMan.panel.getHeight());
            g.setColor(Color.BLACK);
            for (Entity ent : MainMan.living) {
                Coordinate pos = ent.getLoc();
                int x = (int)pos.getX();
                int y = (int)pos.getY();
                g.fillOval(x - 30, y - 30, 60, 60);
            }
        }
    }

}

