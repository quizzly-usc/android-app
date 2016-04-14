package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;
import edu.usc.clicker.model.QuizQuestion;
import edu.usc.clicker.model.QuizStatistics;

public class QuestionListItem extends LinearLayout {
    private TextView quizName;
    //private TextView score;

    public void bindQuizQuestions(QuizQuestion quizQuestions) {
        quizName.setText(quizQuestions.getText());
       // score.setText(Integer.toString(quizStatistics.getSectionId()));
    }

    public QuestionListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        quizName = (TextView) findViewById(R.id.quizName);
        //score = (TextView) findViewById(R.id.score);
    }
}
