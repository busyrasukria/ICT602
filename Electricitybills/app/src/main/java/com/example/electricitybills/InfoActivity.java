/*
 * Copyright (c) Created by Busyra 2024.
 * Last modified 11/26/24, 8:54PM
 */

package com.example.electricitybills;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info); // Create a new layout file for this activity

        TextView instruction = findViewById(R.id.instruction);
        instruction.setText("\"How to use:\\n\\n\" +\n" +
                "                        \"1. Enter your electricity usage in number only. \\n\" +\n" +
                "                        \"2. Enter rebate between 0 and 5 %. \\n\" +\n" +
                "                        \"3. Tap 'Calculate' to compute the total cost.\\n\" +\n" +
                "                        \"4. Review the results, including charges and rebate.\\n\"+\n" +
                "                        \"5. Tap 'Clear' if want to reset.\\n\")");
    }
}