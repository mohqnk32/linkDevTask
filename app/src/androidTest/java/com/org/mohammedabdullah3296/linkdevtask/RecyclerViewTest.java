package com.org.mohammedabdullah3296.linkdevtask;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.org.mohammedabdullah3296.linkdevtask.adapter.ArticleAdapter;
import com.org.mohammedabdullah3296.linkdevtask.view.HomeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.org.mohammedabdullah3296.linkdevtask", appContext.getPackageName());
    }


    @Test
    public void article0() {
        onView(withId(R.id.main_news)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withText("Northern California fire is state's deadliest ever")).check(matches(isDisplayed()));
    }


}
