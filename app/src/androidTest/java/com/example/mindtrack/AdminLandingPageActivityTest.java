package com.example.mindtrack;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.mindtrack.AdminLandingPageActivity;
import com.example.mindtrack.LoginActivity;

@RunWith(AndroidJUnit4.class)
public class AdminLandingPageActivityTest {

    @Rule
    public ActivityScenarioRule<AdminLandingPageActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminLandingPageActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testLogoutButtonStartsLoginActivity() {
        // Perform a click on the logout button
        onView(withId(R.id.logout_button)).perform(click());

        // Verify that an Intent to start the LoginActivity was sent
        Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
    }
}