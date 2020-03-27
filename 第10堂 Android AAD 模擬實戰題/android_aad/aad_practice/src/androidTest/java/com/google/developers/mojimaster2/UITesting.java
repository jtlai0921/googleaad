package com.google.developers.mojimaster2;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UITesting {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ui_test1() throws InterruptedException {
        onView(withId(R.id.action_list)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(3000);
    }

    @Test
    public void ui_test2() throws InterruptedException {
        int delay = 500;
        String[] answers = activityTestRule.getActivity().getResources().getStringArray(R.array.number_smileys_answers);
        for(String answer : answers) {
            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
            onView(withText(R.string.action_settings)).perform(click());
            onView(withText(R.string.pref_answers_title)).perform(click());
            onView(withText(answer)).perform(click());
            // 左上方 back 鍵
            onView(withContentDescription("Navigate up")).perform(click());
            Thread.sleep(delay);
        }
        Thread.sleep(3000);
    }

    // memo :
    // 左下方退回鍵
    //onView(isRoot()).perform(ViewActions.pressBack());

}
