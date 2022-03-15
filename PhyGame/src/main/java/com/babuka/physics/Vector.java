package com.babuka.physics;

public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector cpy () {
        return new Vector(x, y);
    }
    public Vector set (Vector v) {
        x = v.x;
        y = v.y;
        return this;
    }
    public Vector set (double vX, double vY) {
        x = vX;
        y = vY;
        return this;
    }

    public Vector add (Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }
    public Vector subtract (Vector v) {
        x -= v.x;
        y -= v.y;
        return this;
    }
    public Vector multiply (double a) {
        x *= a;
        y *= a;
        return this;
    }
    public double length () {
        return Math.sqrt(x * x + y * y);
    }
    public double distance (Vector v) {
        return cpy().subtract(v).length();
    }
    public Vector setLength (double l) {
        double mult = l / length();
        x *= mult;
        y *= mult;
        return this;
    }

    public int intX () {
        return (int) Math.round(x);
    }
    public int intY () {
        return (int) Math.round(y);
    }
    public String toString () {
        return "x= " + round(x, 2) + " y= " + round(y, 2);
        //return "x= " + x + " y= " + y;
    }
    public String shorten (double d) {
        String out = String.valueOf(d);
        if (d == 0) {
            return "0.0";
        }
        else if (d < 0) {
            return out.substring(0, Math.min(out.length(), 5 + Math.abs ((int) Math.floor(Math.log10(d))) ));
        }
        return out.substring(0, Math.min(out.length(), 4 +  Math.abs ((int) Math.floor(Math.log10(d))) ));
    }

    public static double round (double num, int digits) {
        int mult = (int) Math.pow(10, digits);
        num *= mult;
        num = Math.round(num);
        return num/mult;
    }

}
