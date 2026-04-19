package br.edu.ifsulminas.mch.pdm.login;

import android.app.Activity;
import android.app.ComponentCaller;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.MessageFormat;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity {

    private  static final String LOG_TAG = "welcome_activity";

    private Button buttonTakePicture;
    private ImageView imageViewPicture;

    private  Intent resultIntent;

    private static final int REQUEST_PICTURE_CODE = 321552105;

    private static final String RESULT_KEY = "resultado";
    private static final String RESULT_IMG = "imageView_volta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_welcome_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentQueChamou = getIntent();
        String userName = intentQueChamou.getStringExtra("user_name");

        String finalmessage = MessageFormat.format("{0} {1}!",
                getText(R.string.welcome_message), userName);

        View viewLayout = findViewById(R.id.activity_welcome_id);
        Snackbar snackbar = Snackbar.make(viewLayout, finalmessage, Snackbar.LENGTH_LONG);
        snackbar.show();

        buttonTakePicture = findViewById(R.id.buttonTakePictureId);
        imageViewPicture = findViewById(R.id.imageViewPictureId);

        buttonTakePicture.setOnClickListener((View view) ->{
            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicIntent, REQUEST_PICTURE_CODE);
        });

        resultIntent = new Intent();
        resultIntent.putExtra("resultado","Não bateu a foto");
        setResult(Activity.RESULT_OK, resultIntent);

        Log.i(LOG_TAG, "WelcomeActivity foi criada.");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == REQUEST_PICTURE_CODE){
            if (data != null) {
                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap image = (Bitmap) extras.get("data");
                    imageViewPicture.setImageBitmap(image);

                    resultIntent.putExtra(RESULT_KEY,"Bateu a foto");
                    resultIntent.putExtra(RESULT_IMG,image);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "WelcomeActivity foi fechada.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "WelcomeActivity passou pelo onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "WelcomeActivity passou pelo onRestart.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "WelcomeActivity passou pelo onResume.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "WelcomeActivity passou pelo onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "WelcomeActivity passou pelo onStop.");
    }
}






