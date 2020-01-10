package com.example.gamelauncher;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class TappyShipGameActivity extends Activity {
    private TappyShipView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        // Get the display resolution and load it to a point object
        Point screenSize = new Point();
        display.getSize(screenSize);

        gameView = new TappyShipView(this, screenSize.x, screenSize.y);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
