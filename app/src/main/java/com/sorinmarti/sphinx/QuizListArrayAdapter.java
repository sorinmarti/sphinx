package com.sorinmarti.sphinx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.Quiz;

import java.util.Objects;

/**
 * Created by SOMA on 15.10.2017.
 */

class QuizListArrayAdapter extends ArrayAdapter<Quiz> {

    private final Context context;
    private final Quiz[] values;

    public QuizListArrayAdapter(Context context, int resource, int textViewResourceId, Quiz[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = Objects.requireNonNull(inflater).inflate(R.layout.expandable_list_item, parent, false);
        TextView title = rowView.findViewById(R.id.quiz_title);
        TextView shortDescription = rowView.findViewById(R.id.quiz_short_desc);
        TextView description = rowView.findViewById(R.id.quiz_long_desc);
        ImageView imageView = rowView.findViewById(R.id.quiz_image);

        Quiz quiz = values[position];
        title.setText(quiz.getQuizTitle());
        shortDescription.setText(quiz.getTotalQuestionNumber()+ "Fragen");
        description.setText(quiz.getQuizDescription());

        imageView.setImageResource(R.mipmap.ic_launcher);


        return rowView;
    }
}
