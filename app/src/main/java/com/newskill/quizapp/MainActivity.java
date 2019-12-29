package com.newskill.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textQuestion;
    private Button trueButton;
    private Button falseButton;
    private int questionIndex;
    private ProgressBar quizProgressBar;
    private TextView remainingQuestions;
    private short userScore;

    private QuizModal[] quizQuestions = new QuizModal[] {
      new QuizModal(R.string.q1,false),
      new QuizModal(R.string.q2,false),
      new QuizModal(R.string.q3,true),
      new QuizModal(R.string.q4,true),
      new QuizModal(R.string.q5,false),
      new QuizModal(R.string.q6,false),
      new QuizModal(R.string.q7,true),
      new QuizModal(R.string.q8,true),
      new QuizModal(R.string.q9,false),
      new QuizModal(R.string.q10,true)
};
    final int USER_PROGRESS=(int)Math.ceil(100.0/quizQuestions.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textQuestion=findViewById(R.id.textQuestion);

        trueButton = findViewById(R.id.trueButton);

        falseButton = findViewById(R.id.falseButton);

        quizProgressBar = findViewById(R.id.quizProgressBar);

        remainingQuestions = findViewById(R.id.remainingQuestions);

        QuizModal questions = quizQuestions[questionIndex];

        textQuestion.setText(questions.getQuestion());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluation(true);

                Log.i("Listener","True Button is tapped");

                changeQuestion();
            }
        };

        trueButton.setOnClickListener(onClickListener);

        View.OnClickListener onClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluation(false);

                Log.i("Listener","False Button is tapped");

                changeQuestion();
            }
        };

        falseButton.setOnClickListener(onClickListener1);
    }
    private void changeQuestion(){
        questionIndex=(++questionIndex)%10;
        if(questionIndex==0)
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("The quiz is done");
            alertDialog.setMessage("Congratulations, you scored "+userScore);
            alertDialog.setPositiveButton("Finish the quiz.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
        });
            alertDialog.show();
        }
        textQuestion.setText(quizQuestions[questionIndex].getQuestion());
        quizProgressBar.incrementProgressBy(USER_PROGRESS);
        remainingQuestions.setText(userScore+"");
    }

    private void evaluation(boolean userGuess)
    {
        boolean currentAnswer=quizQuestions[questionIndex].isAnswer();
        if(userGuess==currentAnswer) {
            Toast.makeText(getApplicationContext(), "Right Answer!", Toast.LENGTH_SHORT).show();
            userScore++;
        }
        else
            Toast.makeText(getApplicationContext(),"You can do better!",Toast.LENGTH_SHORT).show();
    }


}
