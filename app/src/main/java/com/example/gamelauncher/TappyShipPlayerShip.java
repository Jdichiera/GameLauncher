package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class TappyShipPlayerShip {
    private int x;
    private int y;
    private int maxY;
    private int minY;
    private int speed;
    private Bitmap bitmap;
    private Rect hitbox;
    private boolean boosting;
    private int shieldStrength;
    private final int GRAVITY = -12;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;


    public TappyShipPlayerShip(Context context, int screenSizeX, int screenSizeY) {
        x = 50;
        y = 50;
        speed = 1;
        boosting = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        maxY = screenSizeY - bitmap.getHeight();
        minY = 0;
        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
        shieldStrength = 2;
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

    public Rect getHitbox() {
        return hitbox;
    }

    public int getShieldStrength() {
        return shieldStrength;
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

        if (y > maxY) {
            y = maxY;
        }

        if (y < minY) {
            y = minY;
        }

        hitbox.left = x;
        hitbox.top = y;
        hitbox.right = x + bitmap.getWidth();
        hitbox.bottom = y + bitmap.getHeight();
    }
}
