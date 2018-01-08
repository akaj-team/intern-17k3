package vn.asiantech.internship.tungnguyen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.model.User;
import vn.asiantech.internship.ui.validation.UserValidation;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 04/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {

    @Mock
    private User mUser;

    @Test
    public void checkUserLenght() {
        Mockito.when(mUser.getUserName()).thenReturn("abcabc");
        Assert.assertTrue(UserValidation.ischeckUserLenght(mUser.getUserName()));
        Mockito.when(mUser.getUserName()).thenReturn("abcab");
        Assert.assertFalse(UserValidation.ischeckUserLenght(mUser.getUserName()));
    }

    @Test
    public void checkUserSpecial() {
        Mockito.when(mUser.getUserName()).thenReturn("asd");
        Assert.assertTrue(UserValidation.isCheckUserSpecial(mUser.getUserName()));
        Mockito.when(mUser.getUserName()).thenReturn("Sopa$$$");
        Assert.assertFalse(UserValidation.isCheckUserSpecial(mUser.getUserName()));
    }

    @Test
    public void checkUserSpace() {
        Mockito.when(mUser.getUserName()).thenReturn("");
        Assert.assertTrue(UserValidation.isCheckUserSpace(mUser.getUserName()));
        Mockito.when(mUser.getUserName()).thenReturn(" ");
        Assert.assertFalse(UserValidation.isCheckUserSpace(mUser.getUserName()));
    }

    @Test
    public void checkUserSpecialAndNumber() {
        Mockito.when(mUser.getUserName()).thenReturn("Tung12");
        Assert.assertTrue(UserValidation.isCheckUserCapitalAndNumber(mUser.getUserName()));
        Mockito.when(mUser.getUserName()).thenReturn("tung1");
        Assert.assertFalse(UserValidation.isCheckUserCapitalAndNumber(mUser.getUserName()));
    }

    @Test
    public void checkUserNameSpecialChar() {
        Mockito.when(mUser.getUserName()).thenReturn("TungkutE");
        Assert.assertTrue(UserValidation.isUserNameUpperCaseOrLowerCase(mUser.getUserName()));
    }
}
