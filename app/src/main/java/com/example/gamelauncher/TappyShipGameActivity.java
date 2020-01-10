package com.example.gamelauncher;

import android.app.Activity;
import android.os.Bundle;

public class TappyShipGameActivity extends Activity {
    private TappyShipView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new TappyShipView(this);

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
