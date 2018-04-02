package com.sorinmarti.sphinx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.Question;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizLibrary;

public class QuestionActivity extends AppCompatActivity implements QuizTypeAnswerFragment.OnFragmentInteractionListener {

    Quiz quiz;
    Question question;

    TextView txtProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtProgress = (TextView)findViewById(R.id.txtQuestionXofY);

        Intent intent = getIntent();
        String quizToLoad = intent.getStringExtra(QuizSelectionActivity.QUIZ_TO_LOAD);

        quiz = QuizLibrary.getInstance().getQuiz(quizToLoad);
        QuizLibrary.getInstance().startNewQuizStatistics(quizToLoad);

        setTitle(quiz.getQuizTitle());

        showNextQuestion();
    }

    private void showNextQuestion() {
        question = quiz.getNextQuestion();
        if(question==null) {    // Quiz has ended: show stats
            Intent intent = new Intent(this, StatisticsActivity.class);
            intent.putExtra(StatisticsActivity.QUIZ_STATISTICS, quiz.getFilename());
            startActivity(intent);
            return;
        }

        // Valid question; which question type?
        QuizTypeQuestionFragment questionFragment = QuizTypeQuestionFragment.newInstance(question, getBaseContext());

        // Valid question; which answer type?
        QuizTypeAnswerFragment answerFragment = QuizTypeAnswerFragment.newInstance(question);

        // 1st Element: progress in quiz
        txtProgress.setText( "Question "+quiz.getCurrentQuestionNumber()+" of "+quiz.getTotalQuestionNumber() );

        // 2nd Element: the question
        // --> remove: txtQuestion.setText( question.getQuestionText() );
        FragmentTransaction questionFt = getSupportFragmentManager().beginTransaction();
        questionFt.replace(R.id.question_area, questionFragment);
        questionFt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        questionFt.addToBackStack(null);
        questionFt.commit();

        // 3rd Element: the answer
        FragmentTransaction answerFt = getSupportFragmentManager().beginTransaction();
        answerFt.replace(R.id.answer_area, answerFragment);
        answerFt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        answerFt.addToBackStack(null);
        answerFt.commit();
    }

    @Override
    public void questionAnswered() {
        continueQuiz();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Quiz")
                .setMessage("Do you really want to quit this quiz?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(QuestionActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    /*
    @Override
    public QuizStatistics.Result answerTheQuestion(Answer answer) {
        QuizStatistics.Result result = question.solveQuestion(answer);
        QuizLibrary.getInstance().getCurrentQuizStatistics().addQuestionResult(question, result);
        continueQuiz();
        return result;
    }

    @Override
    public QuizStatistics.Result answerTheQuestion(String answer) {
        QuizStatistics.Result result = question.solveQuestion(answer);
        QuizLibrary.getInstance().getCurrentQuizStatistics().addQuestionResult(question, result);
        continueQuiz();
        return result;
    }
    */

    private void continueQuiz() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNextQuestion();
            }
        }, 2500);
    }
}
