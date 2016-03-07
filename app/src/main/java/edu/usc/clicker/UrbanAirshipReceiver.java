package edu.usc.clicker;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.urbanairship.push.BaseIntentReceiver;
import com.urbanairship.push.PushMessage;

import org.json.JSONObject;

import edu.usc.clicker.activity.MultipleChoiceActivity;
import edu.usc.clicker.model.MultipleChoiceQuestion;

/**
 * Created by markk_000 on 3/4/2016.
 */
public class UrbanAirshipReceiver extends BaseIntentReceiver {

    private static final String TAG = "IntentReceiver";

    @Override
    protected void onChannelRegistrationSucceeded(Context context, String channelId) {
        Log.i(TAG, "Channel registration updated. Channel Id:" + channelId + ".");
    }

    @Override
    protected void onChannelRegistrationFailed(Context context) {
        Log.i(TAG, "Channel registration failed.");
    }

    @Override
    protected void onPushReceived(Context context, PushMessage message, int notificationId) {
        Log.i(TAG, "Received push notification. Alert: " + message.getAlert() + ". Notification ID: " + notificationId);
        Log.i("regular question", message.getPushBundle().getString("question"));

    }

    @Override
    protected void onBackgroundPushReceived(Context context, PushMessage message) {
        Log.i(TAG, "Received background push message: " + message);
        Log.i("background question", message.getPushBundle().getString("question"));
        Bundle b = message.getPushBundle();
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(b); //populates question with data
        MultipleChoiceActivity.start(context, question); //start activity
    }

    @Override
    protected boolean onNotificationOpened(Context context, PushMessage message, int notificationId) {
        Log.i(TAG, "User clicked notification. Alert: " + message.getAlert());
        Log.i("question opened", message.getPushBundle().getString("question"));
        Bundle b = message.getPushBundle();

        if (ClickerApplication.getShouldAutoLaunch()) {
            Log.i("question opened", "should auto launch");
        } else {
            Log.i("question opened", "should NOT auto launch");
        }
        try {
            MultipleChoiceQuestion question = new MultipleChoiceQuestion(b);
            MultipleChoiceActivity.start(context, question);
        }
        catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        // Return false to let UA handle launching the launch activity
        return false;
    }

    @Override
    protected boolean onNotificationActionOpened(Context context, PushMessage message, int notificationId, String buttonId, boolean isForeground) {
        Log.i(TAG, "User clicked notification button. Button ID: " + buttonId + " Alert: " + message.getAlert());

        // Return false to let UA handle launching the launch activity
        return false;
    }

    @Override
    protected void onNotificationDismissed(Context context, PushMessage message, int notificationId) {
        Log.i(TAG, "Notification dismissed. Alert: " + message.getAlert() + ". Notification ID: " + notificationId);
    }
}