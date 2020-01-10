package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TappyShipPlayerShip {
    private int x;
    private int y;
    private int maxY;
    private int minY;
    private int speed;
    private Bitmap bitmap;
    private boolean boosting;
    private final int GRAVITY = -12;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;


    public TappyShipPlayerShip(Context context, int screenSizeX, int screenSizeY) {
        x = 50;
        y = 50;
        speed = 1;
        boosting = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        maxY = 0;
        minY = screenSizeY - bitmap.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void startBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        if (boosting) {
            speed += 2;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;

//        if (y < minY) {
//            y = minY;
//        }
//
//        if (y > maxY) {
//            y = maxY;
//        }
    }
}
