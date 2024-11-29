/*
 * Copyright (c) Created by Busyra 2024.
 * Last modified 11/18/24, 6:46PM
 */

package com.example.electricitybills;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {

    Button github;
    Button btnback;
    ImageView home_vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);

        github = findViewById(R.id.github);
        github.setOnClickListener(v -> {
            String url = "https://github.com/busyrasukria";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(v -> {
            Intent intent = new Intent(About.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        home_vector = findViewById(R.id.home_vector);
        home_vector.setOnClickListener(v -> {
            Intent intent = new Intent(About.this, MainActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pic), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
