package vn.asiantech.internship.tungnguyen;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.unittest.LoginActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by trungnnq on 7/6/17.
 */
@RunWith(AndroidJUnit4.class)
public class LoginUiTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkToastLengthUserName() {
        // Type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tung"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // Check toast
        Espresso.onView(withText(startsWith("The username length is greater than 5 and is less than 24 characters"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Capital Letter And Number
     */
    @Test
    public void checkToastCapitalLetterAndNumber() {
        // Type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tungkute"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Username There is at least one capital letter and two numerals"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Space of User
     */
    @Test
    public void checkToastUserSpace() {
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tun 123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // Check toast
        Espresso.onView(withText(startsWith("UserName does not contain space"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast User not Special Character
     */
    @Test
    public void checkToastUserNameSpecialChar() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tung271*^@"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("UserName does not contain special characters"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Password Different UserName
     */
    @Test
    public void checkToastPasswordDifferentUserName() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TUNG123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TUNG123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("UserName and Password must be different"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Length PassWord
     */
    @Test
    public void checkToastLengthPassword() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TUNG123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("ABC123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("The minimum password length is 7 characters"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Password have Space
     */
    @Test
    public void checkToastPasswordSpace() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TUNGDEV123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TUNG Android"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Password can not contain spaces"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Password Capital
     */
    @Test
    public void checkToastPasswordCapital() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TUNGDEV123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("tung01235"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Password There are at least 3 uppercase letters"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Repeat PassWord Character
     */
    @Test
    public void checkToastRepeatPassWordCharacter() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tung368AAA"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TTUUNNGG"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Password can not be repeated more than 2 time"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Dialog when Login Done
     */
    @Test
    public void checkDialogDone() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tung362AST"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TUNG363AA"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnit))
                .perform(click());
        // check dialog
        Espresso.onView(withText("Notification")).check(matches(isDisplayed()));
    }
}
