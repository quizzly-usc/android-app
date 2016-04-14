package edu.usc.clicker.view;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import edu.usc.clicker.activity.QuestionActivity;
        import edu.usc.clicker.activity.StatisticsActivity;
        import edu.usc.clicker.model.QuizStatistics;
        import edu.usc.clicker.model.Section;

public class StatisticsListView extends ListView implements AdapterView.OnItemClickListener{
    public StatisticsListView(Context context, AttributeSet attrs) {

        super(context, attrs);


        setOnItemClickListener(this);
    }

    public void setSection(int sectionID) {
        setAdapter(new StatisticsListAdapter(getContext(), sectionID));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Clicked", Integer.toString(position));
       QuizStatistics qs = (QuizStatistics) getAdapter().getItem(position);

        QuestionActivity.start(getContext(), (int)qs.getCourseId());
    }
}
