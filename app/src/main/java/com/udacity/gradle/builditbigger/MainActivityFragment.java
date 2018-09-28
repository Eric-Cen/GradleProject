package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eightmin4mile.jokedisplaylib.JokeDisplayActivity;
import com.eightmin4mile.jokegeneratelib.JokeProvider;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    TextView jokeTextView;
    Button jokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        jokeTextView = (TextView) root.findViewById(R.id.instructions_text_view);
        jokeButton = (Button) root.findViewById(R.id.bt_start_joke);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JokeProvider jokeProvider = new JokeProvider();
                String jokeString = jokeProvider.getJoke();

                Intent intent = new Intent(getActivity().getApplicationContext(),
                        JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.JOKE_EXTRA, jokeString);
                startActivity(intent);
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }


}
