import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import vn.asiantech.internship.ui.login.LoginActivity;

/**
 * Created by anh.quach on 1/4/18.
 * Sign up ui test
 */
@RunWith(AndroidJUnit4.class)
public class SignupUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void checkToastWhenBlankPassword() {

}
