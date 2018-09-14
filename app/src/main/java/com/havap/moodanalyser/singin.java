package com.havap.moodanalyser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class singin extends Fragment {

    public singin() {
    }

    EditText e1,e2;
    //Context ct;
    Button b1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_singin, container, false);
        e1=(EditText)view.findViewById(R.id.useremail);
        e2=(EditText)view.findViewById(R.id.userpassword);
        b1=(Button)view.findViewById(R.id.userlogin);
        mAuth=FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(getContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if( user != null )
                {
                    Intent moveToHome = new Intent(getContext(),MainActivity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity( moveToHome );
                }

            }
        };

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void loginUser() {
        mProgressDialog.setMessage("Please wait Logging in");

        String userEmail, userPassword;

        userEmail = e1.getText().toString().trim();
        userPassword = e2.getText().toString().trim();

        if( !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword))
        {
            mProgressDialog.show();
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if( task.isSuccessful())
                    {

                        mProgressDialog.dismiss();
                        Intent moveToHome = new Intent(getContext(), MainActivity.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);

                    }else
                    {

                        Toast.makeText(getContext(), "Unable to login user", Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();

                    }

                }
            });

        }else
        {

            Toast.makeText(getContext(), "Please enter user email and password", Toast.LENGTH_LONG).show();
            mProgressDialog.dismiss();

        }

    }

}
