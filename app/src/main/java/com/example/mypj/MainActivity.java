package com.example.mypj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    public SQLiteDatabase db;
    TextView LuuUserName = (TextView) findViewById(R.id.UserNameTextView);
    TextView LuuTien = (TextView) findViewById(R.id.MoneyTextView);
    public Login login = new Login();
    String valueUserName = login.getUsername();

    public void IsProletariat() {
        db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT Tien FROM dbUser WHERE Username=?", new String[]{valueUserName});
        if (cursor.moveToFirst()) {
            int tienIndex = cursor.getColumnIndex("Tien");
            if (tienIndex == 0) {

            }
            String tienValue = cursor.getString(tienIndex);
            LuuTien.setText(tienValue + "vnđ");
        }
        cursor.close();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LuuUserName.setText(valueUserName);
    }
}