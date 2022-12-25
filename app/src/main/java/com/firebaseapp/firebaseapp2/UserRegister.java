package com.firebaseapp.firebaseapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity {

    EditText txtUserName,txtEmail,txtContactNo, txtPassword, txtCPassword;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        txtUserName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtContactNo = findViewById(R.id.txtConactNo);
        txtPassword = findViewById(R.id.txtPassword);
        txtCPassword = findViewById(R.id.txtCPassword);
        btnSave = findViewById(R.id.btnSave);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String TAG = "KTTAG";

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.e(TAG, "onComplete: User Created as " + user.getDisplayName());
                                }

                            }
                        });
            }
        });

    }
}