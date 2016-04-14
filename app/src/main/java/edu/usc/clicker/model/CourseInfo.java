package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by crawl_000 on 4/13/2016.
 */
public class CourseInfo implements Parcelable{
    protected CourseInfo(Parcel in) {
        quizzes = in.createTypedArrayList(QuizStatistics.CREATOR);
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public ArrayList<QuizStatistics> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<QuizStatistics> quizzes) {
        this.quizzes = quizzes;
    }

    @SerializedName("quizzes")
    @Expose
    private ArrayList<QuizStatistics> quizzes;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //  dest.writeString(this.courseID);
        dest.writeTypedList(quizzes);
    }
}
