package com.babuka.physics;

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    public Draw() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int nodeSize = 10;

        g2.setColor(new Color(220, 220, 180));
        g2.fillRect(0, 0, Main.window.width, Main.window.height);

        Connection c;
        for (int i = 0; i < Main.sim.connections.size(); i ++) {
            c = Main.sim.connections.get(i);
            if (!c.broken) {
                g2.setColor(stressColor((float) c.stress));
                g2.setStroke(new BasicStroke(4));
                g2.drawLine(c.p1.intX(), c.p1.intY(), c.p2.intX(), c.p2.intY());
            }
        }

        Point p;
        for (int i = 0; i < Main.sim.points.size(); i ++) {
            p = Main.sim.points.get(i);
            if (!p.broken) {
                g2.setColor(Color.BLACK);
                g2.fillOval(p.intX() - nodeSize/2, p.intY() - nodeSize/2, nodeSize, nodeSize);
            }
        }
        /*
        if (MouseAdapter.selected != null) {
            g2.setColor(new Color (80, 120, 200));
            g2.setStroke(new BasicStroke(2));
            int radius = nodeSize + 2;
            g2.drawArc(MouseAdapter.selected.intX() - radius/2, MouseAdapter.selected.intY() - radius/2, radius, radius, 90, 360);
        }
         */
        g2.dispose();
    }

    static Color stressColor(float percentage) {
        percentage = Math.max(Math.min(percentage, 1), 0);
        float red = Math.min(percentage * 2, 1);
        float green = Math.min((1 - percentage) * 2, 1);
        float blue = 0;
        return new Color(red, green, blue);
    }
}
