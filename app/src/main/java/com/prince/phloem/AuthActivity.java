package com.prince.phloem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class AuthActivity extends AppCompatActivity {
    SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        editor=preferences.edit();
        setContentView(R.layout.activity_auth);
        emailEditText=findViewById(R.id.emailId);
    }
    public void authenticateUser(View view){
        String email=emailEditText.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editor.putString("auth-id",email);
            editor.apply();
            finish();
        }else{
            emailEditText.setError("Enter valid email address");
        }
    }
}
