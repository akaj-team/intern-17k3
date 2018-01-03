package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import vn.asiantech.internship.models.UserTesting;
import vn.asiantech.internship.ui.unittest.UserValidation;

import static org.mockito.Mockito.when;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserUnitTest
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {
    @Mock
    private UserTesting mUser;

    @Test
    public void checkLengthUserName() {
        when(mUser.getUserName()).thenReturn("admin123");
        Assert.assertTrue(UserValidation.checkLengthUserName(mUser.getUserName()));
    }

    @Test
    public void checkSpecialCharAndSpaceUserName() {
        when(mUser.getUserName()).thenReturn("admin");
        Assert.assertTrue(UserValidation.checkSpecialCharAndSpaceUserName(mUser.getUserName()));
    }

    @Test
    public void checkUpperCaseLowercaseUserName() {
        when(mUser.getUserName()).thenReturn("aDmIn");
        Assert.assertTrue(UserValidation.checkUpperCaseLowercaseUserName(mUser.getUserName()));
    }

    @Test
    public void checkLengthPassword() {
        when(mUser.getPassword()).thenReturn("admin123");
        Assert.assertTrue(UserValidation.checkLengthPassword(mUser.getPassword()));
    }

    @Test
    public void checkSpacePassword() {
        when(mUser.getPassword()).thenReturn("");
        Assert.assertTrue(UserValidation.checkSpacePassword(mUser.getPassword()));
    }
}
