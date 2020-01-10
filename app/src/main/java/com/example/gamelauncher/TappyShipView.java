package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TappyShipView extends SurfaceView implements Runnable {
    private volatile Boolean playing;
    private Thread gameThread = null;
    private TappyShipPlayerShip player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public TappyShipView(Context context) {
        super(context);

        surfaceHolder = getHolder();
        paint = new Paint();
        player = new TappyShipPlayerShip(context);
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        player.update();
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            // Lock the canvas so we can draw to it
            canvas = surfaceHolder.lockCanvas();

            // Clear the last frame by painting the screen black
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            // Draw the player
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);

            // Unlock the canvas and draw the scene
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {

    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
