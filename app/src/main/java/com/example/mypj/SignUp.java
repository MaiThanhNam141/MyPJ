package com.example.mypj;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SignUp extends Activity {
    public EditText SignupUsername, SignupPassword, SignupRepeatPassword;
    public Button SignupButton;
    public SQLiteDatabase db;

    public void insertDB(String username, String password) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            String sql = "INSERT INTO dbUser(Username, PassWord,Tien) values (?, ?,0)";
            db.execSQL(sql, new String[]{username, password});
            db.close();
            Toast.makeText(this, "Khởi tạo thành công", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi ở đây", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        SignupUsername = (EditText) findViewById(R.id.usernameEditText);
        SignupPassword = (EditText) findViewById(R.id.passwordEditText);
        SignupRepeatPassword = (EditText) findViewById(R.id.repeatPasswordEditText);
        SignupButton = (Button) findViewById(R.id.signUpButton);


        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password, repeatpassword;
                username = SignupUsername.getText().toString();
                password = SignupPassword.getText().toString();
                repeatpassword = SignupRepeatPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty())
                    Toast.makeText(SignUp.this, "Vui lòng điền thông tin tài khoản, mật khẩu", Toast.LENGTH_LONG).show();
                else if (!password.equals(repeatpassword)) {
                    Toast.makeText(SignUp.this, "Mật khẩu và Xác nhận mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
                } else {
                    insertDB(username, password);
                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
