package edu.usc.clicker.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.ResponseBody;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.activity.MainActivity;
import edu.usc.clicker.model.AnswerResponse;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.util.ClickerLog;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QuestionAnswerListAdapter extends RecyclerView.Adapter<QuestionAnswerListItem> implements Callback<ResponseBody> {
    private Context context;
    private MultipleChoiceQuestion question;
    private int selected = -1;
    private boolean showAnswers = true;

    public void setQuestion(MultipleChoiceQuestion question) {
        this.question = question;
        this.showAnswers = this.question.getShowAnsers();
    }

    @Override
    public QuestionAnswerListItem onCreateViewHolder(ViewGroup viewGroup, int i) {

        View answer = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_answer_item, viewGroup, false);
        return new QuestionAnswerListItem(answer, this);
    }

    @Override
    public void onBindViewHolder(QuestionAnswerListItem questionAnswerListItem, int i) {
        questionAnswerListItem.bindAnswer(question.getChoices().get(i), i, showAnswers);
       questionAnswerListItem.setSelected(selected == i);
    }

    @Override
    public int getItemCount() {
        return question == null ? 0 : question.getChoices().size();
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
      //  answer();
    }

    public int getSelectedItem() {
        return selected;
    }

    public QuestionAnswerListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
        ClickerLog.d("MCLA", response.message());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }

    private void answer() {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setAnswer(question.getChoices().get(selected));
        answerResponse.setQuestionId(question.getID());
        answerResponse.setUser(ClickerApplication.LOGIN_HELPER.getEmail(context));
        if (ClickerApplication.getLocationHelper() != null && ClickerApplication.getLocationHelper().hasLocation()) {
            answerResponse.setLocationBody(ClickerApplication.getLocationHelper().getBestLocation());
        }
        answerResponse.setQuizId(question.getQuizID());

        //ClickerApplication.CLICKER_API.answer(answerResponse).enqueue(this);
        MultipleChoiceQuestion.answerQuestion(context, answerResponse);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
