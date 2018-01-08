import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.login.SignUpFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by anh.quach on 1/5/18.
 * Test UI sign up fragment
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class SignUpUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkToastAWhenBlankPassword() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanh"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Not enough data")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenNotDifferentUserName() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Password must different username")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenPasswordContainSpace() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("hanangoc anh"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Password don't contain space")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenPasswordNotContainSpecialCharOrNumber() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("hanangocanh"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Password must contain at least a specical letter or a number letter")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenPasswordIncorrectPasswordLenght() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ha@a"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Passwords have at least seven letter and contain at most two duplicate letters")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenPasswordContainLessThanThreeCapitalChar() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TT"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Password must have at least three capital letter")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenUsernameLessThan5OrMoreThan24() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hana"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TTJ"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("Length must more than 5 and less than 24")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenUsernameNotContainCapitalChar() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanh"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TTJ"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("User name must have at least a capital letter")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenUsernameContainSpecialLetterAndSpace() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("Hanangoc@a nh"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TTJ"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("User name can't contain special letter and space")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastWhenUsernameContainAtLeastTwoDigit() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("Hanangocanh112"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TTJ"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText(startsWith("User name have most two digit")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkShowDialog() {
        setupFragment();
        onView(withId(R.id.edtUsername))
                .perform(typeText("Hanangocanh"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("ngocanh@TTJ"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());
        onView(withText("Done")).check(matches(isDisplayed()));
    }

    private void setupFragment() {
        android.support.v4.app.FragmentManager fragmentManager = mActivityRule.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frLogin, new SignUpFragment()).commit();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }
}
