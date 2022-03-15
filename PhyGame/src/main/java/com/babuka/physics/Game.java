package com.babuka.physics;

public class Game extends Thread{

    @Override
    public void run () {
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
                    Main.sim.update();
                    tickCounter += 1;
                }
                if (System.nanoTime() - lastFrame >= targetFrameTime) {
                    lastFrame = System.nanoTime();
                    Main.window.draw();
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
