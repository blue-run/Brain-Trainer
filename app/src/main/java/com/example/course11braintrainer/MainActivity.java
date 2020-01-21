package com.example.course11braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    View view;
    Button button,  button1, button2, button3, button4,exit,play;
    int a,b,score=0,outOf=0;
    TextView resultTextView,scoreTextView,questionTextView,timeTextView,introTextView;CountDownTimer countDownTimer;
    ArrayList<Integer> answers=new ArrayList<>();

    public void onGo(View view){
        button.setVisibility(View.INVISIBLE);
        introTextView.setVisibility(View.INVISIBLE);
        timeTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
        exit.setVisibility(View.VISIBLE);
        nextQuestion(view);
        countDownTimer.start();

    }

    public void chooseAnswer(View view){
        resultTextView.setVisibility(view.VISIBLE);
        outOf++;
        Button getButton=findViewById(view.getId());
        if(getButton.getText().equals(String.valueOf(a+b))){
                score++;
            resultTextView.setText("CORRECT !");
        }else{
            resultTextView.setText("WRONG :(");
        }
        scoreTextView.setText(String.valueOf(score)+"/"+String.valueOf(outOf));
        nextQuestion(view);

    }


    public void nextQuestion(View view){
        Random random=new Random();
        a=random.nextInt(21);
        b=random.nextInt(21);

        questionTextView.setText(String.valueOf(a)+" + "+String.valueOf(b) );
        answers.clear();

        int locationOfAnswer=random.nextInt(4);

        for(int i=0;i<4;i++){
            if(i==locationOfAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer=random.nextInt(42);
                if(wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(42);
                }
                answers.add(wrongAnswer);
            }
        }

        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));
    }
    public void disabled(View view) {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }

    public void playAgain(View view){
        score=0;outOf=0;
        scoreTextView.setText("0"+"/"+"0");
        resultTextView.setVisibility(View.INVISIBLE);
        nextQuestion(view);
        countDownTimer.start();
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

    }

    public void Exit(View view){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Do You Want To Exit The Game ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=alertDialog.create();
        alert.setTitle("Alert!");
        alert.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        introTextView=findViewById(R.id.introTextView);


         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button3);
         button4=findViewById(R.id.button4);
         play=findViewById(R.id.play);
         exit=findViewById(R.id.exit);

        button=findViewById(R.id.button);
        questionTextView=findViewById(R.id.questionTextView);


        resultTextView.setVisibility(View.INVISIBLE);
        timeTextView=findViewById(R.id.timeTextView);


        countDownTimer=new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("TIME'S UP!");
                resultTextView.setVisibility(View.VISIBLE);
                disabled(view);
            }
        };
    }
}
