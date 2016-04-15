package edu.usc.clicker.util;

import android.content.Context;

import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.activity.StatisticsActivity;
import edu.usc.clicker.model.QuizStatistics;
import edu.usc.clicker.model.StudentResponse;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by crawl_000 on 4/14/2016.

public class Validation implements Callback<StudentResponse> {
    private StudentResponse sr = new StudentResponse();

    public Validation(){}
    @Override
    public void onResponse(Response<StudentResponse> response, Retrofit retrofit) {
        sr = response.body();


    }

    @Override
    public void onFailure(Throwable t) {

    }
    public boolean answered(int id, Context context){
       ClickerApplication.CLICKER_API.getStudentResponse(id, ClickerApplication.LOGIN_HELPER.getEmail(context)).enqueue(this);
    }
}
*/