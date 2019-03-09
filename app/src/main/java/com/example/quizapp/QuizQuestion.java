package com.example.quizapp;

public class QuizQuestion {
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String CorrectAnswer;

    QuizQuestion() {
    }

    protected void setQuestion(String questionText) {
        question = questionText;
    }


    protected void setChoiceA(String answer) {
        choiceA = answer;
    }

    protected void setChoiceB(String answer) {
        choiceB = answer;
    }

    protected void setChoiceC(String answer) {
        choiceC = answer;
    }

    protected void setChoiceD(String answer) {
        choiceD = answer;
    }

    protected void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    protected String getQuestion() {
        return question;

    }

    protected String getChoiceA() {
        return choiceA;
    }

    protected String getChoiceB() {
        return choiceB;
    }

    protected String getChoiceC() {
        return choiceC;
    }

    protected String getChoiceD() {
        return choiceD;
    }

    protected boolean isCorrectAnswer(String answer) {
        if (this.CorrectAnswer == null) {
            return false;
        } else if (this.CorrectAnswer == answer) {
            return true;
        }
        else {
            return false;
        }
    }


}



