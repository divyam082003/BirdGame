package com.gd.birdgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gd.birdgame.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding gameBinding;
    private boolean touchControl= false;
    private boolean beginControl= false;

    Runnable runnable,runnable2;
    Handler handler,handler2;

    //position
    int birdX,enemy1X,enemy2X,enemy3X,coin1X,coin2X;
    int birdY,enemy1Y,enemy2Y,enemy3Y,coin1Y,coin2Y;

    //dimension of screen
    int screenWidth,screenHeight;

    int right=3;
    int score;

    final int MAX_SCORE = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameBinding = ActivityGameBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(gameBinding.getRoot());


        gameBinding.constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gameBinding.taptoplayTV.setVisibility(View.INVISIBLE);
                 if (!beginControl){
                     beginControl=true;
                     screenWidth = (int) gameBinding.constraintLayout.getWidth();
                     screenHeight = (int) gameBinding.constraintLayout.getHeight();

                     birdX = (int) gameBinding.chickenBirdGameIV.getX();
                     birdY = (int) gameBinding.chickenBirdGameIV.getY();

                     handler = new Handler();
                     runnable = new Runnable() {
                         @Override
                         public void run() {
                             moveToBird();
                             enemyControl();
                             collisionControl();
                         }
                     };
                     handler.post(runnable);
                 }
                 else {
                     if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                         touchControl=true;
                     }
                     if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                         touchControl=false;
                     }
                 }
                return true;
            }
        });

    }

    void moveToBird(){
        if (touchControl ){
            birdY = birdY - (screenHeight/50);
        }
        else {
            birdY = birdY + (screenHeight/50);
        }

        if (birdY<=0){
            birdY =0;
        }
        if (birdY>=(screenHeight - gameBinding.chickenBirdGameIV.getHeight())){

            birdY=(screenHeight - gameBinding.chickenBirdGameIV.getHeight());
        }

        gameBinding.chickenBirdGameIV.setY(birdY);

    }

    void enemyControl(){
        gameBinding.enemy1GameIV.setVisibility(View.VISIBLE);
        gameBinding.enemy2GameIV.setVisibility(View.VISIBLE);
        gameBinding.enemy3GameIV.setVisibility(View.VISIBLE);
        gameBinding.coin1GameIV.setVisibility(View.VISIBLE);
        gameBinding.coin2GameIV.setVisibility(View.VISIBLE);

        enemy1X = enemy1X - (screenWidth/150);
        if (enemy1X<0){
            enemy1X = screenWidth +200;
            enemy1Y = (int) Math.floor(Math.random()*screenHeight);

            if (enemy1Y<=0){
                enemy1Y =0;
            }
            if (enemy1Y>=(screenHeight - gameBinding.enemy1GameIV.getHeight())){

                enemy1Y=(screenHeight - gameBinding.enemy1GameIV.getHeight());
            }
        }
        gameBinding.enemy1GameIV.setX(enemy1X);
        gameBinding.enemy1GameIV.setY(enemy1Y);

        enemy2X = enemy2X - (screenWidth/140);
        if (enemy2X<0){
            enemy2X = screenWidth +200;
            enemy2Y = (int) Math.floor(Math.random()*screenHeight);

            if (enemy2Y<=0){
                enemy2Y =0;
            }
            if (enemy2Y>=(screenHeight - gameBinding.enemy2GameIV.getHeight())){

                enemy2Y=(screenHeight - gameBinding.enemy2GameIV.getHeight());
            }
        }
        gameBinding.enemy2GameIV.setX(enemy2X);
        gameBinding.enemy2GameIV.setY(enemy2Y);

        enemy3X = enemy3X - (screenWidth/130);
        if (enemy3X<0){
            enemy3X = screenWidth +200;
            enemy3Y = (int) Math.floor(Math.random()*screenHeight);

            if (enemy3Y<=0){
                enemy3Y =0;
            }
            if (enemy3Y>=(screenHeight - gameBinding.enemy3GameIV.getHeight())){

                enemy3Y=(screenHeight - gameBinding.enemy3GameIV.getHeight());
            }
        }
        gameBinding.enemy3GameIV.setX(enemy3X);
        gameBinding.enemy3GameIV.setY(enemy3Y);

        coin1X = coin1X - (screenWidth/120);
        if (coin1X<0){
            coin1X = screenWidth +200;
            coin1Y = (int) Math.floor(Math.random()*screenHeight);

            if (coin1Y<=0){
                coin1Y =0;
            }
            if (coin1Y>=(screenHeight - gameBinding.coin1GameIV.getHeight())){

                coin1Y=(screenHeight - gameBinding.coin1GameIV.getHeight());
            }
        }
        gameBinding.coin1GameIV.setX(coin1X);
        gameBinding.coin1GameIV.setY(coin1Y);

        coin2X = coin2X - (screenWidth/110);
        if (coin2X<0){
            coin2X = screenWidth +200;
            coin2Y = (int) Math.floor(Math.random()*screenHeight);

            if (coin2Y<=0){
                coin2Y =0;
            }
            if (coin2Y>=(screenHeight - gameBinding.coin2GameIV.getHeight())){

                coin2Y=(screenHeight - gameBinding.coin2GameIV.getHeight());
            }
        }
        gameBinding.coin2GameIV.setX(coin2X);
        gameBinding.coin2GameIV.setY(coin2Y);

    }

    void collisionControl(){
        
        int centreEnemy1X = enemy1X + gameBinding.enemy1GameIV.getWidth() / 2;
        int centreEnemy1Y = enemy1Y + gameBinding.enemy1GameIV.getHeight() / 2;
        if (centreEnemy1X >= birdX
                && centreEnemy1X <= (birdX + gameBinding.chickenBirdGameIV.getWidth())
                && centreEnemy1Y >= birdY
                && centreEnemy1Y <= (birdY + gameBinding.chickenBirdGameIV.getHeight())) {
            enemy1X = screenWidth+200;
            right--;
        }

        int centreEnemy2X = enemy2X + gameBinding.enemy2GameIV.getWidth() / 2;
        int centreEnemy2Y = enemy2Y + gameBinding.enemy2GameIV.getHeight() / 2;
        if (centreEnemy2X >= birdX
                && centreEnemy2X <= (birdX + gameBinding.chickenBirdGameIV.getWidth())
                && centreEnemy2Y >= birdY
                && centreEnemy2Y <= (birdY + gameBinding.chickenBirdGameIV.getHeight())) {
            enemy2X = screenWidth+200;
            right--;
        }

        int centreEnemy3X = enemy3X + gameBinding.enemy3GameIV.getWidth() / 2;
        int centreEnemy3Y = enemy3Y + gameBinding.enemy3GameIV.getHeight() / 2;
        if (centreEnemy3X >= birdX
                && centreEnemy3X <= (birdX + gameBinding.chickenBirdGameIV.getWidth())
                && centreEnemy3Y >= birdY
                && centreEnemy3Y <= (birdY + gameBinding.chickenBirdGameIV.getHeight())) {
            enemy3X = screenWidth+200;
            right--;
        }

        int centreCoin1X = coin1X + gameBinding.coin1GameIV.getWidth() / 2;
        int centreCoin1Y = coin1Y + gameBinding.coin1GameIV.getHeight() / 2;
        if (centreCoin1X >= birdX
                && centreCoin1X <= (birdX + gameBinding.chickenBirdGameIV.getWidth())
                && centreCoin1Y >= birdY
                && centreCoin1Y <= (birdY + gameBinding.chickenBirdGameIV.getHeight())) {
            coin1X = screenWidth+200;
            score+=10;
            gameBinding.scoreTV.setText(String.valueOf(score));
        }

        int centreCoin2X = coin2X + gameBinding.coin2GameIV.getWidth() / 2;
        int centreCoin2Y = coin2Y + gameBinding.coin2GameIV.getHeight() / 2;
        if (centreCoin2X >= birdX
                && centreCoin2X <= (birdX + gameBinding.chickenBirdGameIV.getWidth())
                && centreCoin2Y >= birdY
                && centreCoin2Y <= (birdY + gameBinding.chickenBirdGameIV.getHeight())) {
            coin2X = screenWidth+200;
            score+=10;
            gameBinding.scoreTV.setText(String.valueOf(score));
        }

        if (right >0 && score<MAX_SCORE){
            if (right == 2){
                gameBinding.right1.setVisibility(View.INVISIBLE);
            }
            if (right == 1){
                gameBinding.right2.setVisibility(View.INVISIBLE);
            }
            handler.postDelayed(runnable,20);
        }
        else if (score >= MAX_SCORE) {
            handler.removeCallbacks(runnable);
            gameBinding.constraintLayout.setEnabled(false);
            gameBinding.taptoplayTV.setVisibility(View.VISIBLE);
            gameBinding.taptoplayTV.setText("Congrats. You Won!");

            gameBinding.enemy1GameIV.setVisibility(View.INVISIBLE);
            gameBinding.enemy2GameIV.setVisibility(View.INVISIBLE);
            gameBinding.enemy3GameIV.setVisibility(View.INVISIBLE);
            gameBinding.coin1GameIV.setVisibility(View.INVISIBLE);
            gameBinding.coin2GameIV.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    birdX = birdX + (screenWidth/300);
                    gameBinding.chickenBirdGameIV.setX(birdX);
                    gameBinding.chickenBirdGameIV.setY(screenHeight/2f);

                    if (birdX <= screenWidth){
                        handler2.postDelayed(runnable2,20);
                    }else {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("score",score);
                        startActivity(intent);
                        finish();
                    }
                }
            };

            handler2.post(runnable2);

        } else if (right == 0) {
            handler.removeCallbacks(runnable);
            gameBinding.right3.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }
    }
}