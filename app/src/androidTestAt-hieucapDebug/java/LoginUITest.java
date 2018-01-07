import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.mockito.LoginActivity;

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
 * Create UI test
 * Created by tiboo on 05/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class LoginUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkNullUsername() {
        // Press the button.
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username is empty
        onView(withText(startsWith("Username is empty")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLengthAllowedUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("hieu"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username length is not allowed
        onView(withText(startsWith("Username length is not allowed")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCapitalLetterUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("hieuintern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username not enough capital letter
        onView(withText(startsWith("Username not enough capital letter")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkHaveSpaceUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username have character space
        onView(withText(startsWith("Username have character space")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCharactersUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu_intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username have spacial character
        onView(withText(startsWith("Username have special character")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkDigitNumberUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu222intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username above two number
        onView(withText(startsWith("Username above two number")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkUpperCaseOrLowerCaseUsername() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("hHDieu22intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when username general characters not usual
        onView(withText(startsWith("Password is empty")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNullPassword() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when password is empty
        onView(withText(startsWith("Password is empty")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkPasswordDifferentUsername() {
        // Type text username.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast when password is not different username
        onView(withText(startsWith("Password is not different username")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCharacterSpecialAndDigitNumberPassword() {
        // Type text username.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        // Type text password and then press the button.
        onView(withId(R.id.edtPassword))
                .perform(typeText("Hieuintern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText(startsWith("Password have at least one special character or character number")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkLengthAllowedPassword() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        // Type text username and then press the button.
        onView(withId(R.id.edtPassword))
                .perform(typeText("hie2u"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText(startsWith("Password length is not allowed")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSameCharacterPassword() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        // Type text username and then press the button.
        onView(withId(R.id.edtPassword))
                .perform(typeText("hiefdsABFff2u"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText(startsWith("Password have character overlap more than two times")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkHaveSpacePassword() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        // Type text username and then press the button.
        onView(withId(R.id.edtPassword))
                .perform(typeText("hieds ff2u4"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText(startsWith("Password have character space")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCapitalLetterPassword() {
        // Type text username and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        // Type text username and then press the button.
        onView(withId(R.id.edtPassword))
                .perform(typeText("hiedsff2u4"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText(startsWith("Password not enough three capital letters")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkDialogShow() {
        // Type text and then press the button.
        onView(withId(R.id.edtUserName))
                .perform(typeText("Hieu22intern"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("HIUe9845intern"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        // Check toast
        onView(withText("Congratulations on your successful login !!!")).check(matches(isDisplayed()));
    }
}
