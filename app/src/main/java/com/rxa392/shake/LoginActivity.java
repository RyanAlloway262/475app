package com.rxa392.shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Model M = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void CreateAccountButton(View view){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);

        Button button = (Button) findViewById(R.id.create_account);
        EditText eText = (EditText)findViewById(R.id.edit_Text_EmailAddress);
        EditText pText = (EditText)findViewById(R.id.edit_Text_Password);

        Context context = getApplicationContext();
        String accountAdded = "Account added";
        String accountAlreadyHere = "Email already in use or Invalid Email";

        int duration = Toast.LENGTH_SHORT;
        Toast accountAddedToast = Toast.makeText(context, accountAdded, duration);
        Toast accountAlreadyHereToast = Toast.makeText(context, accountAlreadyHere, duration);

        String emailText = eText.getText().toString();
        String passwordText = pText.getText().toString();

        System.out.println("Added Email: "+emailText+'\n'+"\tAdded Password: "+passwordText);

        int nextActivity = M.CreateAccountLogic(emailText, passwordText);

        if(nextActivity == 0) accountAddedToast.show();
        if(nextActivity == 1){startActivity(mainActivityIntent);} //send to new activity
        else if(nextActivity == 2) accountAlreadyHereToast.show();
    }

    public void SignInButton(View view){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);

        Button button = (Button) findViewById(R.id.sign_in);
        EditText eText = (EditText)findViewById(R.id.edit_Text_EmailAddress);
        EditText pText = (EditText)findViewById(R.id.edit_Text_Password);

        Context context = getApplicationContext();
        String accountNotFound = "Account not found";

        int duration = Toast.LENGTH_SHORT;
        Toast accountNotFoundToast = Toast.makeText(context, accountNotFound, duration);

        String emailText = eText.getText().toString();
        String passwordText = pText.getText().toString();

        System.out.println("Added Email: "+emailText+'\n'+"\tAdded Password: "+passwordText);

        int nextActivity = M.SignInLogic(emailText, passwordText);

        if(nextActivity == 0) accountNotFoundToast.show();
        if(nextActivity == 1){startActivity(mainActivityIntent);} //send to new activity

    }
}



