package vn.asiantech.internship.anhquach;

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
        Assert.assertTrue(UserNameValidation.checkUserNameLength(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("hana");
        Assert.assertFalse(UserNameValidation.checkUserNameLength(mUser.getUsename()));
    }

    @Test
    public void checkCapitalUserName() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanhH");
        Assert.assertTrue(UserNameValidation.checkCapitalUserName(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("anh");
        Assert.assertFalse(UserNameValidation.checkCapitalUserName(mUser.getUsename()));
    }

    @Test
    public void checkUserNameSpace() {
        Mockito.when(mUser.getUsename()).thenReturn("");
        Assert.assertTrue(UserNameValidation.checkUserNameSpace(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn(" ");
        Assert.assertFalse(UserNameValidation.checkUserNameSpace(mUser.getUsename()));
    }

    @Test
    public void checkContainSpecialChar() {
        Mockito.when(mUser.getUsename()).thenReturn("hanangocanh");
        Assert.assertTrue(UserNameValidation.checkContainSpecialChar(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("!@#$%^&*()");
        Assert.assertFalse(UserNameValidation.checkContainSpecialChar(mUser.getUsename()));
    }

    @Test
    public void checkUserNameIgnoreUpperCase() {
        Mockito.when(mUser.getUsename()).thenReturn("abcd");
        Assert.assertTrue(UserNameValidation.checkUserNameIgnoreUpperCase(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("Abcd");
        Assert.assertFalse(UserNameValidation.checkUserNameIgnoreUpperCase(mUser.getUsename()));
    }

    @Test
    public void checkMostTwoDigits() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh97");
        Assert.assertTrue(UserNameValidation.checkMostTwoDigits(mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("ngocanh1997");
        Assert.assertFalse(UserNameValidation.checkMostTwoDigits(mUser.getUsename()));
    }
}
