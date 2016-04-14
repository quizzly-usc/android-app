package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by crawl_000 on 4/13/2016.
 */
public class CourseInfo {
    @SerializedName("quizzes")
    @Expose
    private ArrayList<QuizStatistics> quizzes;
    @SerializedName("sections")
    @Expose
    private ArrayList<Section> sections;
    @SerializedName("id")
    @Expose
    private double sectionId;
    @SerializedName("createdAt")
    @Expose
    private long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;
    @SerializedName("title")
    @Expose
    private String title;

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public double getSectionId() {
        return sectionId;
    }

    public void setSectionId(double sectionId) {
        this.sectionId = sectionId;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public ArrayList<QuizStatistics> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<QuizStatistics> quizzes) {
        this.quizzes = quizzes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
