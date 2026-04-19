package br.edu.ifsulminas.mch.pdm.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private  static final String USER_NAME = "emerson";
    private  static final String PW = "admin";
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonForgotPW;

    private EditText editTextUser;
    private EditText editTextPW;

    private ActivityResultLauncher<String> startWelcomeActLauncher =
            registerForActivityResult(new SimpleContract(),
                    result -> {
                        if (result == null || "".equals(result))
                            return;

                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

                        if (TempDoProjeto.imagem != null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(
                                    TempDoProjeto.imagem, 0, TempDoProjeto.imagem.length
                            );

                            ImageView imageView = findViewById(R.id.imageViewResult);
                            if (imageView != null) {
                                imageView.setImageBitmap(bitmap);
                            }

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
    }

}