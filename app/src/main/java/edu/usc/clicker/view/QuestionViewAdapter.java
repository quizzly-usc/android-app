package edu.usc.clicker.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.CourseInfo;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.model.QuizQuestion;
import edu.usc.clicker.model.QuizStatistics;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QuestionViewAdapter extends BaseAdapter implements Callback <List<QuizQuestion> > {
    private final Context context;

    private final List<QuizQuestion> questions = new ArrayList<>();

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_item, parent, false);
        }

        ((QuestionListItem) convertView).bindQuizQuestions(questions.get(position));
        return convertView;
    }

    @Override
    public void onResponse(Response<List<QuizQuestion>> response, Retrofit retrofit) {

        questions.clear();

        questions.addAll(response.body());

        notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable t) {

    }
    public void refresh(int sectionID){
        Log.i("sectionID", Integer.toString(sectionID));
        ClickerApplication.CLICKER_API.getQuestions(sectionID).enqueue(this);
    }
    public QuestionViewAdapter(Context context, int sectionID) {
        this.context = context;
        refresh(sectionID);

    }
}
