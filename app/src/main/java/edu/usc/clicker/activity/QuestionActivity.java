package edu.usc.clicker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.QuizQuestion;
    import edu.usc.clicker.model.Section;
import edu.usc.clicker.model.StudentResponse;
import edu.usc.clicker.util.ClickerLog;
    import edu.usc.clicker.view.QuestionListView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QuestionActivity extends AppCompatActivity implements Callback<StudentResponse> {

    public static void start(Context context, int quizID) {
        //ClickerApplication.CLICKER_API.getStudentResponse(quizID, )
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra("question", quizID);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  if (!getIntent().hasExtra("section")) {
            ClickerLog.e("StatisticsActivity", "Activity started with invalid bundle!");
            finish();
        }
      */
        int questID= getIntent().getExtras().getInt("question");

        setContentView(R.layout.activity_question);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Questions");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        QuestionListView listView = (QuestionListView) findViewById(R.id.listView);

        listView.setSection(questID);
    }

    @Override
    public void onResponse(Response<StudentResponse> response, Retrofit retrofit) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}

