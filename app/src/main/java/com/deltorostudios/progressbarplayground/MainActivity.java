package com.deltorostudios.progressbarplayground;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int x, y;
    private TextView numberView, countdownNumberView;
    private ProgressBar fabProgressBar, countdownProgressBar;
    private Button countdownButton;
    private CountDownTimer timer;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numberView = findViewById(R.id.textViewNumber);
        countdownNumberView = findViewById(R.id.CountdownView);
        countdownButton = findViewById(R.id.CountdownButton);
        countdownProgressBar = findViewById(R.id.CountDownProgressBar);
        fabProgressBar = findViewById(R.id.fabProgressBar);

        countdownProgressBar.setVisibility(ProgressBar.VISIBLE);
        countdownProgressBar.setMax(300);
        fabProgressBar.setVisibility(ProgressBar.VISIBLE);
        fabProgressBar.setMax(10);
        x = 0;
        y = 0;

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdownButton.setEnabled(false);
                activateCountdown();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabulous);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = x + 1;
                numberView.setText(""+x);
                fabProgressBar.setProgress(x);

                if (x == 10) {
                    x = 0;
                    numberView.setText(""+x);
                    fabProgressBar.setProgress(x);
                    Toast.makeText(MainActivity.this, "YOU WINNNNN!!!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
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

    void activateCountdown() {
        timer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long l) {
                Log.e(TAG, "" + l);
                y = y + 1;
                int z = 300 - y;
                countdownNumberView.setText("" + z);
                countdownProgressBar.setProgress(y);
            }

            @Override
            public void onFinish() {
                y = 0;
                countdownProgressBar.setProgress(0);
                countdownButton.setEnabled(true);
                Toast.makeText(MainActivity.this, "YOU FUCKING ROCK YO", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }


}
