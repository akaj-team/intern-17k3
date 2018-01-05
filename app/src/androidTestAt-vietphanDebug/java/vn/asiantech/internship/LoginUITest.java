package vn.asiantech.internship;

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

    @Rule
    public ActivityTestRule<UnitTestActivity> mActivityTestRule =
            new ActivityTestRule(UnitTestActivity.class);

    @Test
    public void checkLengthUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("admin123"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(startsWith("user name must have lengt more than 5 and a little than 24")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

//    @Test
//    public void checkToastWhenBlankUserName() {
//        // Type text and then press the button.
//        onView(withId(R.id.edtUserName))
//                .perform(typeText("viet12"), closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
//        // Check toast
//        onView(withText(startsWith("username must have most 2 number")))
//                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
//    }

}
