package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<QuizQuestion> quizQuestionList = null;
    QuizQuestion currentQuestion = null;
    int currentQuestionNumber = 1;

    private int currentScore = 0;
    private int maxScore = 0;

    TextView textViewQuestionTitle = null;
    TextView textViewQuestion = null;
    TextView textViewScore = null;

    RadioGroup radioGroupQuestion = null;
    RadioButton radioButtonA = null;
    RadioButton radioButtonB = null;
    RadioButton radioButtonC = null;
    RadioButton radioButtonD = null;

    Button buttonSubmit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize widgets.
        this.textViewQuestionTitle = (TextView)findViewById(R.id.QuestionNumber);    // The question title
        this.textViewQuestion = (TextView)findViewById(R.id.Question);              // The question asked.
                           // The current score.

        // Intialize the radio buttons for question multiple choice answers.
        radioGroupQuestion = (RadioGroup)findViewById(R.id.RadioGroup1);             // Create a group for radio buttons.
        radioButtonA = (RadioButton)findViewById(R.id.RadioButtonA);
        radioButtonB = (RadioButton)findViewById(R.id.RadioButtonB);
        radioButtonC = (RadioButton)findViewById(R.id.RadioButtonC);
        radioButtonD = (RadioButton)findViewById(R.id.RadioButtonD);

        // Initialize the submit question answer along with the callback function.
        buttonSubmit = (Button)findViewById(R.id.ButtonSubmit);
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateAnswer()) {
                    if(currentQuestionNumber < maxScore) {

                        currentQuestionNumber = currentQuestionNumber + 1;
                        currentQuestion= quizQuestionList.get(currentQuestionNumber - 1);
                        setQuestionView(currentQuestion);
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                        // You can set the reference from the quizQuestionList.
                        // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                    else {
                        Intent intent = new Intent (QuizActivity.this, ResultActivity.class);
                        intent.putExtra("Score", currentScore);
                        intent.putExtra("MaxScore", maxScore);
                        startActivity(intent);

                    }
                }
            }
        });

        // Initiate all questions first.
        this.initQuestions();

        this.currentQuestion = quizQuestionList.get(currentQuestionNumber - 1);
        // Ask use the first question when this activity loads.
        this.setQuestionView(this.currentQuestion);
    }

    private void initQuestions() {
        // Create some questions to ask the questions.

        this.quizQuestionList = new ArrayList<QuizQuestion>();  // Initialize our question array.

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Create instances (using the new QuizQuestion()) of your questions.
        // You will have to call into QuizQuestion() setters to set the follow:
        // - The question to ask.
        // - Set choice options for A, B, C, and D.
        // - Set the correct answer (so that class knows which one is correct or not).
        // - Remember to add the object to our quizQuestionList array. Hint: Use .add(...) function here.
        // NOTE: No widgets should be set in this method.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        QuizQuestion question1 = new QuizQuestion();
        question1.setQuestion("How many moons does Jupiter have");
        question1.setChoiceA("109");
        question1.setChoiceB("26");
        question1.setChoiceC("79");
        question1.setChoiceD("59");
        question1.setCorrectAnswer("79");
        quizQuestionList.add(question1);

        QuizQuestion question2 = new QuizQuestion();
        question2.setQuestion("How old is the Sun");
        question2.setChoiceA("5.901 Billion Years");
        question2.setChoiceB("9.147 Billion Years");
        question2.setChoiceC("7.412 Billion Years");
        question2.setChoiceD("4.603 Billion Years");
        question2.setCorrectAnswer("4.603 Billion Years");
        quizQuestionList.add(question2);
        QuizQuestion question3 = new QuizQuestion();
        question3.setQuestion("What is the radius of Pluto");
        question3.setChoiceA("529.54 Miles");
        question3.setChoiceB("738.38 Miles");
        question3.setChoiceC("852.48 Miles");
        question3.setChoiceD("694.53 Miles");
        question3.setCorrectAnswer("738.38 Miles");
        quizQuestionList.add(question3);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        //this.currentQuestion = /*Your first question*/;
//        this.currentQuestion = quizQuestionList.get(0);
        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
        this.maxScore = this.quizQuestionList.size();
        this.currentScore = 0;
    }

    private void setQuestionView(QuizQuestion quizQuestion) {
        if(quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroupQuestion.clearCheck();
     this.textViewQuestion.setText( currentQuestion.getQuestion());
     this.radioButtonA.setText(currentQuestion.getChoiceA());
     this.radioButtonB.setText(currentQuestion.getChoiceB());
     this.radioButtonC.setText(currentQuestion.getChoiceC());
     this.radioButtonD.setText(currentQuestion.getChoiceD());
        // Loads the current question view.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set the UI view (all your widgets) with the current QuizQuestion passed in.
        // Hint: Use your getters from the QuizQuestion class to get the values stored there.
        // Set the following widget text:
        // - The question text (i.e. Question #1).
        // - Set the question to ask.
        // - Set all for radio button text.
        // - Set the score view with the current score (remeber to convert integer to string).
        //   Example: Score: 2
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private boolean validateAnswer() {
        // Validate the current answer selected.
        int selectedButtonId = this.radioGroupQuestion.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.isCorrectAnswer(answerSelectedStr)) {
                // Answer is correct.
                Log.d("ANSWER: ", "Correct");
                currentScore++;
            }
            else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true; // Allow to continue to next question.
        }
        else {
            // No answer selected.
            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}


