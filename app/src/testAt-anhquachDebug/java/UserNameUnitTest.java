import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.model.User;
import vn.asiantech.internship.validation.UserNameValidation;

/**
 * Created by anh.quach on 1/2/18.
 * User Name Unit Test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserNameUnitTest {
    @Mock
    private User mUser;

    @Test
    public void checkUserNameLength() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh");
        Assert.assertTrue(UserNameValidation.isCorrectUserNameLength(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("hana");
        Assert.assertFalse(UserNameValidation.isCorrectUserNameLength(mUser.getUsename()));
    }

    @Test
    public void checkCapitalUserName() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanhH");
        Assert.assertTrue(UserNameValidation.isHaveAtLeastACapitalUserName(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("anh");
        Assert.assertFalse(UserNameValidation.isHaveAtLeastACapitalUserName(mUser.getUsename()));
    }

    @Test
    public void checkContainSpecialChar() {
        Mockito.when(mUser.getUsename()).thenReturn("hanangocanh");
        Assert.assertTrue(UserNameValidation.isContainSpecialChar(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("!@#$%^ &*()");
        Assert.assertFalse(UserNameValidation.isContainSpecialChar(mUser.getUsename()));
    }

    @Test
    public void checkUserNameIgnoreUpperCase() {
        Mockito.when(mUser.getUsename()).thenReturn("abcd");
        Assert.assertTrue(UserNameValidation.isUserNameIgnoreUpperCase(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("Abcd");
        Assert.assertFalse(UserNameValidation.isUserNameIgnoreUpperCase(mUser.getUsename()));
    }

    @Test
    public void checkMostTwoDigits() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh97");
        Assert.assertTrue(UserNameValidation.isMostTwoDigits(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh1997");
        Assert.assertFalse(UserNameValidation.isMostTwoDigits(mUser.getUsename()));
    }
}
