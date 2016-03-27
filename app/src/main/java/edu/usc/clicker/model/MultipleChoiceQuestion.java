package edu.usc.clicker.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoiceQuestion implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("choices")
    @Expose
    private List<String> choices = new ArrayList<String>();
    @SerializedName("push_hash")
    @Expose
    private String pushHash;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("expiration")
    @Expose
    private long expiration;
    @SerializedName("time_limit")
    @Expose
    private long timeLimit;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("show_answers")
    @Expose
    private boolean showAnswers;
    @SerializedName("quiz_id")
    @Expose
    public int quizID;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getPushHash() {
        return pushHash;
    }

    public String getQuestion() {
        return question;
    }

    public long getExpiration() {
        return expiration;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public String getType() {
        return type;
    }

    public boolean getShowAnsers() {
        return showAnswers;
    }

    public int getQuizID() {
        return quizID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.answer);
        dest.writeStringList(this.choices);
        dest.writeString(this.pushHash);
        dest.writeString(this.question);
        dest.writeLong(this.expiration);
        dest.writeLong(this.timeLimit);
        dest.writeString(this.type);
        dest.writeByte((byte) (this.showAnswers ? 1 : 0));
        dest.writeInt(this.quizID);
    }

    public MultipleChoiceQuestion() {
    }

    public MultipleChoiceQuestion(Bundle b) {
        //dont think we need answer
        this.answer = "yo";

        //Data from question
        this.question = b.getString("question");
        this.type = b.getString("type");
        this.quizID = Integer.parseInt(b.getString("quiz_id"));
        this.id = Integer.parseInt(b.getString("quest_id"));

        //Data from possible answers
        List<String> choices = new ArrayList<String>();
        choices.add(b.getString("answer0"));
        choices.add(b.getString("answer1"));

        if(b.getString("answer2") != null) {
            choices.add(b.getString("answer2"));

            if(b.getString("answer3") != null) {
                choices.add(b.getString("answer3"));
            }
        }


        this.choices = choices;

        //random data - not sure if we need
        this.expiration = 1467402316;
        this.timeLimit = 50000;
        this.pushHash = "some hash";
        this.showAnswers = true;

    }

    protected MultipleChoiceQuestion(Parcel in) {
        this.id = in.readInt();
        this.answer = in.readString();
        this.choices = in.createStringArrayList();
        this.pushHash = in.readString();
        this.question = in.readString();
        this.expiration = in.readLong();
        this.timeLimit = in.readLong();
        this.type = in.readString();
        this.showAnswers = in.readByte() != 0;
        this.quizID = in.readInt();
    }

    public static void answerQuestion(Context context, final AnswerResponse answer) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = String.format("http://fontify.usc.edu/question/answer?qid=%1$d&question=%2$s", 1, "hi");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("answer", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("answer error", "error");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("quest_id", "" + answer.getQuestionId());
                params.put("answer", answer.getAnswer());
                params.put("user_email", answer.getUser());
                params.put("quiz_id", "" + answer.getQuizId());
                return params;
            };
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static final Parcelable.Creator<MultipleChoiceQuestion> CREATOR = new Parcelable.Creator<MultipleChoiceQuestion>() {
        public MultipleChoiceQuestion createFromParcel(Parcel source) {
            return new MultipleChoiceQuestion(source);
        }

        public MultipleChoiceQuestion[] newArray(int size) {
            return new MultipleChoiceQuestion[size];
        }
    };
}