package edu.usc.clicker.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.usc.clicker.R;


import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.AnswerOptions;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.model.StudentResponse;
import edu.usc.clicker.util.Timer;
import edu.usc.clicker.view.MultipleChoiceList;
import edu.usc.clicker.view.QuestionAnswerListView;
import edu.usc.clicker.view.TimerView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class QuestionAnswerActivity extends ResponseActivity implements Timer.TimerListener {

    private LinearLayout root;
    private LinearLayout content;
    private QuestionAnswerListView questionAnswerListView;
    private TextView questionText;
    private Vibrator vibrator;
    private int questID;
    private String answerStudent;
    private String answerCorrect;
    private String TextQuestion;
    private List<AnswerOptions> answerOptions;
    private MultipleChoiceQuestion mcq;
    private List<String> answers;
    private TextView titleCorrect;
    private TextView titleStudent;

    public static void start(Context context) {
        Intent intent = new Intent(context, QuestionAnswerActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, int position, String quest, String sa, String correct) {

        context.startActivity(getIntent(context, position, quest, sa, correct));
    }

    public static Intent getIntent(Context context, int position, String quest, String student_answer, String correct_answer) {
        Intent intent = new Intent(context, QuestionAnswerActivity.class);
        intent.putExtra("question", position);
        intent.putExtra("questionText", quest);
        intent.putExtra("student_answer", student_answer);
        intent.putExtra("correct_answer", correct_answer);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        answerOptions = new ArrayList<AnswerOptions>();
        questID = getIntent().getExtras().getInt("question");
        TextQuestion = getIntent().getExtras().getString("questionText");
        answerStudent = getIntent().getExtras().getString("student_answer");
        answerCorrect = getIntent().getExtras().getString("correct_answer");

        root = (LinearLayout) findViewById(R.id.root);
        titleStudent= (TextView) findViewById(R.id.titleStudent);
        titleCorrect = (TextView) findViewById(R.id.titleCorrect);
        content = (LinearLayout) findViewById(R.id.content);
        questionAnswerListView = (QuestionAnswerListView)findViewById(R.id.questionAnswerListView);
        //timerView = (TimerView) findViewById(R.id.timeRemaining);
        questionText = (TextView) findViewById(R.id.question);
        questionAnswerListView.setSelected(questID);
        titleStudent.setText("Your Answer:  " + answerStudent);
        titleCorrect.setText("Correct: " + answerCorrect);
        ClickerApplication.CLICKER_API.getAnswers(questID).enqueue(new Callback<List<AnswerOptions>>() {

            @Override
            public void onResponse(Response<List<AnswerOptions>> response, Retrofit retrofit) {

                Log.i("Responded: ", "response");
                answerOptions.addAll(response.body());
                Log.i("answers ", "here");
                mcq = new MultipleChoiceQuestion();
                mcq.setQuestion(TextQuestion);
                mcq.setShowAnswers(true);
                answers = new ArrayList<String>();
                int checker = answerOptions.size();
                Log.i("answers", Integer.toString(checker));
                for (int i = 0; i < checker; i++) {
                    Log.i("answers", answerOptions.get(i).getText());
                    answers.add(answerOptions.get(i).getText());
                    if("true" == answerOptions.get(i).getCorrect()){
                  //      titleCorrect.setText("Correct: " + numberToLetter(i));
                    }
                    Log.i("answers", Integer.toString(answers.size()));
                }
                mcq.setChoices(answers);

                if (mcq != null) {

                    setQuestion(mcq);

                    //letter.setBackgroundColor(Color.GREEN);
                }

            }

            @Override
            public void onFailure(Throwable t) {


            }
        });

        Log.i("mult-choice create", "end");
        ClickerApplication.getLocationHelper().setTrackLocation(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mult-choice destroy", "why");
        ClickerApplication.getLocationHelper().setTrackLocation(false);
    }
    private String numberToLetter(int id){
        if(id == 1){
            return "A";
        }
        else if(id == 2){
            return "B";
        }
        else if(id == 3){
            return "C";
        }
        else if(id == 4){
            return "D";
        }
        else{
            return "E";
        }
    }
    private void setQuestion(MultipleChoiceQuestion question) {
        content.setAlpha(1.0f);
        content.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        questionText.setText(question.getQuestion());

        questionAnswerListView.setQuestion(question);

        content.animate().alpha(1.0f).translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(700).start();

       // timerView.setListener(this);


        Log.i("mult-choice set q", "did it get here?");
    }

    private void rippleTimeRemaining() {
        int color1 = getResources().getColor(R.color.colorPrimary);
        int color2 = getResources().getColor(R.color.colorWarning);

        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), color1, color2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                root.setBackgroundDrawable(new ColorDrawable((Integer) animation.getAnimatedValue()));
            }
        });

        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        final ValueAnimator animator2 = ValueAnimator.ofObject(new ArgbEvaluator(), color2, color1);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                root.setBackgroundDrawable(new ColorDrawable((Integer) animation.getAnimatedValue()));
            }
        });

        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animator2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();
    }

    @Override
    public void onTick(int seconds) {
        if (seconds <= 10) {
            rippleTimeRemaining();
        }
    }

    @Override
    public void onFinish() {
        finish();
    }
}

