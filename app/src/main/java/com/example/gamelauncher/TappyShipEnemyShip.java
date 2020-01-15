package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class TappyShipEnemyShip {
    private Bitmap bitmap;
    private Rect hitbox;
    private int x;
    private int y;
    private int speed = 1;

    private int minX;
    private int maxX;

    private int minY;
    private int maxY;

    public TappyShipEnemyShip(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        minX = 0;
        minY = 0;
        maxX = screenX;
        maxY = screenY;

        Random randomGenerator = new Random();
        speed = randomGenerator.nextInt(6) + 10;

        x = screenX;
        y = randomGenerator.nextInt(maxY) - bitmap.getHeight();
        hitbox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;

        if (x < minX - bitmap.getWidth()) {
            Random randomGenerator = new Random();
            speed = randomGenerator.nextInt(10) + 10;
            x = maxX;
            y = randomGenerator.nextInt(maxY) - bitmap.getHeight();
        }

        hitbox.left = x;
        hitbox.top = y;
        hitbox.right = x + bitmap.getWidth();
        hitbox.bottom = y + bitmap.getHeight();
    }
}
