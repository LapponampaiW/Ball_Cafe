package com.su.lapponampai_w.ball_cafe;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 4/16/16.
 */
public class MyManage {

    MySQLiteOpenHelper openHelper;
    SQLiteDatabase readSqLiteDatabase, writeSqLiteDatabase;
    String[] columnUser = {"_id","User","Password","Name"};
    String[] columnFood = {column_id,column_Food,column_Source,column_Price};
    public static final String column_id = "_id";
    public static final String column_Food = "Food";
    public static final String column_Source = "Source";
    public static final String column_Price = "Price";

    public MyManage(Context context) {


        openHelper = new MySQLiteOpenHelper(context);
        readSqLiteDatabase = openHelper.getReadableDatabase();
        writeSqLiteDatabase = openHelper.getWritableDatabase();

    }

    public long addValue(int intTable,
                         String strColumn2,
                         String strColumn3,
                         String strColumn4) {
        ContentValues contentValues = new ContentValues();

        long mylong = 0;

        switch (intTable) {
            case 1:
                contentValues.put("User",strColumn2);
                contentValues.put("Password",strColumn3);
                contentValues.put("Name",strColumn4);

                mylong = writeSqLiteDatabase.insert("userTABLE", null, contentValues);

                break;
            default:
                break;

        }
        return mylong;
    }
    //ทำการ filter ได้สำเร็จ
    public Cursor filter(String i) {
        Cursor cursor = readSqLiteDatabase.query("userTABLE", columnUser , "User = " + i +";", null, null, null, null);
        return cursor;
    }

    public void errorDialog(Context context,String strTitle, String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.icon_question);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


    public String[] searchUserNamePassword(String strUser) {
        try {
            String[] strResult = null;
           Cursor cursor = readSqLiteDatabase.query("userTABLE", columnUser , "User =?", new String[]{String.valueOf(strUser)}, null, null, null);
            //Cursor cursor = readSqLiteDatabase.query("userTABLE", columnUser , "User = " + strUser +";", null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    strResult = new String[4];

                    strResult[0] = cursor.getString(0);
                    strResult[1] = cursor.getString(1);
                    strResult[2] = cursor.getString(2);
                    strResult[3] = cursor.getString(3);
                }
                cursor.close();
                return strResult;
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public String[] readAllFood(int intColumn) {
        String[] strAllFood = null;
        Cursor cursor = readSqLiteDatabase.query("foodTABLE",columnFood,null,null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
            strAllFood = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                switch (intColumn) {
                    case 0:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(column_id));
                        break;
                    case 1:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(column_Food));
                        break;
                    case 2:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(column_Source));
                        break;
                    case 3:
                        strAllFood[i] = cursor.getString(cursor.getColumnIndex(column_Price));
                        break;
                    default:
                        break;
                }
                cursor.moveToNext();
            }

        }

        return strAllFood;
    }
}
