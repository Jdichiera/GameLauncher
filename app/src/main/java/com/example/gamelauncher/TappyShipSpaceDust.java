package com.example.gamelauncher;

import java.util.Random;

public class TappyShipSpaceDust {
    private int x;
    private int y;
    private int speed;

    private int minX;
    private int minY;

    private int maxX;
    private int maxY;

    public TappyShipSpaceDust(int screenX, int screenY) {
        minX = 0;
        minY = 0;
        maxX = screenX;
        maxY = screenY;
        Random randomGenerator = new Random();
        speed = randomGenerator.nextInt(10);

        x = randomGenerator.nextInt(maxX);
        y = randomGenerator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        y -= speed;

        if (x < 0) {
            x = maxX;
            Random randomGenerator = new Random();
            y = randomGenerator.nextInt(maxY);
            speed = randomGenerator.nextInt(15);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
