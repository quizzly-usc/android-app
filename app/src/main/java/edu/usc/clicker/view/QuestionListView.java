package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.usc.clicker.activity.QuestionActivity;
import edu.usc.clicker.activity.StatisticsActivity;

public class QuestionListView extends ListView implements AdapterView.OnItemClickListener{
    public QuestionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSection(int sectionID) {
        setAdapter(new QuestionViewAdapter(getContext(), sectionID));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        //QuestionActivity.start(getContext(), position);
    }
}
