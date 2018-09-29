package com.udacity.gradle.builditbigger.backend;

import com.eightmin4mile.jokegeneratelib.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();

        JokeProvider jokeProvider = new JokeProvider();

        String joke = jokeProvider.getJoke();

        response.setData(joke);

        return response;
    }

//    @ApiMethod(name = "tellJoke")
//    public MyBean tellJoke(){
//        MyBean response = new MyBean();
//
//        JokeProvider jokeProvider = new JokeProvider();
//
//        String newJoke = jokeProvider.getJoke();
//
//        response.setData(newJoke);
//
//        return response;
//
//    }

}
