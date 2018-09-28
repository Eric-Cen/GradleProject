package com.eightmin4mile.jokedisplaylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static String JOKE_EXTRA = "joke";
    TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        jokeText = (TextView)findViewById(R.id.tv_display_joke);

        String jokeStr = getIntent().getStringExtra(JOKE_EXTRA);

        jokeText.setText(jokeStr);
    }
}
