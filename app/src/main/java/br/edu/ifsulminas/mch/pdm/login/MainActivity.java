package br.edu.ifsulminas.mch.pdm.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private  static final String USER_NAME = "emerson";
    private  static final String PW = "admin";
    private  static final String LOG_TAG = "login_activity_main";
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonForgotPW;

    private EditText editTextUser;
    private EditText editTextPW;

    private ActivityResultLauncher<String> startWelcomeActLauncher =
            registerForActivityResult(new SimpleContract(),
                    new ActivityResultCallback<String>() {
                        @Override
                        public void onActivityResult(String result) {
                            if (result == null || "".equals(result))
                                return;
                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonLogin = findViewById(R.id.buttonLoginId);
        buttonRegister = findViewById(R.id.buttonRegisterId);
        buttonForgotPW = findViewById(R.id.buttonForgotPWId);
        editTextUser = findViewById(R.id.textInputEditTextUserId);
        editTextPW = findViewById(R.id.textInputEditTextPWId);

        // Classe anônima
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = editTextUser.getText().toString();
                String userPW = editTextPW.getText().toString();

                if (USER_NAME.equals(userName) && PW.equals(userPW)){
                    // Via laucher
                    startWelcomeActLauncher.launch(userName);
                } else {
                    Toast toast = Toast.makeText(getBaseContext(), R.string.invalid_user_or_pw, Toast.LENGTH_LONG);
                    toast.show();
                    editTextUser.requestFocus();
                }

            }
        });

        // Classe externa
        buttonRegister.setOnClickListener(new RegisterClickListener());

        // Lambdas com interfaces SUM
        buttonForgotPW.setOnClickListener((View view) -> {
            Toast.makeText(view.getContext(), R.string.button_forgot_pw_clicked, Toast.LENGTH_LONG).show();
        });

        Log.d(LOG_TAG, "Método onCreate da MainActivity executou com sucesso!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "ActivityMain foi fechada.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "ActivityMain passou pelo onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "ActivityMain passou pelo onRestart.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "ActivityMain passou pelo onResume.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "ActivityMain passou pelo onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "ActivityMain passou pelo onStop.");
    }
}