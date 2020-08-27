package com.example.currecyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Spinner sp1,sp2;
    EditText ed1;
    Button bt1;
    Button bt2;
    Button bt3;

    boolean b_green;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout)findViewById(R.id.con);

        final DecimalFormat df = new DecimalFormat("######0.00");

        SharedPreferences settings = getSharedPreferences("PREF", MODE_PRIVATE);
        b_green = settings.getBoolean("GREEN", false);

        if (b_green) {
            layout.setBackground(getResources().getDrawable(R.drawable.g));
        } else {
            layout.setBackground(getResources().getDrawable(R.drawable.cloud));
        }

        ed1 = findViewById(R.id.txtamount);
        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);

        String[] from = {"SGD"};
        ArrayAdapter ad = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,from);
        sp1.setAdapter(ad);

        String[] to = {"CNY","USD"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,to);
        sp2.setAdapter(ad1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double tot;

                Double amount= Double.parseDouble(ed1.getText().toString());

                if(sp1.getSelectedItem().toString()== "SGD" && sp2.getSelectedItem().toString()== "CNY")
                {
                    tot = amount * 4.988;
                    String tot_str = df.format(tot);
                    Toast.makeText(getApplicationContext(), tot_str, Toast.LENGTH_LONG).show();
                }
                else if (sp1.getSelectedItem().toString()== "SGD" && sp2.getSelectedItem().toString()=="USD")
                {
                    tot = amount * 0.704;
                    String tot_str = df.format(tot);
                    Toast.makeText(getApplicationContext(), tot_str, Toast.LENGTH_LONG).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });


    }
}
