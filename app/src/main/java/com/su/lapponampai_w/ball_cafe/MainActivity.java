package com.su.lapponampai_w.ball_cafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonlogin;
    EditText userEditText, passwordEditText;
    MyManage myManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);

        //myManage.addValue(1, "ballzv", "1234", "Weerachod");

        bingwidget();

        pressbuttonlogin();

        gotoOrderActivity();

    }

    private void gotoOrderActivity() {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }


    private void pressbuttonlogin() {
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.equals("") || passwordString.equals("")) {


                    myManage.errorDialog(MainActivity.this, "Ball Cafe", "กรุณากรอกข้อมูลให้ครบถ้วน");

                } else {

                    checkUserNamePassword(userString, passwordString);


                }




            }
        });
    }


    private void checkUserNamePassword(String userString, String passwordString) {
        String[] strings = myManage.searchUserNamePassword(userString);

        try {

            if (passwordString.equals(strings[2])) {
                Toast t = Toast.makeText(MainActivity.this, "สำเร็จแล้ว", Toast.LENGTH_LONG);
                t.show();
            } else {
                Toast t = Toast.makeText(MainActivity.this, "Username or Password Incorrect Please Check Again", Toast.LENGTH_LONG);
                t.show();
            }

        } catch (Exception e) {
            Toast t = Toast.makeText(MainActivity.this, "Exception e", Toast.LENGTH_LONG);
            t.show();
        }
    }

    private void bingwidget() {
        buttonlogin = (Button) findViewById(R.id.buttonLogin);
        userEditText = (EditText) findViewById(R.id.userEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }


}
