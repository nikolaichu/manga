package belstu.diploma.mobile.mangabook.ui.enter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText loginEditText;
    EditText passwordEditText;
    EditText mailEditText;

    private FirebaseAuth mAuth;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        loginEditText = findViewById(R.id.registrationActivity_login_EditText);
        passwordEditText = findViewById(R.id.registrationActivity_password_EditText);
        mailEditText = findViewById(R.id.registrationActivity_mail_EditText);

        registerButton = findViewById(R.id.registrationActivity_registration_Button);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
    }


    public void signUp_onClick(View view) {
        mAuth.createUserWithEmailAndPassword(mailEditText.getText().toString(),
                passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(RegistrationActivity.this, "User is already exists or invalid password or login", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}