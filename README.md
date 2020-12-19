Приложение выполнено в рамках курсового проекта по предмету "Программное обеспечение безопасных мобильных систем".

Функциональные возможности: регистрация и авторизация с помощью Firebase (аутентификация по связке электропочта+пароль), создание, изменение, удаление и просмотр книг, переводчиков и авторов.

Для защиты приложения используется несколько функций:
 - биометрическая аутентификация пользователя. Реализована с помощью дополнительной активности FingerPrintController.
 ```java
 
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
 ```
 
 - обфускация кода реализована с помощью изменения параметров файла build.gradle
 ```java
 buildTypes {
        release {
//            minifyEnabled false
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
  }
 ```
 - шифрование базы данных с помощью SQLCipher
 
 Сначала устанавливаются зависимости
 ```java
    implementation 'net.zetetic:android-database-sqlcipher:4.3.0@aar'
    implementation "androidx.sqlite:sqlite:2.0.1"
 ```
 
 Работа с базой данных была реализована в отдельном классе DatabaseUtils, при создании которого загружаются библиотеки и устанавливается пароль, который после используется для доступа к данным
 ```java
    SQLiteDatabase.loadLibs(context);
 ```
 
 ```java
     public SQLiteDatabase getReadableDatabase() {
        return (super.getReadableDatabase(password));
    }

    public SQLiteDatabase getWritableDatabase() {
        return (super.getWritableDatabase(password));
    }
 ```
 
 - запрет на создание скриншота экрана в MainActivity
  ```java
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
  ```
 
