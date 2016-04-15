package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by crawl_000 on 4/14/2016.
 */
public class AnswerOptions implements Parcelable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("correct")
    @Expose
    private String correct;
    @SerializedName("option")
    @Expose
    private Boolean option;
    @SerializedName("question")
    @Expose
    private int questID;
    @SerializedName("id")
    @Expose
    private int answerID;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.correct);
        dest.writeByte((byte) (option ? 1 : 0));
        dest.writeInt(this.questID);
        dest.writeInt(this.answerID);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public AnswerOptions() {
    }

    protected AnswerOptions(Parcel in) {
        this.text = in.readString();
        this.correct = in.readString();
        this.option = in.readByte() != 0;
        this.questID = in.readInt();
        this.answerID = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel source) {
            return new Section(source);
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Boolean getOption() {
        return option;
    }

    public void setOption(Boolean option) {
        this.option = option;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }
}
