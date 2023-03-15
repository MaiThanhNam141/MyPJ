package com.example.mypj;

import android.app.Activity;
import android.content.Intent;
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
    public Login login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        SignupUsername = (EditText) findViewById(R.id.usernameEditText);
        SignupPassword = (EditText) findViewById(R.id.passwordEditText);
        SignupRepeatPassword =(EditText) findViewById(R.id.repeatPasswordEditText);
        SignupButton = (Button) findViewById(R.id.signUpButton);

        String username, password, repeatpassword;
        username = SignupUsername.getText().toString();
        password = SignupPassword.getText().toString();
        repeatpassword=SignupRepeatPassword.getText().toString();

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.equals(repeatpassword)){
                    Toast.makeText(SignUp.this, "Mật khẩu và Xác nhận mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
                }
                else {
                    login = new Login();
                    login.insertDB(username, password);
                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
