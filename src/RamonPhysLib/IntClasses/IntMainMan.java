package RamonPhysLib.IntClasses;/*
 * Decompiled with CFR 0_118.
 */
import RamonPhysLib.math.IntVector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


class IntMainMan {
    public static ArrayList<IntEntity> living;
    public static JPanel panel;
    public static IntEngine eng;

    IntMainMan() {
    }

    public static void main(String[] args) {
        System.out.println("int main");
        IntMainMan main = new IntMainMan();
        main.go();
    }

    public void makeBalls() {
        for (int i = 0; i < 7; ++i) {
            int rndXLoc = (int)(Math.random() * 600.0);
            int rndYLoc = (int)(Math.random() * 600.0);
            int rndYVel = (int)(Math.random() * 400.0) - 200;
            int rndXVel = (int)(Math.random() * 400.0) - 200;
            IntCoordinate loc = new IntCoordinate(rndXLoc, rndYLoc);
            IntVector vel = new IntVector(rndXVel, rndYVel);
            IntCircleEntity ent = new IntCircleEntity(loc, 30.0);
            ent.setVel(vel);
            living.add(ent);
        }
    }

    public void go() {
        living = new ArrayList();
        eng = new IntEngine(living);
        this.makeBalls();
        this.setupGUI();
        do {
            panel.repaint();
            eng.go();
            try {
                Thread.sleep(20);
            }
            catch (Exception ex) {
            }
        } while (true);
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
                IntMainMan.panel.repaint();
                try {
                    Thread.yield();
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
                IntMainMan.eng.go();
                try {
                    Thread.yield();
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
            g.fillRect(0, 0, IntMainMan.panel.getWidth(), IntMainMan.panel.getHeight());
            g.setColor(Color.BLACK);
            for (IntEntity ent : IntMainMan.living) {
                IntCoordinate pos = ent.getLoc();
                int x = pos.getX();
                int y = pos.getY();
                g.fillOval(x - 30, y - 30, 60, 60);
            }
        }
    }

}

