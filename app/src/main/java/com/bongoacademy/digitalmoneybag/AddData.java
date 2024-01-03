package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddData extends AppCompatActivity {

    TextView tvTitle ;
    EditText edAmount, edReason ;
    Button button ;

    DatabaseHelper dbHelper ;

    public static boolean EXPENSE = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        tvTitle = findViewById(R.id.tvTitle);
        edAmount = findViewById(R.id.edAmount);
        edReason = findViewById(R.id.edReason);
        button = findViewById(R.id.button);
        dbHelper = new DatabaseHelper(this);

        if (EXPENSE == true){
            tvTitle.setText("Add Expense");
        } else {
            tvTitle.setText("Add Income");
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edAmount!=null && edReason!=null){
                    String sAmount = edAmount.getText().toString();
                    String reason = edReason.getText().toString();
                    double amount = Double.parseDouble(sAmount);


                    if (EXPENSE == true){
                        dbHelper.addExpense(amount, reason);
                        tvTitle.setText("Expense Added");
                    } else {
                        dbHelper.addIncome(amount, reason);
                        tvTitle.setText("Income Added");
                    }



                } else {
                    Toast.makeText(AddData.this, "Enter Data", Toast.LENGTH_SHORT).show();
                }







            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}