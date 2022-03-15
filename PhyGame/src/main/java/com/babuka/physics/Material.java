package com.babuka.physics;

public class Material {

    public String name;
    public double k;
    public double maxCompressedForce;
    public double maxExpandedForce;

    public Material(String name, double k, double maxCompressedForce, double maxExpandedForce) {
        this.name = name;
        this.k = k;
        this.maxCompressedForce = maxCompressedForce;
        this.maxExpandedForce = maxExpandedForce;
    }

    public boolean breaks (double force, double lengthRatio) {
        if (lengthRatio < 1) {
            return force > maxCompressedForce;
        }
        else {
            return force > maxExpandedForce;
        }
    }

    public double getStress (double force, double lengthRatio) {
        if (lengthRatio < 1) {
            return Math.min(Math.max(force / maxCompressedForce, 0), 1);
        }
        else {
            return Math.min(Math.max(force / maxExpandedForce, 0), 1);
        }
    }
}
