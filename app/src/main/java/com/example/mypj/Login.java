package com.example.mypj;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class Login extends Activity {
    public Login() {}

    public EditText LoginUsername, LoginPassword;
    public Button LoginButton,SignupButton;
    public SQLiteDatabase db;
    public static final String dtbase = "QLCT.db";
    public void initDB(){
        db= openOrCreateDatabase(dtbase,MODE_PRIVATE,null);
        String sql;
        try{
            if(!isTableExists(db,"dbUser"))
            {
                sql = "CREATE TABLE dbUser(id_user INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Username TEXT NOT NULL, PassWord TEXT NOT NULL, Tien INT)";
                db.execSQL(sql);
                sql = "INSERT INTO dbUser(Username, PassWord, Tien) values ('admin','1','0')";
                db.execSQL(sql);
                db.close();
            }
        }catch (Exception ex){
            db.close();
            Toast.makeText(this, "Khởi tạo cơ sở dữ liệu không thành công!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public boolean isUser(String username, String password){
        try {
            db = openOrCreateDatabase(dtbase,MODE_PRIVATE,null);
            Cursor c = db.rawQuery("select * from dbUser where Username = ? and PassWord = ?", new String[]{username, password});
            c.moveToFirst();
            db.close();
            if (c.getCount() > 0)
            {
                c.close();
                return true;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Bug nek", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        LoginUsername = (EditText) findViewById(R.id.loginusernameEditText);
        LoginPassword = (EditText) findViewById(R.id.loginpasswordEditText);
        LoginButton = (Button) findViewById(R.id.loginButton);
        SignupButton = (Button) findViewById(R.id.signupButton);
        initDB();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = LoginUsername.getText().toString();
                String password = LoginPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Vui lòng điền thông tin tài khoản, mật khẩu", Toast.LENGTH_LONG).show();
                } else {
                    if (isUser(username, password)) {
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public String getUsername() {
        return LoginUsername.getText().toString();
    }
}
