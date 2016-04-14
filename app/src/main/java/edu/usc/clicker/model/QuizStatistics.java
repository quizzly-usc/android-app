package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizStatistics implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("course")
    @Expose
    private double courseId;
    @SerializedName("id")
    @Expose
    private double sectionId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getQuizName() {
        return title;
    }

    public void setQuizName(String quizName) {
        this.title = quizName;
    }

    public double getCourseId() {
        return courseId;
    }

    public void setCourseId(double courseId) {
        this.courseId = courseId;
    }

    public double getSectionId() {
        return sectionId;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public void setSectionId(double sectionId) {
        this.sectionId = sectionId;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeDouble(this.courseId);
        dest.writeDouble(this.sectionId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }
    protected QuizStatistics(Parcel in) {
        this.title = in.readString();
        this.courseId = in.readDouble();
        this.sectionId = in.readDouble();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    //   this.id = in.readLong();
       // this.createdAt = in.readString();
       // this.updatedAt = in.readString();
    }
    public static final Parcelable.Creator<QuizStatistics> CREATOR = new Parcelable.Creator<QuizStatistics>() {
        public QuizStatistics createFromParcel(Parcel source) {
            return new QuizStatistics(source);
        }

        public QuizStatistics[] newArray(int size) {
            return new QuizStatistics[size];
        }
    };
}