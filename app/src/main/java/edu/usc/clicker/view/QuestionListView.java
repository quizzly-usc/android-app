package edu.usc.clicker.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.activity.QuestionActivity;
import edu.usc.clicker.activity.QuestionAnswerActivity;
import edu.usc.clicker.activity.StatisticsActivity;
import edu.usc.clicker.model.QuizQuestion;
import edu.usc.clicker.model.StudentResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QuestionListView extends ListView implements AdapterView.OnItemClickListener{
    private Boolean answer;
    private Context context;
    private int id2;
    private String questionText;
    public QuestionListView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;
        setOnItemClickListener(this);
    }

    public void setSection(int sectionID) {
        setAdapter(new QuestionViewAdapter(getContext(), sectionID));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.i("OnItemClick", "clicked");
        QuizQuestion quizQuestion = ((QuestionViewAdapter) getAdapter()).getItem(position);
        id2 = quizQuestion.getQuestID();
        questionText = quizQuestion.getText();

        ClickerApplication.CLICKER_API.getStudentResponse(id2, ClickerApplication.LOGIN_HELPER.getEmail(getContext())).enqueue(new Callback<StudentResponse>() {

            @Override
            public void onResponse(Response<StudentResponse> response, Retrofit retrofit) {
                StudentResponse sr = response.body();
                QuestionAnswerActivity.start(getContext(), id2, questionText);
               /* if (sr.getStudent_answer() > 0) {
                    answer = true;

                }
                else{
                    answer = false;
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("Invalid: ")
                        .setMessage("Answered " + sr.getStudent_answer())
                        .show();
                */
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
       /* if (answer) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Invalid: ")
                    .setMessage("Answered")
                    .show();
        }
        else {
            new AlertDialog.Builder(getContext())
                    .setTitle("Invalid: ")
                    .setMessage("You have not answered this question yet!")
                            .show();

        }*/
    }
}

