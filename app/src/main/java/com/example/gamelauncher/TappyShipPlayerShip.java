package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TappyShipPlayerShip {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed;

    public TappyShipPlayerShip(Context context) {
        x = 50;
        y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
    }

    public void update() {

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

    public int getSpeed() {
        return speed;
    }
}
