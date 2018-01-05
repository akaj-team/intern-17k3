import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
@RunWith(AndroidJUnit4.class)
public class SignUpUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);
//
//    @Test
//    public void checkToastWhenBlankPassword() {
//        setupFragment();
//        // Type text and then press the button.
//        onView(withId(R.id.edtUsername))
//                .perform(typeText("hanangocanh"), closeSoftKeyboard());
//        onView(withId(R.id.imgNext)).perform(click());
//
//        // Check toast
//        onView(withText(startsWith("Not enough data")))
//                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
//    }
//    @Test
//    public void checkToastWhenNotDifferentUserName() {
//        setupFragment();
//        // Type text and then press the button.
//        onView(withId(R.id.edtUsername))
//                .perform(typeText("hanangocanH"), closeSoftKeyboard());
//        onView(withId(R.id.edtPassword))
//                .perform(typeText("hanangocanH"), closeSoftKeyboard());
//        onView(withId(R.id.imgNext)).perform(click());
//
//        // Check toast
//        onView(withText(startsWith("Password must different username")))
//                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
//    }
    @Test
    public void checkToastWhenPasswordContainSpace() {
        setupFragment();
        // Type text and then press the button.
        onView(withId(R.id.edtUsername))
                .perform(typeText("hanangocanH"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("hanangoc anh"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());

        // Check toast
        onView(withText(startsWith("Password don't contain space")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
    private void setupFragment() {
        android.support.v4.app.FragmentManager fragmentManager = mActivityRule.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frLogin, new SignUpFragment()).commit();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }
}
