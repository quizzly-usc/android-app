package edu.usc.clicker.view;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.model.StudentResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ian on 9/9/15.
 */
public class QuestionAnswerListView extends RecyclerView implements AdapterView.OnItemClickListener {
    private Boolean answer;
    private Context context;
    public QuestionAnswerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        setAdapter(new QuestionAnswerListAdapter(context));

        this.context = context;
        //setOnItemClickListener(this);
}

    public void setSelected(int id) {
        ((QuestionAnswerListAdapter) getAdapter()).setSelected(id);
    }

    public int getSelectedItem() {
        return ((QuestionAnswerListAdapter) getAdapter()).getSelectedItem();
    }
    public void setQuestion(MultipleChoiceQuestion question) {
        ((QuestionAnswerListAdapter) getAdapter()).setQuestion(question);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
