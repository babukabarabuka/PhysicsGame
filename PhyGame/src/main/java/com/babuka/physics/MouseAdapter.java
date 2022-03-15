package com.babuka.physics;

import java.awt.event.*;

import static com.babuka.physics.Inputs.*;

public class MouseAdapter implements MouseListener, MouseWheelListener, MouseMotionListener{

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            Inputs.mousePressedQueued = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            Inputs.mousePressedQueued = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        mouseX = e.getX();
        mouseY = e.getY() - Window.forehead;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        mouseX = e.getX();
        mouseY = e.getY() - Window.forehead;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}