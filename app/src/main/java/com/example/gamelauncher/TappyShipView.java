package com.example.gamelauncher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
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
    private float distanceRemaining;
    private long timeTaken;
    private long timeStarted;
    private long fastestTime;
    private SurfaceHolder surfaceHolder;
    private TappyShipPlayerShip player;
    public TappyShipEnemyShip enemyShip1;
    public TappyShipEnemyShip enemyShip2;
    public TappyShipEnemyShip enemyShip3;
    public ArrayList<TappyShipSpaceDust> dusts = new ArrayList<TappyShipSpaceDust>();
    private int screenX;
    private int screenY;

    public TappyShipView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        screenX = screenSizeX;
        screenY = screenSizeY;
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
        if (Rect.intersects(player.getHitbox(), enemyShip1.getHitbox())) {
            enemyShip1.setX(-100);
        }
        if (Rect.intersects(player.getHitbox(), enemyShip2.getHitbox())) {
            enemyShip2.setX(-100);
        }
        if (Rect.intersects(player.getHitbox(), enemyShip3.getHitbox())) {
            enemyShip3.setX(-100);
        }
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
            canvas.drawRect(player.getHitbox().left, player.getHitbox().top, player.getHitbox().right, player.getHitbox().bottom, paint);
            canvas.drawRect(enemyShip1.getHitbox().left, enemyShip1.getHitbox().top, enemyShip1.getHitbox().right, enemyShip1.getHitbox().bottom, paint);
            canvas.drawRect(enemyShip2.getHitbox().left, enemyShip2.getHitbox().top, enemyShip2.getHitbox().right, enemyShip2.getHitbox().bottom, paint);
            canvas.drawRect(enemyShip3.getHitbox().left, enemyShip3.getHitbox().top, enemyShip3.getHitbox().right, enemyShip3.getHitbox().bottom, paint);
            for (TappyShipSpaceDust dust : dusts) {
                canvas.drawPoint(dust.getX(), dust.getY(), paint);
            }
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            canvas.drawBitmap(enemyShip1.getBitmap(), enemyShip1.getX(),enemyShip1.getY(), paint);
            canvas.drawBitmap(enemyShip2.getBitmap(), enemyShip2.getX(),enemyShip2.getY(), paint);
            canvas.drawBitmap(enemyShip3.getBitmap(), enemyShip3.getX(),enemyShip3.getY(), paint);

            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor((Color.argb(255, 255, 255, 255)));
            paint.setTextSize(25);
            canvas.drawText("Fastest: " + fastestTime + "s", 10, 20, paint);
            canvas.drawText("Time: " + timeTaken + "s", screenX / 2, 20, paint);
            canvas.drawText("Distance: " + distanceRemaining / 1000 + " km", screenX / 3, screenY - 20, paint);
            canvas.drawText("Shield: " + player.getShieldStrength(), 10, screenY - 20, paint);
            canvas.drawText("Speed: " + player.getSpeed() * 60 + " MPS", (screenX / 3) * 2, screenY - 20, paint);
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
