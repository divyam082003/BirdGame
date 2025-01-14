package com.gd.birdgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gd.birdgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mainBinding;

    Animation animation;
    MediaPlayer mediaPlayer;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_animation);

        mainBinding.chickenBird.setAnimation(animation);
        mainBinding.redBee.setAnimation(animation);
        mainBinding.greenBee.setAnimation(animation);
        mainBinding.yellowBee.setAnimation  (animation);
        mainBinding.coin.setAnimation(animation);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.gamemusic);
        mediaPlayer.start();

        mainBinding.volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (!status){
                     mediaPlayer.setVolume(0,0);
                     mainBinding.volume.setImageResource(R.drawable.volume_off);
                     status =true;
                 }
                 else {
                     mediaPlayer.setVolume(1,1);
                     mainBinding.volume.setImageResource(R.drawable.volume_on);
                     status =false ;
                 }
            }
        });

        mainBinding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                mainBinding.volume.setImageResource(R.drawable.volume_on);
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.reset();
    }
}