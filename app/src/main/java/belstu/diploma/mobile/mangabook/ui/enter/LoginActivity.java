package belstu.diploma.mobile.mangabook.ui.enter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import belstu.diploma.mobile.mangabook.FingerPrintController;
import belstu.diploma.mobile.mangabook.MainActivity;
import belstu.diploma.mobile.mangabook.R;

public class LoginActivity extends AppCompatActivity {

    EditText loginEditText;
    EditText passwordEditText;
    TextView forgetPasswordTextView;

    Button signINButton;
    Button signUPButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginEditText = findViewById(R.id.loginActivity_login_EditView);
        passwordEditText = findViewById(R.id.loginActivity_password_EditView);
//        forgetPasswordTextView = findViewById(R.id.loginActivity_forgetPassword_TextView);

        signINButton = findViewById(R.id.loginActivity_signIN_Button);
        signUPButton = findViewById(R.id.loginActivity_signUP_Button);

//        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
//                startActivity(intent);
//            }
//        });


        signUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            // авторизирован
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }


    public void signUp_onClick(View view) {
        mAuth.signInWithEmailAndPassword(loginEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Wrong Login or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void fingerPrintAuthStart(View view) {
//        FingerPrintController fingerPrintService = new FingerPrintController();
//        fingerPrintService.authenticateUser(view);
//    }
}
