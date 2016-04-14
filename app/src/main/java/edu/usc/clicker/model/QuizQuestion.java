package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by crawl_000 on 4/13/2016.
 */
public class QuizQuestion implements Parcelable
{
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("quiz")
    @Expose
    private int quizID;
    @SerializedName("id")
    @Expose
    private int questID;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
        dest.writeString(this.text);
        dest.writeString(this.type);
        dest.writeInt(this.duration);
        dest.writeInt(this.quizID);
        dest.writeInt(this.questID);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public QuizQuestion() {
    }

    protected QuizQuestion(Parcel in) {
        this.text = in.readString();
        this.type = in.readString();
        this.duration = in.readInt();
        this.quizID = in.readInt();
        this.questID = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<QuizQuestion> CREATOR = new Parcelable.Creator<QuizQuestion>() {
        public QuizQuestion createFromParcel(Parcel source) {
            return new QuizQuestion(source);
        }

        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
