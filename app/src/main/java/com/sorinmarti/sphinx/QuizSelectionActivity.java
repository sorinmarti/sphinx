package com.sorinmarti.sphinx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;

import com.sorinmarti.sphinx.listview.ActionSlideExpandableListView;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizLibrary;

public class QuizSelectionActivity extends AppCompatActivity {

    ActionSlideExpandableListView listView;
    public static final String QUIZ_TO_LOAD = "QUIZ_TO_LOAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

        listView = (ActionSlideExpandableListView)this.findViewById(R.id.listQuizzes);
        listView.setAdapter(buildDummyData());

        /*
        Quiz[] allQuizzes = QuizLibrary.getInstance().getAllQuizzes();

        final QuizListArrayAdapter adapter = new QuizListArrayAdapter(this, allQuizzes);
        listView.setAdapter(adapter);
        */

        listView.setItemActionListener(new ActionSlideExpandableListView.OnActionClickListener() {

            @Override
            public void onClick(View listView, View buttonview, int position) {
                loadQuiz(position);
            }

            // note that we also add 1 or more ids to the setItemActionListener
            // this is needed in order for the listview to discover the buttons
        }, R.id.buttonA);
    }

    private void loadQuiz(int position) {
        Quiz quiz = (Quiz) listView.getAdapter().getItem(position);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(QUIZ_TO_LOAD, quiz.getFilename());
        startActivity(intent);

    }

    public ListAdapter buildDummyData() {
        final int SIZE = 20;
        String[] values = new String[SIZE];
        for(int i=0;i<SIZE;i++) {
            values[i] = "Item "+i;
        }

        Quiz[] allQuizzes = QuizLibrary.getInstance().getAllQuizzes();
        return new QuizListArrayAdapter(
                this,
                R.layout.expandable_list_item,
                R.id.quiz_title,
                allQuizzes
        );
    }

}
