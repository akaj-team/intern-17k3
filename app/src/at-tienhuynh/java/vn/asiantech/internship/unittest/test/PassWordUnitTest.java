package vn.asiantech.internship.unittest.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import vn.asiantech.internship.unittest.PasswordValidation;
import vn.asiantech.internship.unittest.User;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
@RunWith(MockitoJUnitRunner.class)
public class PassWordUnitTest {

    @Mock
    private User mUser;

    @Test
    public void checkDifferentUserName() {
        Mockito.when(mUser.getUser()).thenReturn("tienhuynh");
        Mockito.when(mUser.getPassword()).thenReturn("tiennguyen");
        Assert.assertTrue(PasswordValidation.checkDifferentUserName(mUser.getUser(), mUser.getPassword()));
        Mockito.when(mUser.getUser()).thenReturn("tienhuinh");
        Mockito.when(mUser.getPassword()).thenReturn("tienhuinh");
        Assert.assertFalse(PasswordValidation.checkDifferentUserName(mUser.getUser(), mUser.getPassword()));
    }

    @Test
    public void checkPasswordSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("");
        Assert.assertTrue(PasswordValidation.checkPasswordSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn(" ");
        Assert.assertFalse(PasswordValidation.checkPasswordSpace(mUser.getPassword()));
    }

    @Test
    public void checkAtLeastNumber() {
        Mockito.when(mUser.getPassword()).thenReturn("tienhuynh2%");
        Assert.assertTrue(PasswordValidation.checkAtLeastNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("tienhuenh");
        Assert.assertFalse(PasswordValidation.checkAtLeastNumber(mUser.getPassword()));
    }

    @Test
    public void checkCapitalLetter() {
        Mockito.when(mUser.getPassword()).thenReturn("HUYtien");
        Assert.assertTrue(PasswordValidation.checkCapitalLetter(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("tienhumnh");
        Assert.assertFalse(PasswordValidation.checkCapitalLetter(mUser.getPassword()));
    }

    @Test
    public void checkLength() {
        Mockito.when(mUser.getPassword()).thenReturn("HUYtien27");
        Assert.assertTrue(PasswordValidation.checkLength(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ti");
        Assert.assertFalse(PasswordValidation.checkLength(mUser.getPassword()));
    }

    @Test
    public void checkRepeatCharacter() {
        Mockito.when(mUser.getPassword()).thenReturn("Tien270197");
        Assert.assertTrue(PasswordValidation.checkRepeatCharacter(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("HuuuTien");
        Assert.assertFalse(PasswordValidation.checkRepeatCharacter(mUser.getPassword()));
    }
}
