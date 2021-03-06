package edu.usc.clicker.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.urbanairship.UAirship;

import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.Section;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final int LOCATION_PERMISSION_REQUEST_CODE = 42;

    private LinearLayout content;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UAirship.takeOff(getApplication(), new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {

                // Enable user notifications
                airship.getPushManager().setUserNotificationsEnabled(true);
            }
        });
        if (!ClickerApplication.LOGIN_HELPER.isLoggedIn(this)) {
            WelcomeActivity.start(this);
            finish();
        }



         requestLocationPermissions(false);

        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        content = (LinearLayout) findViewById(R.id.content);

        content.setAlpha(0.0f);
        content.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        content.animate()
                .alpha(1.0f)
                .translationY(0.0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(1000)
                .start();

        getSections();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.logout) {
            ClickerApplication.LOGIN_HELPER.logout(this);
            WelcomeActivity.start(this);
            finish();
        } else if (item.getItemId() == R.id.my_classes) {
            MyClassesActivity.start(this);
        } else if (item.getItemId() == R.id.foss) {
            FOSSActivity.start(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            requestLocationPermissions(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestLocationPermissions(true);

        if (!ClickerApplication.getShouldAutoLaunch()) {
            ClickerApplication.setShouldAutoLaunch(this, true);
            ClickerApplication.getLocationHelper().setTrackLocation(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ClickerApplication.getLocationHelper() != null) {
            ClickerApplication.getLocationHelper().setTrackLocation(false);
        }
    }

    private void getSections() {
        ClickerApplication.CLICKER_API.getUserSections(ClickerApplication.LOGIN_HELPER.getEmail(this)).enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Response<List<Section>> response, Retrofit retrofit) {
                if (response.body() == null) {
                    return;
                }

                ClickerApplication.SECTION_HELPER.removeAllSections();
                ClickerApplication.SECTION_HELPER.addSections(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void requestLocationPermissions(boolean onResume) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.permissions_request_title)
                        .setMessage(R.string.permissions_request_message)
                        .setPositiveButton(R.string.permissions_request_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                            }
                        }).create();

                dialog.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            if (!onResume) {
                ClickerApplication.initLocationHelper(this);
                ClickerApplication.enableAutoLaunch(this);
            }

            ClickerApplication.getLocationHelper().setTrackLocation(true);
        }
    }
}
