package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.eightmin4mile.jokedisplaylib.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by goandroid on 9/28/18.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, SimpleIdlingResource>, Void, String> {

    private static final String TAG = "EndpointsAsyncTask";

    private static MyApi myApiService = null;
    private Context context;


    @Override
    protected String doInBackground(Pair<Context, SimpleIdlingResource>[] pairs) {

        context = pairs[0].first;
        SimpleIdlingResource idlingResource = pairs[0].second;
        if(idlingResource != null){
            idlingResource.setIdleState(false);
        }

        if(myApiService == null){ // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
                    // end options for devappserver

            myApiService = builder.build();
        }

        String result = "";

        try {
            //result = myApiService.sayHi("whatever").execute().getData();

            result = myApiService.tellJoke().execute().getData();

        } catch (IOException e){
            Log.d(TAG, "failed to get a joke:  " + e.getMessage());

        }

        if (idlingResource != null) {
            idlingResource.setIdleState(true);
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        Intent intent = new Intent(context,
                JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_EXTRA, result);
        context.startActivity(intent);
    }
}
