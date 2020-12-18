package belstu.diploma.mobile.mangabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

public class FingerPrintController extends AppCompatActivity {

    private static final String TAG = FingerPrintController.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_finger_print_controller);

//Create a thread pool with a single thread//

        Executor newExecutor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;

//Start listening for authentication events//

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override

//onAuthenticationError is called when a fatal error occurrs//

            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {

//Print a message to Logcat//

                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }

//onAuthenticationSucceeded is called when a fingerprint is matched successfully//

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

//Print a message to Logcat//

                Log.d(TAG, "Fingerprint recognised successfully");
                finish();
            }

//onAuthenticationFailed is called when the fingerprint doesn’t match//

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

//Print a message to Logcat//

                Log.d(TAG, "Fingerprint not recognised");
            }
        });

//Create the BiometricPrompt instance//

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()

//Add some text to the dialog//

                .setTitle("Сканер отпечатка пальцев")
                .setSubtitle("")
                .setDescription("Подтвердите, что вы владелец телефона")
                .setNegativeButtonText("Cancel")

//Build the dialog//

                .build();

//Assign an onClickListener to the app’s “Authentication” button//

        myBiometricPrompt.authenticate(promptInfo);

    }
}
