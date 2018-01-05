import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
import vn.asiantech.internship.unittest.LoginUnitTestActivity;

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
 * Created by TienHuynh on 04/01/2018.
 * AsianTech Co., Ltd
 */
@RunWith(AndroidJUnit4.class)
public class UiTest {

    @Rule
    public ActivityTestRule<LoginUnitTestActivity> mActivityRule =
            new ActivityTestRule<>(LoginUnitTestActivity.class);

    /**
     * check Toast When Blank Password
     */
    @Test
    public void checkToastWhenBlankPassword() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tienhuynh27"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("please input password"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast When Blank User Name
     */
    @Test
    public void checkToastWhenBlankUserName() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIEN271"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Please input user name"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Validate Length UserName
     */
    @Test
    public void checkToastValidateLengthUserName() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tien"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("Length of userName is higher than 5 and lower than 25"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Capital Letter And Number
     */
    @Test
    public void checkToastCapitalLetterAndNumber() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tienhuhu"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("user name have least one capital letter and most 2 number"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }


    /**
     * check Toast User Name Space
     */
    @Test
    public void checkToastUserNameSpace() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271 "), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("user name have space"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToastUserNameSpecialChar() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271*^@"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("username have special character"))).
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
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("password is not different username"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Password At Least Number
     */
    @Test
    public void checkToastPasswordAtLeastNumber() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIENbilt"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("password have at least one special character or number"))).
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
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIENbilt "), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("password have space"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Password have Capital Letter
     */
    @Test
    public void checkToastPasswordCapitalLetter() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("tienbilt2"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("password have least 3 capital letter"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Length Password
     */
    @Test
    public void checkToastLengthPassword() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIEN27"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("password must > 7 character"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Toast Repeat Character
     */
    @Test
    public void checkToastRepeatCharacter() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIENNN27"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check toast
        Espresso.onView(withText(startsWith("repeat character password 2 times"))).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * check Dialog when Done
     */
    @Test
    public void checkDialogDone() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("TIEN271ABC"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.edtPassWord))
                .perform(typeText("TIEN27hui"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnLoginUnitTest))
                .perform(click());
        // check dialog
        Espresso.onView(withText("Notification")).check(matches(isDisplayed()));
    }
}
