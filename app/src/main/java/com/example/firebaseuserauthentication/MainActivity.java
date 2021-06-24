package com.example.firebaseuserauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextInputLayout t1,t2;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       t1=(TextInputLayout)findViewById(R.id.email);
       t2=(TextInputLayout)findViewById(R.id.pwd);
       progressBar=findViewById(R.id.progressBar2);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(t1.getEditText().getText().toString()) &&TextUtils.isEmpty(t2.getEditText().getText().toString())){
                    Toast.makeText(MainActivity.this,"fileds are empty",Toast.LENGTH_LONG).show();
                }else {
                    SignInUphere();
                }
            }
        });

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void SignInUphere() {

       progressBar.setVisibility(View.VISIBLE);
        String mail=t1.getEditText().getText().toString();
        String password=t2.getEditText().getText().toString();
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(mail,password)
               .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                          progressBar.setVisibility(View.INVISIBLE);
                          t1.getEditText().setText("");
                           t2.getEditText().setText("");
                           Toast.makeText(MainActivity.this, "Authentication Successful..!!",
                                   Toast.LENGTH_SHORT).show();
                       }
                       else{
                           progressBar.setVisibility(View.INVISIBLE);
                           t1.getEditText().setText("");
                           t2.getEditText().setText("");
                           Toast.makeText(MainActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();

                       }



                   }
               });

    }

}