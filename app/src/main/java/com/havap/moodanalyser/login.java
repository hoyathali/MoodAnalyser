package com.havap.moodanalyser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends Fragment {
    EditText e1,e2,e3;
    Button b1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog mProgressDialog;
    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_login, container, false);
        e1=(EditText)view.findViewById(R.id.useremail);
        e2=(EditText)view.findViewById(R.id.userpassword);
        e3=(EditText)view.findViewById(R.id.userconfirmpassword);
        b1=(Button)view.findViewById(R.id.create);
        mAuth=FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(getContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if( user != null )
                {

                /*    Intent moveToHome = new Intent(getContext(),MainActivity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity( moveToHome );
*/

                }

            }
        };

    return view;
    }
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop(){
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createuser() {
        mProgressDialog.setMessage("Creating account patience please");

        String emailUser, passUser;

        emailUser = e1.getText().toString().trim();
        passUser = e2.getText().toString().trim();

        if( !TextUtils.isEmpty(emailUser) && !TextUtils.isEmpty(passUser))
        {
            if(e2.getText().toString().equals(e3.getText().toString())) {
                mProgressDialog.show();
                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getContext(), "Account created Success", Toast.LENGTH_LONG).show();
                            mProgressDialog.dismiss();

                            Intent moveToHome = new Intent(getContext(), MainActivity.class);
                            moveToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(moveToHome);

                        } else {
                            Toast.makeText(getContext(), "Account creation failed", Toast.LENGTH_LONG).show();
                            mProgressDialog.dismiss();

                        }
                    }

                });
            }else
            {
                e3.setError("Not matching ");
            }


        }

    }

}
