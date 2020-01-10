package com.example.gamelauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to our buttons
        final Button btnTappyShip = (Button) findViewById(R.id.btnTappyShip);

        btnTappyShip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Create new intent for tappy ship
        Intent tappyShipIntent = new Intent(this, TappyShip.class);
        startActivity(tappyShipIntent);
        finish();

    }
}
