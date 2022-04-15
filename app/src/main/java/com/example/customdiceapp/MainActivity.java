package com.example.customdiceapp;
//Vinith Nair A00241282

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //storing all the elements into variables
        Spinner diceList = findViewById(R.id.spinner);
        TextView rollResult = findViewById(R.id.rollResult);
        Button rollOnceBtn = findViewById(R.id.rollOnceBtn);
        Button rollTwiceBtn = findViewById(R.id.rollTwiceBtn);
        EditText addDice = findViewById(R.id.addDice);
        Button addBtn = findViewById(R.id.addBtn);


        //entering initial hard coded values of the sides
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("4");
        arrayList.add("6");
        arrayList.add("8");
        arrayList.add("10");
        arrayList.add("12");
        arrayList.add("20");

        //setting the array adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diceList.setAdapter(arrayAdapter);



        //adding functionality of returning one value of the dice
        rollOnceBtn.setOnClickListener(new android.view.View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int max = Integer.parseInt(diceList.getSelectedItem().toString());
                String result = String.valueOf((int)(Math.random() * ((max - 1) + 1)) + 1);
                rollResult.setText(result);
            }
        });

        //adding functionality of returning two values of the dice
        rollTwiceBtn.setOnClickListener(new android.view.View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int max = Integer.parseInt(diceList.getSelectedItem().toString());
                String result1 = String.valueOf((int)(Math.random() * ((max - 1) + 1)) + 1);
                String result2 = String.valueOf((int)(Math.random() * ((max - 1) + 1)) + 1);
                rollResult.setText(result1+","+result2);
            }
        });

        //adding functionality of adding new dimension's dice to the spinner
        addBtn.setOnClickListener(new android.view.View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String newDice = addDice.getText().toString();

                //EXTRAS: User is not allowed to give any negative number or 0 as input
                if(Integer.parseInt(newDice)<1){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Enter a value greater than 0",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else {

                   SharedPreferences shrd = getSharedPreferences("test", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("str", newDice);
                    editor.apply();
                    arrayList.add(newDice);
                    addDice.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Value added",
                            Toast.LENGTH_SHORT);

                    toast.show();

                }
            }
        });

        //getting the stored value form shared preference
        SharedPreferences getShared = getSharedPreferences("test", MODE_PRIVATE);
        String value = getShared.getString("str","");
        arrayList.add(value);


    }
}