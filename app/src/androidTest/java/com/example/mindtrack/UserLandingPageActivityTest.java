package com.example.mindtrack;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.mindtrack.R;
import com.example.mindtrack.UserLandingPageActivity;

@RunWith(AndroidJUnit4.class)
public class UserLandingPageActivityTest {

    @Rule
    public ActivityScenarioRule<UserLandingPageActivity> activityScenarioRule =
            new ActivityScenarioRule<>(UserLandingPageActivity.class);

    @Test
    public void testUserLandingPageActivityInView() {
        // Check if the welcome message is displayed
        onView(withId(R.id.welcome_text_view)).check(matches(isDisplayed()));
    }
}

