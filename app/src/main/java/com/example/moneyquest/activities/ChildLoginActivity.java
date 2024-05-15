package com.example.moneyquest.activities;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyquest.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChildLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText editTextEmail, editTextSenha;
    ImageView imgChoro;
    TextView textErro, textChild;
    boolean isPasswordVisible = false;
    Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.LoginEdit);
        editTextSenha = findViewById(R.id.PasswordEdit);
        textErro = findViewById(R.id.textErro);
        textChild = findViewById(R.id.textChild);
        imgChoro = findViewById(R.id.imgChoro);

        textErro.setVisibility(View.INVISIBLE);
        imgChoro.setVisibility(View.INVISIBLE);

        editTextSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    public void SignIn(View view){
        String email, senha;
        email = String.valueOf(editTextEmail.getText());
        senha = String.valueOf(editTextSenha.getText());

        signIn(email, senha, view);
    }

    public void Teste(View view){
        System.out.println("Ok");
    }

    private void signIn(String email, String password, View view) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("Logado");
                            updateUI(view, user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ChildLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            errorMsg();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void reload() { }

    private void updateUI(View view, FirebaseUser user) {
        Intent i = new Intent(getApplicationContext(), TreasureActivity.class);
        startActivity(i);
    }

    private void errorMsg() {
        textChild.setVisibility(View.INVISIBLE);
        textErro.setVisibility(View.VISIBLE);
        imgChoro.setVisibility(View.VISIBLE);
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // If password is visible, hide it by setting the input type to textPassword
            editTextSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordVisible = false;
        } else {
            // If password is hidden, show it by setting the input type to text
            editTextSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isPasswordVisible = true;
        }

        // Move the cursor to the end of the text to maintain the current position
        editTextSenha.setSelection(editTextSenha.getText().length());
    }

}