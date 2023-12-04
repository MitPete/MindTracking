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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.mindtrack.AdminLandingPageActivity;
import com.example.mindtrack.R;

@RunWith(AndroidJUnit4.class)
public class AdminLoginActivityTest {

    @Rule
    public ActivityScenarioRule<AdminLoginActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminLoginActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testAdminLoginActivityInView() {
        // Check if the login button is displayed
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginButtonStartsAdminLandingPageActivity() {
        // Perform a click on the login button
        onView(withId(R.id.login_button)).perform(click());

        // Verify that an Intent to start the AdminLandingPageActivity was sent
        Intents.intended(IntentMatchers.hasComponent(AdminLandingPageActivity.class.getName()));
    }
}