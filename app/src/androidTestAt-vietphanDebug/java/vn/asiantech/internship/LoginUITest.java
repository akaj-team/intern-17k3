package vn.asiantech.internship;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.ui.unittest.UnitTestActivity;

import static android.support.test.espresso.Espresso.onView;
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
 * Created by vietphan on 05/01/2018.
 * Class vn.asiantech.internship.LoginUITest
 */
@RunWith(AndroidJUnit4.class)
public class LoginUITest {

    @SuppressWarnings("unchecked")
    @Rule
    public ActivityTestRule<UnitTestActivity> mActivityTestRule =
            new ActivityTestRule(UnitTestActivity.class);

    @Test
    public void checkLengthUserName() {
        onView(ViewMatchers.withId(R.id.edtUserName)).perform(typeText("Admi"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.edtPassword)).perform(typeText("PASsword1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("user name must have length more than 5 and a little than 24")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLeastOneCapitalLetterUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("viet12"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.edtPassword)).perform(typeText("PASsword1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("username must have least one capital letter")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSpecialCharAndSpaceUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText(") Viet12"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.edtPassword)).perform(typeText("PASsword1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("username must haven't special char and white space")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkMostTwoNumberUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("Viet123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.edtPassword)).perform(typeText("PASsword1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("username must have most 2 number")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkPasswordDifferentUserName() {
        onView(withId(R.id.edtPassword)).perform(typeText("PASword1"), closeSoftKeyboard());
        onView(withId(R.id.edtUserName)).perform(typeText("PASword1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must have different username")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkAtLeastSpecialOrNumber() {
        onView(withId(R.id.edtUserName)).perform(typeText("Vietviet1"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText("PASword"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must have at least special or number")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLengthPassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Vietviet1"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText("PASS1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must have length more than 7 char")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkRepeatCharacterPassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Vietviet1"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText("PASsword1dd"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must have repeat character a little 2 times")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSpacePassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Vietviet1"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText("PASSword 1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must haven't white space")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLeastThreeCharacters() {
        onView(withId(R.id.edtUserName)).perform(typeText("Vietviet1"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText("PAssword1"), closeSoftKeyboard());
        onView(withId(R.id.edtUserName))
                .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("password must have lest 3 Characters")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
