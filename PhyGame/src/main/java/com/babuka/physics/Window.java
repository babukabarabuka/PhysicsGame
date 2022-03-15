package com.babuka.physics;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Window extends JPanel{

    public JFrame window;
    public int frameCount = 0;
    public Draw draw;
    public int width = 0;
    public int height = 0;
    public static int forehead = 27;

    public Window(int width, int height, String title) {

        this.width = width;
        this.height = height - forehead;

        window = new JFrame(title);
        window.pack();
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.addKeyListener(new KeyboardInputs());
        window.addMouseMotionListener(new MouseAdapter());
        window.addMouseListener(new MouseAdapter());
        window.addMouseWheelListener(new MouseAdapter());
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        draw = new Draw();
        window.add(draw);

    }

    public void draw () {

        draw.repaint(0, 0, width, height);
        window.setVisible(true);

        frameCount ++;

    }

    public void close () {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

}
