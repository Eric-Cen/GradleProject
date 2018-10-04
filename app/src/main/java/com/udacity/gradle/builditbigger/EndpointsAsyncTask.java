package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by goandroid on 9/28/18.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "EndpointsAsyncTask";

    private static MyApi myApiService = null;

    private Context mContext;
    private SimpleIdlingResource mSimpleIdlingResource;
    private OnEventListener<String> mCallback;
    public  Exception mException;


    public interface  OnEventListener<T> {
        public void onSuccess (T object);
        public void onFailure (Exception e);
    }

    public EndpointsAsyncTask(Context context,
                              OnEventListener callback,
                              SimpleIdlingResource simpleIdlingResource){

        mContext = context;
        mCallback = callback;
        mSimpleIdlingResource = simpleIdlingResource;

    }


    @Override
    protected String doInBackground(Void... voids) {

        if(mSimpleIdlingResource != null){
            mSimpleIdlingResource.setIdleState(false);
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
            mException  = e;

        }

        if (mSimpleIdlingResource != null) {
            mSimpleIdlingResource.setIdleState(true);
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if(mCallback != null){
            if(mException == null){
                mCallback.onSuccess(result);
            } else {
                mCallback.onFailure(mException);
            }
        }
    }
}
