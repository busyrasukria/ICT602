/*
 * Copyright (c) Created by Busyra 2024.
 * Last modified 11/18/24, 6:46
 */

package com.example.electricitybills;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView output, view1, view2, tCharge, tRebate, fCost;
    Button btnCalc, btnClear;
    EditText electric, rebate;
    ImageView profile, info;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        electric = findViewById(R.id.electric);
        rebate = findViewById(R.id.rebate);
        tCharge = findViewById(R.id.tCharge);
        tRebate = findViewById(R.id.tRebate);
        fCost = findViewById(R.id.fCost);
        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);
        profile = findViewById(R.id.profile);
        info = findViewById(R.id.info);

        btnCalc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    if (!electric.getText().toString().matches("\\d+")) { // Ensures only digits
                        Toast.makeText(MainActivity.this, "Enter a valid number for electricity usage!", Toast.LENGTH_SHORT).show();
                        output.setText("RM 0.00");
                        electric.setText("");
                        rebate.setText("");
                        tCharge.setText("Total Charges: RM 0.00");
                        tRebate.setText("Total Rebate: RM 0.00");
                        fCost.setText("Total Cost: RM 0.00");;
                        return;
                    }
                    if (!rebate.getText().toString().matches("\\d+(\\.\\d+)?")) {
                        Toast.makeText(MainActivity.this, "Enter a valid rebate percentage!", Toast.LENGTH_SHORT).show();
                        output.setText("RM 0.00");
                        electric.setText("");
                        rebate.setText("");
                        tCharge.setText("Total Charges: RM 0.00");
                        tRebate.setText("Total Rebate: RM 0.00");
                        fCost.setText("Total Cost: RM 0.00");
                        return;
                    }

                    int usage = Integer.parseInt(electric.getText().toString());
                    float rebateValue = Float.parseFloat(rebate.getText().toString());

                    if (rebateValue < 0 || rebateValue > 5) {
                        Toast.makeText(MainActivity.this, "Rebate must be between 0% and 5%!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    float cost = 0.0F;
                    rebateValue = rebateValue / 100;

                    if (usage >= 1 && usage <= 200) {
                        cost = usage * 0.218F;
                    } else if (usage >= 201 && usage <= 300) {
                        cost = 200 * 0.218F + (usage - 200) * 0.334F;
                    } else if (usage >= 301 && usage <= 600) {
                        cost = 200 * 0.218F + 100 * 0.334F + (usage - 300) * 0.516F;
                    } else if (usage >= 601 && usage <= 900) {
                        cost = 200 * 0.218F + 100 * 0.334F + 300 * 0.516F + (usage - 600) * 0.546F;
                    } else if (usage > 900) {
                        cost = 200 * 0.218F + 100 * 0.334F + 300 * 0.516F + 300 * 0.546F + (usage - 900) * 0.546F;
                    }

                    float totalRebate = cost * rebateValue;
                    float finalCost = cost - totalRebate;
                    output.setText(String.format("RM %.2f", finalCost));

                    tCharge.setText(String.format("Total Charges: RM %.2f", cost));
                    tRebate.setText(String.format("Total Rebate: RM %.2f", totalRebate));
                    fCost.setText(String.format("Total Cost: RM %.2f", finalCost));

                } catch (NumberFormatException nfe) {
                    Toast.makeText(MainActivity.this, "Please enter valid numbers!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("RM 0.00");
                electric.setText("");
                rebate.setText("");
                tCharge.setText("Total Charges: RM 0.00");
                tRebate.setText("Total Rebate: RM 0.00");
                fCost.setText("Total Cost: RM 0.00");
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialog();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pic), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Electricity Bill Instruction")
                .setMessage("How to use:\n\n" +
                        "1. Enter your electricity usage in number only. \n" +
                        "2. Enter rebate between 0 and 5 %. \n" +
                        "3. Tap 'Calculate' to compute the total cost.\n" +
                        "4. Review the results, including charges and rebate.\n"+
                        "5. Tap 'Clear' if want to reset.\n")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

