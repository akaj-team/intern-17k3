import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.asiantech.internship.R;
import vn.asiantech.internship.unittest.LoginUnitTestActivity;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by TienHuynh on 04/01/2018.
 * AsianTech Co., Ltd
 */
@RunWith(AndroidJUnit4.class)
public class UiTest {

    @Rule
    public ActivityTestRule<LoginUnitTestActivity> mActivityRule =
            new ActivityTestRule<>(LoginUnitTestActivity.class);

    @Test
    public void checkToastWhenBlankPassword() {
        // type text and press button.
        Espresso.onView(withId(R.id.edtUserName))
                .perform(typeText("Tienhuynh27"), closeSoftKeyboard());
    }
}
