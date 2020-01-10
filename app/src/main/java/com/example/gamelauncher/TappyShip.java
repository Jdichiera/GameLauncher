package com.example.gamelauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TappyShip extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tappy_ship);

        final Button btnTappyShipPlay = (Button) findViewById(R.id.btnTappyShipPlay);

        btnTappyShipPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent tappyShipGameIntent = new Intent(this, TappyShipGameActivity.class);
        startActivity(tappyShipGameIntent);
        finish();
    }
}
