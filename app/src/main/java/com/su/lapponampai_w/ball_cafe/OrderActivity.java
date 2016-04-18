package com.su.lapponampai_w.ball_cafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    MyManage myManage;
    ListView listView;
    TextView textView2;
    String stringOfficer,strDesk,stringFood,itemString;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        myManage = new MyManage(this);

        bindwidget();

        showofficer();


        showSpinner();

        createListView();

    }

    private void showSpinner() {
        final String[] strDeskSpinner = {"one", "two", "three", "four", "five"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDeskSpinner);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDesk = strDeskSpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strDesk = strDeskSpinner[0];
            }
        });

    } //showSpinner

    private void showofficer() {
        stringOfficer = getIntent().getStringExtra("welcome");
        textView2.setText(stringOfficer);
    }

    private void bindwidget() {

        listView = (ListView) findViewById(R.id.listView);
        textView2 = (TextView) findViewById(R.id.textView2);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void createListView() {

        final String[] strFood = myManage.readAllFood(1);
        String[] strSource = myManage.readAllFood(2);
        String[] strPrice = myManage.readAllFood(3);
        int[] ints = {R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4,
                R.drawable.food5,R.drawable.food6,R.drawable.food7,R.drawable.food8,
                R.drawable.food9,R.drawable.food10};



        MyAdaptor myAdaptor = new MyAdaptor(OrderActivity.this, strFood, ints, strPrice);
        listView.setAdapter(myAdaptor);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stringFood = strFood[position];
                Toast t = Toast.makeText(OrderActivity.this, stringFood, Toast.LENGTH_LONG);
                t.show();
                chooseitem();
            }
        });

    }

    private void chooseitem() {
        CharSequence[] charSequences = {"1 จาน", "2 จาน", "3 จาน", "4 จาน", "5 จาน"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //เจอ Builder แล้ว ctrl + space เลย
        builder.setTitle(stringFood);
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemString = Integer.toString(which + 1);

                dialog.dismiss();
            }

        });

        builder.show();


    }

}
