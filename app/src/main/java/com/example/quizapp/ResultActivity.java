package com.example.quizapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    int curScore = 0;
    int maxScore = 0;

    TextView textViewScore = null;
    TextView textViewResultDescription = null;

    Button buttonPlayAgain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityresult);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
        return;
    }
        curScore = extras.getInt("Score");
        maxScore = extras.getInt("MaxScore");


        textViewScore = (TextView)findViewById(R.id.NumberCorrect);
        textViewScore.setText(String.valueOf(curScore) + "/"+ String.valueOf(maxScore));
        textViewResultDescription = (TextView)findViewById(R.id.ScoreDescription);
        textViewResultDescription.setText("You answered " + getPercentage(curScore, maxScore)+ " of the questions correctly");
        buttonPlayAgain = (Button)findViewById(R.id.buttonPlayAgain);
        this.buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                ////////////////////////////////////////////////////////////////////////////////////////
                // TO-DO: Make a play again button that will redirect the user back to the main activity.
                ////////////////////////////////////////////////////////////////////////////////////////
            }
        });
    }

    private String getPercentage(int current, int total) {
        float fPercent = (float)current/(float)total;
        return Integer.toString((int)Math.ceil((fPercent) * 100)) + "%";
    }
}
