import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
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
 * Created by hoangnhat on 05/01/2018.
 * Class test UI
 */
@RunWith(AndroidJUnit4.class)
public class UITestLogin {

    @Rule
    public ActivityTestRule<UnitTestActivity> mActivityTestRule =
            new ActivityTestRule<>(UnitTestActivity.class);

    @Test
    public void checkToastWhenBlankPassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhat1"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("Password is blank")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLengthUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("abcd"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("UserName 5-24")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkUpperCaseUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("nhatng"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("UserName have not upper case")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSpecialCaseUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng*"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("UserName have special case")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNumberUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng123"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("UserName have more 2 number")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkPassWordLikeUserName() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng12"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("Nhatng12"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("UserName like PassWord")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSpecialNumberPassWord() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng16"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("nhatng"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("PassWord have special case or number")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkAppearCaseAndLengthPassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng13"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("nhatnguyen1"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("Pass less than 7 or appear a case")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSpaceCasePassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng14"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("nhatn 1"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("Pass have space")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLeastThreeUpperCasePassword() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng15"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("nhatdihoc1"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText(startsWith("password have 3 upper case")))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLoginSuccess() {
        onView(withId(R.id.edtUserName)).perform(typeText("Nhatng12"), closeSoftKeyboard());
        onView(withId(R.id.edtPassWord)).perform(typeText("NhatDiHoc1"), closeSoftKeyboard());
        onView(withId(R.id.btnSubmit)).perform(click());
        onView(withText("Notification"))
                .check(matches(isDisplayed()));
    }
}
