package com.babuka.physics;

public class Connection {

    public Point p1;
    public Point p2;
    public double length;
    public Material mat;
    public boolean broken;

    public double stress;

    public Simulation sim;

    public Connection(Point p1, Point p2, double length, Material mat, Simulation sim) {
        this.p1 = p1;
        this.p2 = p2;
        this.length = length;
        this.mat = mat;
        this.broken = false;
        this.sim = sim;

    }

    Vector temp = new Vector(0, 0);
    public void update () {
        if (!broken) {
            double gotLength = getLength();
            double expanded = gotLength - length;
            double contract = expanded * mat.k;
            double ratio = gotLength / length;

            double move1 = contract * p2.mass / (p1.mass + p2.mass);
            double move2 = contract * p1.mass / (p1.mass + p2.mass);

            if (p1.anchor && p2.anchor) {
                move1 = 0;
                move2 = 0;
            }
            if (p1.anchor) {
                move1 = 0;
                move2 = contract;
            }
            if (p2.anchor) {
                move2 = 0;
                move1 = contract;
            }

            double force = Math.abs(move1 * p1.mass) + Math.abs(move2 * p2.mass);
            broken = mat.breaks(force, ratio);

            stress = Math.max(stress, mat.getStress(force, ratio));

            if (!broken) {
                temp = p2.cpy().subtract(p1).setLength(move1);
                p1.addSmart(temp);

                temp = p1.cpy().subtract(p2).setLength(move2);
                p2.addSmart(temp);
            }
        }
    }

    public double getLength () {
        return p1.distance(p2);
    }

    public void resetStress () {
        stress = 0;
    }

}
