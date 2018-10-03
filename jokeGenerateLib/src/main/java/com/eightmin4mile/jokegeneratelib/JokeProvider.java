package com.eightmin4mile.jokegeneratelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeProvider {

    public String getJoke() {

        Random rand = new Random();

        int n = rand.nextInt(jokes.length);

        return jokes[n];
    }

    private final String[] jokes = {
            "What do you call a fish without eyes? \nFsh.",
            "What do you call an alligator detective? \nAn investi-gator.",
            "Why did the scarecrow win an award? \nBecause he was outstanding in his field.",
            "What did the policeman say to his bellybutton? \nYou're under a vest.",
            "What do you call a pig that does karate? \nA pork chop.",
            "What do you call a cow with two legs? \nLean beef!",
            "Why did the mushroom go to the party? \nBecause he was a fungi.",
            "Why can't your nose be 12 inches long? \nBecause then it would be a foot.",
            "What did the ocean say to the shore? \nNothing... It just waved.",
            "What did the tomato say to the other tomato during a race? \nKetchup."
    };
}
