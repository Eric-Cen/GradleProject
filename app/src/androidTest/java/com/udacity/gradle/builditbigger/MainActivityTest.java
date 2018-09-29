package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by goandroid on 9/28/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup(){
        mActivityRule.getActivity();
    }

    @Before
    public void registerIdlingResource(){
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void testAsyncTaskResult() throws Exception {
        onView(withText(mActivityRule.getActivity().getResources().getString(R.string.button_text)))
                .perform(click());

        onView(withText(containsString("w")))
                .check(matches(isDisplayed()));
    }

}
