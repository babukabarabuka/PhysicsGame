package com.babuka.physics;

public class Point extends Vector{

    public Vector velocity;
    public double mass;
    public boolean anchor;
    public boolean broken;

    public Vector queued;

    public Simulation sim;

    public Point(double x, double y, double mass, boolean anchor, Simulation sim) {
        super(x, y);
        this.velocity = new Vector(0, 0);
        this.mass = mass;
        this.anchor = anchor;
        this.broken = false;
        this.sim = sim;

        if (anchor) {
            this.mass = Double.MAX_VALUE / 100;
        }
        queued = new Vector(0, 0);
        temp = new Vector(0, 0);
    }


    public void update () {

        gravity();

        if (!anchor) {
            velocity.multiply(sim.resistance);
            add(velocity);
        }
    }

    public void gravity () {
        velocity.add(sim.g);
    }

    Vector temp;
    public void addSmart (Vector v) {
        temp.set(this).subtract(velocity);
        add(v);
        velocity.set(cpy().subtract(temp));
    }
    public void setSmart (Vector v) {
        temp.set(this).subtract(velocity);
        set(v);
        velocity.set(cpy().subtract(temp));
    }

    public boolean isSelected () {
        return this.equals(Inputs.selectedPoint);
    }

    public boolean immovable () {
        return anchor || isSelected();
    }




}
