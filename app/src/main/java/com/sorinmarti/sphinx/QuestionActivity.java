package com.sorinmarti.sphinx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sorinmarti.sphinx.quiz.Question;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizLibrary;

public class QuestionActivity extends AppCompatActivity implements QuizTypeAnswerFragment.OnFragmentInteractionListener, MenuFragment.OnMenuFragmentInteraction {

    private Quiz quiz;
    private Question question;

    private TextView txtProgress;
    private ProgressBar countDownBar;
    private AsyncTask<Integer, Integer, Integer> countdownTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtProgress = findViewById(R.id.txtQuestionXofY);
        countDownBar = findViewById(R.id.countdownBar);

        Intent intent = getIntent();
        String quizToLoad = intent.getStringExtra(QuizSelectionActivity.QUIZ_TO_LOAD);

        MenuActions.commitMenuTransaction(  this, R.id.questionMenuFragment, false, false, true );

        quiz = QuizLibrary.getInstance().getQuiz(quizToLoad);
        QuizLibrary.getInstance().startNewQuizStatistics(quizToLoad);

        setTitle(quiz.getQuizTitle());

        showNextQuestion();
    }

    private void showNextQuestion() {
        question = quiz.getNextQuestion();
        if(question==null) {    // Quiz has ended: show stats
            Intent intent = new Intent(this, EndQuizActivity.class);
            intent.putExtra(EndQuizActivity.QUIZ_STATISTICS, quiz.getFilename());
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
            return;
        }

        // Valid question: create countdown
        countdownTask = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPostExecute(Integer result) {
                //Toast.makeText(QuestionActivity.this, "Out of time!", Toast.LENGTH_SHORT).show();
                showNextQuestion();
                return;
            }

            @Override
            protected Integer doInBackground(Integer... integers) {
                int seconds = 5;
                for(int i=0;i<(seconds*100);i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        return null;
                    }
                    countDownBar.setProgress( i );
                }
                return 0;
            }
        };
        countdownTask.execute();


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
        questionFt.commit();

        // 3rd Element: the answer
        FragmentTransaction answerFt = getSupportFragmentManager().beginTransaction();
        answerFt.replace(R.id.answer_area, answerFragment);
        answerFt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        answerFt.commit();
    }

    @Override
    public void questionAnswered() {
        countdownTask.cancel(true);
        continueQuiz();
    }

    @Override
    public void onBackPressed() {
        onSphinxMenuPressed();
    }

    private void continueQuiz() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNextQuestion();
            }
        }, 2500);
    }

    @Override
    public void onSphinxBackPressed() {
        onSphinxMenuPressed();
    }

    @Override
    public void onSphinxExitPressed() {
        MenuActions.quitGame( this );
    }

    @Override
    public void onSphinxMenuPressed() {
        //TODO --> Save quiz to recontinue later
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_quit_quiz_title)
                .setMessage(R.string.dialog_quit_quiz_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MenuActions.goToMenu( QuestionActivity.this );
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
}
