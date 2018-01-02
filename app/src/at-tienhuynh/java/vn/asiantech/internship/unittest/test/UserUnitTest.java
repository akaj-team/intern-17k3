package vn.asiantech.internship.unittest.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import vn.asiantech.internship.unittest.User;
import vn.asiantech.internship.unittest.UserNameValidation;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {

    @Mock
    private User mUser;

    @Test
    public void checkLengthUserName() {
        Mockito.when(mUser.getUser()).thenReturn("tienhuuynh");
        Assert.assertTrue(UserNameValidation.checkLengthUserName(mUser.getUser()));
        Mockito.when(mUser.getUser()).thenReturn("tien");
        Assert.assertFalse(UserNameValidation.checkLengthUserName(mUser.getUser()));
    }

    @Test
    public void checkCapitalLetterAndNumber() {
        Mockito.when(mUser.getUser()).thenReturn("Tien27HH");
        Assert.assertTrue(UserNameValidation.checkCapitalLetterAndNumber(mUser.getUser()));
        Mockito.when(mUser.getUser()).thenReturn("tixn");
        Assert.assertFalse(UserNameValidation.checkCapitalLetterAndNumber(mUser.getUser()));
    }

    @Test
    public void checkUserNameSpace() {
        Mockito.when(mUser.getUser()).thenReturn("");
        Assert.assertTrue(UserNameValidation.checkUserNameSpace(mUser.getUser()));
        Mockito.when(mUser.getUser()).thenReturn(" ");
        Assert.assertFalse(UserNameValidation.checkUserNameSpace(mUser.getUser()));
    }

    @Test
    public void checkUserNameSpecialChar() {
        Mockito.when(mUser.getUser()).thenReturn("tivn");
        Assert.assertTrue(UserNameValidation.checkUserNameSpecialChar(mUser.getUser()));
        Mockito.when(mUser.getUser()).thenReturn("&*(=");
        Assert.assertFalse(UserNameValidation.checkUserNameSpecialChar(mUser.getUser()));
    }

    @Test
    public void checkUserNameIgnoreUpperCase() {
        Mockito.when(mUser.getUser()).thenReturn("tiEn");
        Assert.assertTrue(UserNameValidation.checkUserNameIgnoreUpperCase(mUser.getUser()));
        Mockito.when(mUser.getUser()).thenReturn("tibn");
        Assert.assertFalse(UserNameValidation.checkUserNameIgnoreUpperCase(mUser.getUser()));
    }
}
