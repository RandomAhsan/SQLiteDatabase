package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowData extends AppCompatActivity {

    TextView tvTitle ;
    ListView listView ;

    DatabaseHelper dbHelper ;

    HashMap <String, String> hashMap ;
    ArrayList <HashMap<String,String>> arrayList ;

    public static boolean EXPENSE = true ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = findViewById(R.id.listView);
        tvTitle = findViewById(R.id.tvTitle);
        dbHelper = new DatabaseHelper(this);


        if (EXPENSE == true ){
            tvTitle.setText("Showing All Expenses");
        } else {
            tvTitle.setText("Showing All Income");
        }


        loadData();

    }

    public void loadData () {

        Cursor cursor = null ;

        if (EXPENSE==true) cursor = dbHelper.getAllExpenses();
        else cursor = dbHelper.getAllIncome();

        if (cursor!=null&&cursor.getCount()>0){

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("amount", ""+amount);
                hashMap.put("time", ""+time);
                hashMap.put("reason", reason);
                arrayList.add(hashMap);
            }

            MyAdapter myAdapter = new MyAdapter();
            listView.setAdapter(myAdapter);


        } else {
            tvTitle.append("\nNo Data found...");
        }


    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item, parent, false);

            TextView tvReason = myView.findViewById(R.id.tvReason);
            TextView tvAmount = myView.findViewById(R.id.tvAmount);
            TextView tvDelete = myView.findViewById(R.id.tvDelete);

            hashMap = arrayList.get(position);

            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");
            String time = hashMap.get("time");



            tvReason.setText(reason);
            tvAmount.setText("BDT "+amount);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (EXPENSE==true) dbHelper.deleteExpense(id);
                    else dbHelper.deleteIncome(id);
                    loadData();
                }
            });

            return myView;
        }
    }
}