import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
import vn.asiantech.internship.unittest.TestLogin;

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
 * Created by phongle on 5/1/2561.
 * Class test UI Login
 */
@RunWith(AndroidJUnit4.class)
public class TestUILogin {
    private String mUsername = "Phongle38";
    @Rule
    public ActivityTestRule<TestLogin> mActivityTestRule = new ActivityTestRule<>(TestLogin.class);

    @Test
    public void checkToastWhenInvalidUsernameLength() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("admi"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Username must have more than 5 char and little than 24 char !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenNonAtLeastUppercaseUsername() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("phongle"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Username must have at least one uppercase !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenSpecialCharInUsername() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("Phongle$"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Username can not have special char !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenSpaceCharInUsername() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("Phong le"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Username can not have white space !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenNonAtMostTwoNumUsername() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("Phongle123"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Username just have at most two number !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenInvalidPasswordLength() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("Phong"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password must have more than 7 char !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenUsernameSamePassword() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password can not same at username !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenNonAtLeastSpecialCharOrNumInPassword() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("abcdefgh"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password must have at least one special char or number !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenNonAtLeastThreeUppercaseInPassword() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("abcdxyzt1AB"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password must have at least three uppercase !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenRepeatPassword() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("abcdabcdabcdABC1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password can not repeat over twice !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenSpaceInPassword() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("abcd xyztABC1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("Password can not have white space !")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkDialogWhenAccessGranted() {
        onView(withId(R.id.edtUsername))
                .perform(typeText(mUsername), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("abcdxyztABC1"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText("Access granted!")).check(matches(isDisplayed()));
    }
}
