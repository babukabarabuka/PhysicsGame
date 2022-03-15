package com.babuka.physics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static com.babuka.physics.Inputs.*;
import static java.util.Map.entry;

public class KeyboardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.putIfAbsent(e.getKeyChar(), true);
        keys.put(e.getKeyChar(), true);

        lastPressed[e.getKeyCode()] = pressed[e.getKeyCode()];
        pressed[e.getKeyCode()] = true;

        if (!lastPressed[72] && pressed[72]) {
            selectedAnchor = ! selectedAnchor;
        }

        if (!lastPressed[189] && pressed[189]) {
            selectedMass -= 0.5;
        }
        if (!lastPressed[187] && pressed[187]) {
            selectedMass += 0.5;
        }
        selectedMass = Math.min(0.5, selectedMass);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.putIfAbsent(e.getKeyChar(), false);
        keys.put(e.getKeyChar(), true);

        lastPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;

        if (e.getKeyCode() == 16) {
            startPoint = null;
        }
    }
}
