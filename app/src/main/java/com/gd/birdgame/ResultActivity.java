package com.gd.birdgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gd.birdgame.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding resultBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(resultBinding.getRoot());

        int score = getIntent().getIntExtra("score",0);

        resultBinding.scoreResultTV.setText("Your Score: "+score);

        resultBinding.playAgainBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Help The Innocent Bird");
        builder.setMessage("Are You Sure to Quit Game?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }
}