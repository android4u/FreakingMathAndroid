package com.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.config.PrefStore;

public class GameOver extends Activity {
    ImageView newGameBtn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gameover);

        int highScore = getIntent().getIntExtra("score", 0);

        TextView currentScoreTxt = (TextView) findViewById(R.id.curent_score);
        TextView bestScoreTxt  = (TextView) findViewById(R.id.best_score);
        newGameBtn = (ImageView) findViewById(R.id.play_btn);

        currentScoreTxt.setText(highScore + "");
        bestScoreTxt.setText(PrefStore.getMaxScore() + "");

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, MyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        newGameBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    newGameBtn.setBackgroundResource(R.drawable.play_press);
                } else if (action == MotionEvent.ACTION_UP) {
                    newGameBtn.setBackgroundResource(R.drawable.play);
                }
                return false;
            }
        });
    }
}