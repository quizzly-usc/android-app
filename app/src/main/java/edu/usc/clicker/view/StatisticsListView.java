package edu.usc.clicker.view;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.support.design.widget.Snackbar;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import java.util.List;

        import edu.usc.clicker.ClickerApplication;
        import edu.usc.clicker.activity.QuestionActivity;
        import edu.usc.clicker.activity.StatisticsActivity;
        import edu.usc.clicker.model.QuizStatistics;
        import edu.usc.clicker.model.Section;
        import edu.usc.clicker.model.StudentResponse;
        import retrofit.Callback;
        import retrofit.Response;
        import retrofit.Retrofit;

public class StatisticsListView extends ListView implements AdapterView.OnItemClickListener{
    private Context context;
    private Boolean answer = false;
    public StatisticsListView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;
        setOnItemClickListener(this);
    }

    public void setSection(int sectionID) {
        setAdapter(new StatisticsListAdapter(getContext(), sectionID));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Clicked", Integer.toString(position));
        QuizStatistics qs = (QuizStatistics) getAdapter().getItem(position);
        QuestionActivity.start(getContext(), (int) qs.getSectionId());
    }
}
