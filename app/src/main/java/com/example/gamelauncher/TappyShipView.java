package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TappyShipView extends SurfaceView implements Runnable {
    private Thread gameThread = null;
    private volatile Boolean playing;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private TappyShipPlayerShip player;
    public TappyShipEnemyShip enemyShip1;
    public TappyShipEnemyShip enemyShip2;
    public TappyShipEnemyShip enemyShip3;
    public ArrayList<TappyShipSpaceDust> dusts = new ArrayList<TappyShipSpaceDust>();

    public TappyShipView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        surfaceHolder = getHolder();
        paint = new Paint();
        player = new TappyShipPlayerShip(context, screenSizeX, screenSizeY);
        enemyShip1 = new TappyShipEnemyShip(context, screenSizeX, screenSizeY);
        enemyShip2 = new TappyShipEnemyShip(context, screenSizeX, screenSizeY);
        enemyShip3 = new TappyShipEnemyShip(context, screenSizeX, screenSizeY);

        int spaceDusts = 40;
        for (int i = 0; i < spaceDusts; i++) {
            TappyShipSpaceDust dust = new TappyShipSpaceDust(screenSizeX, screenSizeY);
            dusts.add(dust);
        }
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
        enemyShip1.update(player.getSpeed());
        enemyShip2.update(player.getSpeed());
        enemyShip3.update(player.getSpeed());

        for (TappyShipSpaceDust dust : dusts) {
            dust.update(player.getSpeed());
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            // Lock the canvas so we can draw to it
            canvas = surfaceHolder.lockCanvas();

            // Clear the last frame by painting the screen black
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            // Draw the player
            paint.setColor(Color.argb(255, 255, 255, 255));
            for (TappyShipSpaceDust dust : dusts) {
                canvas.drawPoint(dust.getX(), dust.getY(), paint);
            }
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            canvas.drawBitmap(enemyShip1.getBitmap(), enemyShip1.getX(),enemyShip1.getY(), paint);
            canvas.drawBitmap(enemyShip2.getBitmap(), enemyShip2.getX(),enemyShip2.getY(), paint);
            canvas.drawBitmap(enemyShip3.getBitmap(), enemyShip3.getX(),enemyShip3.getY(), paint);

            // Unlock the canvas and draw the scene
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
        }

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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.startBoosting();
                break;
        }

        return true;
    }
}
