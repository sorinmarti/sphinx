package com.sorinmarti.sphinx;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sorinmarti.sphinx.quiz.Question;


public abstract class QuizTypeQuestionFragment extends Fragment {
    private Context context;
    Question question;

    public QuizTypeQuestionFragment() {
        // Required empty public constructor
    }

    private void setQuestion(Question question) {
        this.question = question;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public static QuizTypeQuestionFragment newInstance(Question question, Context context) {
        QuizTypeQuestionFragment fragment = null;
        switch (question.getQuestionType()) {
            case PLAIN:
                fragment = new QuizTypeQuestionFragmentPlain();
                break;
            case PICTURED:
                fragment = new QuizTypeQuestionFragmentPictured();
                break;
        }

        fragment.setQuestion(question);
        fragment.setContext(context);

        return fragment;
    }

    protected abstract void setUpFragment(View view, Context context);
    protected abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getLayoutId(), container, false);

        setUpFragment(fragmentView, context);
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
