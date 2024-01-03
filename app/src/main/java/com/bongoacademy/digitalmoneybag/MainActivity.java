package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvFinalBalance, tvAddExpense, tvShowAllDataExpense, tvTotalExpense, tvAddIncome, tvShowAllDataIncome, tvTotalIncome ;
    DatabaseHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvFinalBalance = findViewById(R.id.tvFinalBalance);
        tvAddExpense = findViewById(R.id.tvAddExpense);
        tvShowAllDataExpense = findViewById(R.id.tvShowAllDataExpense);
        tvTotalExpense = findViewById(R.id.tvTotalExpense);
        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvShowAllDataIncome = findViewById(R.id.tvShowAllDataIncome);
        tvTotalIncome = findViewById(R.id.tvTotalIncome);
        dbHelper = new DatabaseHelper(this);




        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE = true ;
                startActivity(new Intent(getApplicationContext(), AddData.class));
            }
        });

        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE = false ;
                startActivity(new Intent(getApplicationContext(), AddData.class));
            }
        });



        tvShowAllDataExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData.EXPENSE = true ;
                startActivity(new Intent(getApplicationContext(), ShowData.class));
            }
        });

        tvShowAllDataIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData.EXPENSE = false ;
                startActivity(new Intent(getApplicationContext(), ShowData.class));
            }
        });



    }

    public void updateUI () {
        tvTotalExpense.setText("BDT "+dbHelper.calculateTotalExpense());
        tvTotalIncome.setText("BDT "+dbHelper.calculateTotalIncome());
        double balance = dbHelper.calculateTotalIncome() - dbHelper.calculateTotalExpense() ;
        tvFinalBalance.setText("BDT "+balance);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }


    //===================================================================
}