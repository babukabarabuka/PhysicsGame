package com.babuka.physics;

public class Main {

    public static Window window;
    public static Simulation sim;

    public static void main (String[] args){
        window = new Window(800, 576, "Test");

        sim = new Simulation();

        sim.addPoint(300, 100, 1, true);
        sim.addPoint(400, 100, 1, false);
        sim.addPoint(500, 100, 10, false);
        sim.addConnection(0, 1, sim.wood);
        sim.addConnection(1, 2, sim.wood);

        //Game game = new Game();
        //game.start();

        {
            long oneSecond = 1_000_000_000;

            boolean running = true;
            int targetFPS = 60, targetTPS = 60;
            int targetFrameTime = (int) (oneSecond / targetFPS), targetTickTime = (int) (oneSecond / targetTPS);
            long lastFrame = 0, lastTick = 0;

            int frameCounter = 0, tickCounter = 0;
            long lastCount = 0;

            while (running) {

                if (System.nanoTime() - lastCount >= oneSecond) {
                    lastCount = System.nanoTime();
                    System.out.println("FPS: " + frameCounter + " TPS: " + tickCounter);

                    frameCounter = 0;
                    tickCounter = 0;
                }

                if (System.nanoTime() - lastTick >= targetTickTime) {
                    lastTick = System.nanoTime();
                    sim.update();
                    tickCounter += 1;
                }
                if (System.nanoTime() - lastFrame >= targetFrameTime) {
                    lastFrame = System.nanoTime();
                    window.draw();
                    frameCounter += 1;
                }

                if (lastTick + targetTickTime > System.nanoTime() && lastFrame + targetFrameTime > System.nanoTime()) {
                    long nextLoop = Math.min(lastTick + targetTickTime, lastFrame + targetFrameTime);
                    long waitTime = nextLoop - System.nanoTime();

                    if (waitTime > oneSecond / 16) {
                        try {
                            Thread.sleep(waitTime / 1000000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("I just couldn't wait!");
                        }
                    }
                }
            }
        }//game loop
    }

}
