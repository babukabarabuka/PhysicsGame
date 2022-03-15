package com.babuka.physics;

import java.util.ArrayList;

public class Simulation {

    public Vector g = new Vector(0, 0.2);//units per tick
    public double resistance = 1;
    public int rigidity = 4;

    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Connection> connections = new ArrayList<Connection>();

    public Material wood = new Material("wood", 0.5, 10, 10);

    public Simulation () {}


    public void update () {

        for (int i = 0; i < points.size(); i ++) {
            points.get(i).update();
        }
        for (int j = 0; j < rigidity; j ++) {
            for (int i = 0; i < connections.size(); i++) {
                if (j == 0) {
                    connections.get(i).resetStress();
                }
                connections.get(i).update();
            }
        }
    }

    public Point closestPoint (Vector v, double limit) {
        Point p = null;
        double d = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i ++) {
            double dist = points.get(i).distance(v);
            if (dist < d) {
                d = dist;
                p = points.get(i);
            }
        }
        if (d <= limit) {
            return p;
        }
        return null;
    }

    public void addPoint (double x, double y, double mass, boolean anchor) {
        points.add(new Point(x, y, mass, anchor, this));
    }
    public void addConnection (int p1, int p2, Material mat) {
        connections.add(new Connection(points.get(p1), points.get(p2), points.get(p1).distance(points.get(p2)), mat, this));
    }
    public void addConnection (Point p1, Point p2, Material mat) {
        connections.add(new Connection(p1, p2, p1.distance(p2), mat, this));
    }
}
