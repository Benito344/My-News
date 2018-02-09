package com.behague.benjamin.mynews;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.mynews.controllers.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Benjamin BEHAGUE on 05/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivity_Test {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchActivityLaunch() throws Exception{
        onView(withId(R.id.menu_search)).perform(click());
        onView(withId(R.id.button_search)).check(matches(allOf(isDescendantOfA(withId(R.id.relative_layout_search)))));
    }
}
