package com.example.spotifywrapped;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.common.PackageVerificationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.A;

public class DatabaseManager {

    private static FirebaseAuth mAuth;

    public DatabaseManager() {
    } // DatabaseManager

    public static void setFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
    } // setFirebaseAuth

    public static void createNewAccount(String username, String password, Activity activity) {
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(activity, "Registering user successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Registration failed!", Toast.LENGTH_SHORT).show();
                } // if
            }
        });
    } // registerUser

    public static boolean isLoggedin() {
        if(mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        } // if
    } // isLoggedin

} // DatabaseManager
