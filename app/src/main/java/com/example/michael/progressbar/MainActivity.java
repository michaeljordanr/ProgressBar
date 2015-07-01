package com.example.michael.progressbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView txtContent;
    private ProgressBar progress;
    private int animTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        animTime = getResources().getInteger(android.R.integer.config_longAnimTime);
        setContentView(R.layout.activity_main);

        txtContent = (TextView) findViewById(R.id.txtContent);
        progress = (ProgressBar) findViewById(R.id.progress);

        txtContent.setVisibility(View.GONE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent();
                    }
                });
            }
        }).start();

    }

    private void showContent() {
        //txtContent.setVisibility(View.VISIBLE);
        //progress.setVisibility(View.GONE);

        txtContent.setVisibility(View.VISIBLE);
        txtContent.setAlpha(0.0f);

        txtContent.animate()
                .alpha(1.0f)
                .setDuration(animTime)
                .setListener(null);

        progress.animate()
                .alpha(0.0f)
                .setDuration(animTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.setVisibility(View.GONE);
                    }
                });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
