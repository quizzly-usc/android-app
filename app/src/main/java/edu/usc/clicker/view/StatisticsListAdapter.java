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
import edu.usc.clicker.model.QuizStatistics;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class StatisticsListAdapter extends BaseAdapter implements Callback <List<QuizStatistics> > {
    private final Context context;

    private final List<QuizStatistics> quizzes = new ArrayList<>();

    @Override
    public int getCount() {
        return quizzes.size();
    }

    @Override
    public Object getItem(int position) {
        return quizzes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_list_item, parent, false);
        }

        ((StatisticsListItem) convertView).bindQuizStatistics(quizzes.get(position));
        return convertView;
    }

    @Override
    public void onResponse(Response<List<QuizStatistics>> response, Retrofit retrofit) {

        if(response.equals(null)){
            Log.d("success", "true");
        }
        else{
            Log.d("succes", "false");
        }

        try {
            Log.i("response", response.errorBody().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        quizzes.clear();

        quizzes.addAll(response.body());

        Log.d("quizzes added", "");
        Log.d("quiz 1", quizzes.get(0).getQuizName());
        Log.d("quiz 2", quizzes.get(1).getQuizName());
        Log.d("quiz 3", quizzes.get(2).getQuizName());

        notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    public StatisticsListAdapter(Context context, int sectionID) {
        this.context = context;

        Log.d("clear quizzes", "1");
        ClickerApplication.CLICKER_API.getStats(sectionID).enqueue(this);
        Log.d("clear quizzes", "2");
    }
}
