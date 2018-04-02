package com.sorinmarti.sphinx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.Quiz;

/**
 * Created by SOMA on 15.10.2017.
 */

public class QuizListArrayAdapter extends ArrayAdapter<Quiz> {

    private final Context context;
    private final Quiz[] values;

    public QuizListArrayAdapter(Context context, int resource, int textViewResourceId, Quiz[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.expandable_list_item, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.quiz_title);
        TextView shortDescription = (TextView) rowView.findViewById(R.id.quiz_short_desc);
        TextView description = (TextView) rowView.findViewById(R.id.quiz_long_desc);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.quiz_image);

        Quiz quiz = values[position];
        title.setText(quiz.getQuizTitle());
        shortDescription.setText(quiz.getTotalQuestionNumber()+ "Fragen");
        description.setText(quiz.getQuizDescription());

        imageView.setImageResource(R.mipmap.ic_launcher);


        return rowView;
    }
}
