package com.sorinmarti.sphinx;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sorinmarti.sphinx.quiz.Question;


public abstract class QuizTypeAnswerFragment extends Fragment {
    protected Question question;

    private OnFragmentInteractionListener mListener;

    public QuizTypeAnswerFragment() {
        // Required empty public constructor
    }

    private void setQuestion(Question question) {
        this.question = question;
    }

    public static QuizTypeAnswerFragment newInstance(Question question) {
        QuizTypeAnswerFragment fragment = null;
        switch (question.getType()) {
            case MULTIPLE_CHOICE:
                fragment = new QuizTypeAnswerFragmentMultipleChoice();
                break;
            case ENTER_INT_VALUE:
                fragment = new QuizTypeAnswerFragmentIntegerValue();
                break;
            case ENTER_STRING_VALUE:
                fragment = new QuizTypeAnswerFragmentStringValue();
                break;
        }

        if(fragment!=null) {
            fragment.setQuestion(question);
        }

        return fragment;
    }

    protected abstract void setUpListeners(View view);
    protected abstract int getLayoutId();

    protected void fireQuestionAnswered() {
        if( mListener != null) {
            mListener.questionAnswered();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getLayoutId(), container, false);

        setUpListeners( fragmentView );
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Must be implemented by fragment user. (QuestionActivity)
     */
    public interface OnFragmentInteractionListener {
        void questionAnswered();
    }
}
