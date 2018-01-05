package vn.asiantech.internship.tungnguyen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.model.User;
import vn.asiantech.internship.ui.validation.PasswordValidation;


/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordUnitTest {

    @Mock
    private User mUser;

    @Test
    public void checkPasswordDifferentUser() {
        Mockito.when(mUser.getUserName()).thenReturn("Tungkute");
        Mockito.when(mUser.getPassword()).thenReturn("Tungkute");
        Assert.assertFalse(PasswordValidation.isCheckPasswordDifferentUser(mUser.getUserName(), mUser.getPassword()));
        Mockito.when(mUser.getUserName()).thenReturn("Tunghandsome");
        Mockito.when(mUser.getPassword()).thenReturn("TunghandSome");
        Assert.assertTrue(PasswordValidation.isCheckPasswordDifferentUser(mUser.getUserName(), mUser.getPassword()));
    }

    @Test
    public void checkPassSpecial() {
        Mockito.when(mUser.getPassword()).thenReturn("tung");
        Assert.assertTrue(PasswordValidation.isCheckPassSpecial(mUser.getPassword()));
    }

    @Test
    public void checkPassLenght() {
        Mockito.when(mUser.getPassword()).thenReturn("12345678");
        Assert.assertTrue(PasswordValidation.isCheckPassLenght(mUser.getPassword()));
    }

    @Test
    public void checkPassRepair() {
        Mockito.when(mUser.getPassword()).thenReturn("Tungkute");
        Assert.assertTrue(PasswordValidation.isCheckPassRepeat(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("SSFFAA");
        Assert.assertFalse(PasswordValidation.isCheckPassRepeat(mUser.getPassword()));
    }

    @Test
    public void checkPassSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("");
        Assert.assertTrue(PasswordValidation.isPasswordSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn(" ");
        Assert.assertFalse(PasswordValidation.isPasswordSpace(mUser.getPassword()));
    }

    @Test
    public void checkPassCap() {
        Mockito.when(mUser.getPassword()).thenReturn("ABAsd");
        Assert.assertTrue(PasswordValidation.isCheckPassCapital(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("asdasd");
        Assert.assertFalse(PasswordValidation.isCheckPassCapital(mUser.getPassword()));
    }
}
