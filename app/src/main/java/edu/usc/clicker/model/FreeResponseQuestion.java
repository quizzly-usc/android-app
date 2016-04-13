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

import java.util.HashMap;
import java.util.Map;

public class FreeResponseQuestion implements Parcelable {

    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("push_hash")
    @Expose
    private String pushHash;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("expiration")
    @Expose
    private long expiration;
    @SerializedName("quiz_id")
    @Expose
    private int quizId;
    @SerializedName("time_limit")
    @Expose
    private long timeLimit;
    @SerializedName("type")
    @Expose
    private String type;

    public String getAnswer() {
        return answer;
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

    public int getQuizId(){ return quizId; }

    public String getType() {
        return type;
    }

    public int getId(){ return id;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.answer);
        dest.writeString(this.pushHash);
        dest.writeString(this.question);
        dest.writeLong(this.expiration);
        dest.writeLong(this.timeLimit);
        dest.writeString(this.type);
    }

    public FreeResponseQuestion() {
    }

    protected FreeResponseQuestion(Parcel in) {
        this.answer = in.readString();
        this.pushHash = in.readString();
        this.question = in.readString();
        this.expiration = in.readLong();
        this.timeLimit = in.readLong();
        this.type = in.readString();
    }

    public FreeResponseQuestion(Bundle b) {
        this.question = b.getString("question");
        this.type = b.getString("type");
        this.quizId = Integer.parseInt(b.getString("quiz_id"));
        this.id = Integer.parseInt(b.getString("quest_id"));
        this.expiration = 1467402316;
        this.timeLimit = Integer.parseInt(b.getString("time_limit"));
        timeLimit *= 1000;


    }

    public static final Parcelable.Creator<FreeResponseQuestion> CREATOR = new Parcelable.Creator<FreeResponseQuestion>() {
        public FreeResponseQuestion createFromParcel(Parcel source) {
            return new FreeResponseQuestion(source);
        }

        public FreeResponseQuestion[] newArray(int size) {
            return new FreeResponseQuestion[size];
        }
    };

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
}