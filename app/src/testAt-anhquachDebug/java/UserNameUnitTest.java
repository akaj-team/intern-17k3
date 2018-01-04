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
    public void isUserNameLength() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh");
        Assert.assertTrue(UserNameValidation.isUserNameLength(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("hana");
        Assert.assertFalse(UserNameValidation.isUserNameLength(mUser.getUsename()));
    }

    @Test
    public void isCapitalUserName() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanhH");
        Assert.assertTrue(UserNameValidation.isCapitalUserName(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("anh");
        Assert.assertFalse(UserNameValidation.isCapitalUserName(mUser.getUsename()));
    }

    @Test
    public void isUserNameSpace() {
        Mockito.when(mUser.getUsename()).thenReturn("");
        Assert.assertTrue(UserNameValidation.isUserNameSpace(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn(" ");
        Assert.assertFalse(UserNameValidation.isUserNameSpace(mUser.getUsename()));
    }

    @Test
    public void isContainSpecialChar() {
        Mockito.when(mUser.getUsename()).thenReturn("hanangocanh");
        Assert.assertTrue(UserNameValidation.isContainSpecialChar(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("!@#$%^&*()");
        Assert.assertFalse(UserNameValidation.isContainSpecialChar(mUser.getUsename()));
    }

    @Test
    public void isUserNameIgnoreUpperCase() {
        Mockito.when(mUser.getUsename()).thenReturn("abcd");
        Assert.assertTrue(UserNameValidation.isUserNameIgnoreUpperCase(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("Abcd");
        Assert.assertFalse(UserNameValidation.isUserNameIgnoreUpperCase(mUser.getUsename()));
    }

    @Test
    public void isMostTwoDigits() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh97");
        Assert.assertTrue(UserNameValidation.isMostTwoDigits(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh1997");
        Assert.assertFalse(UserNameValidation.isMostTwoDigits(mUser.getUsename()));
    }
}
