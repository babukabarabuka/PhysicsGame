package com.babuka.physics;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Inputs {


    public static Point selectedPoint;

    public static Map<Character, Boolean> keys = new HashMap<Character, Boolean>(Map.ofEntries(
            entry('w', false),
            entry('a', false),
            entry('s', false),
            entry('d', false),
            entry(' ', false)
    ));
    public static boolean[] lastPressed = new boolean[255];
    public static boolean[] pressed = new boolean[255];


    public static int mouseX, mouseY, lastMouseX, lastMouseY;
    public static boolean mousePressed, lastMousePressed, mousePressedQueued;
    public static double selectedMass = 1;
    public static boolean selectedAnchor = false;
    public static Point startPoint, endPoint;

    public static void update () {
        lastMousePressed = mousePressed;
        mousePressed = mousePressedQueued;

        if (!pressed[32] && !pressed[16]) {
            if (mousePressed && !lastMousePressed) {
                selectedPoint = Main.sim.closestPoint(new Vector(mouseX, mouseY), 20);
            }
            if (!mousePressed && lastMousePressed) {
                selectedPoint = null;
            }

            if (mousePressed && selectedPoint != null) {
                selectedPoint.setSmart(new Vector(mouseX, mouseY));
            }
        }
        else if (pressed[32] && !pressed[16]) {
            if (mousePressed && !lastMousePressed) {
                Main.sim.addPoint(mouseX, mouseY, selectedMass, selectedAnchor);
            }
        }
        else if (!pressed[32] && pressed[16]) {
            if (mousePressed && !lastMousePressed) {
                startPoint = Main.sim.closestPoint(new Vector(mouseX, mouseY), 20);
            }

            if (!mousePressed && lastMousePressed) {
                endPoint = Main.sim.closestPoint(new Vector(mouseX, mouseY), 20);
                if (startPoint != null && endPoint != null && !startPoint.equals(endPoint)) {
                    Main.sim.addConnection(startPoint, endPoint, Main.sim.wood);
                }
                startPoint = null;
            }
        }

    }


}
