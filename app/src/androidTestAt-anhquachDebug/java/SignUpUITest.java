import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
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
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.mockito.AdditionalMatchers.not;

/**
 * Created by anh.quach on 1/5/18.
 * Test UI sign up fragment
 */
@RunWith(AndroidJUnit4.class)
public class SignUpUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void init() {
        mActivityRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frLogin, SignUpFragment.newInstance())
                .commit();
    }

    @Test
    public void checkToastWhenBlankPassword() {
        // Type text and then press the button.
        onView(withId(R.id.edtUsername))
                .perform(typeText("trungnnq@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.imgNext)).perform(click());

        // Check toast
        onView(withText(startsWith("Username and password can not blank")))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
