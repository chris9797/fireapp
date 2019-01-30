package com.example.fireapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonregister;
    private EditText editemail;
    private EditText editpassword;
    private TextView textsignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = new ProgressDialog(this);

        buttonregister = (Button) findViewById(R.id.id_regbutton);

        editemail = (EditText) findViewById(R.id.id_email);

        editpassword = (EditText) findViewById(R.id.id_password);

        textsignin = (TextView) findViewById(R.id.id_alreadyreg);

        buttonregister.setOnClickListener(this);
        textsignin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //pass is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Could not register. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
    }

    @Override
    public void onClick(View v) {
        if(v == buttonregister){
            registerUser();
        }

        if(v == textsignin){
            //will open login activity
        }
    }
}
