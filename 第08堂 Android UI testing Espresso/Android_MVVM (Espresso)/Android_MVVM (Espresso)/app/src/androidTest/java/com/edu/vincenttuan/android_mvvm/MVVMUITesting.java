package com.edu.vincenttuan.android_mvvm;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.NumberPicker;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MVVMUITesting {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fab_click() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(3000);
    }

    @Test
    public void add() throws InterruptedException {
        for(int i=1;i<=10;i++) {
            final int n = i;
            // 按下 +
            onView(withId(R.id.fab)).perform(click());
            // 輸入 title
            onView(withId(R.id.edit_text_title)).perform(typeText("TestTitle-" + n));
            // 輸入 description
            onView(withId(R.id.edit_text_description)).perform(typeText("TestDescription-" + n));
            // 選擇 Number picker
            onView(withId(R.id.number_picker_property)).perform(new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return ViewMatchers.isAssignableFrom(NumberPicker.class);
                }

                @Override
                public String getDescription() {
                    return "Set the value of a NumberPicker";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    ((NumberPicker) view).setValue(n);
                }
            });
            // 存檔
            onView(withId(R.id.add_note)).perform(click());
            //Thread.sleep(3000);
        }
    }

    @Test
    public void auto_add() throws InterruptedException {
        for(int i=1;i<=3;i++) {
            onView(withId(R.id.auto_add_note)).perform(click());
        }
    }

    @Test
    public void delete_all() throws InterruptedException {
        onView(withId(R.id.delete_all_notes)).perform(click());
    }

    @Test
    public void merge() throws InterruptedException {
        delete_all();
        auto_add();
        auto_add();
        delete_all();
        auto_add();
        delete_all();
    }

}
