package com.su.lapponampai_w.ball_cafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class OrderActivity extends AppCompatActivity {

    MyManage myManage;
    ListView listView;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        myManage = new MyManage(this);

        bindwidget();

        createListView();

    }

    private void bindwidget() {

        listView = (ListView) findViewById(R.id.listView);
    }

    private void createListView() {

        String[] strFood = myManage.readAllFood(1);
        String[] strSource = myManage.readAllFood(2);
        String[] strPrice = myManage.readAllFood(3);
        int[] ints = {R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4,
                R.drawable.food5,R.drawable.food6,R.drawable.food7,R.drawable.food8,
                R.drawable.food9,R.drawable.food10};



        MyAdaptor myAdaptor = new MyAdaptor(OrderActivity.this, strFood, ints, strPrice);
        listView.setAdapter(myAdaptor);


    }
}
