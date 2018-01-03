package vn.asiantech.internship.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import vn.asiantech.internship.model.User;
import vn.asiantech.internship.validation.PasswordValidation;

/**
 * Created by anh.quach on 1/3/18.
 * password unit test
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PasswordUnitTest {
    @Mock
    private User mUser;

    @Test
    public void checkDifferentUserName() {
        Mockito.when(mUser.getUsename()).thenReturn("ngocanH");
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.checkDifferentUserName(mUser.getPassword(), mUser.getUsename()));
        Mockito.when(mUser.getUsename()).thenReturn("ngocanH");
        Mockito.when(mUser.getPassword()).thenReturn("ngocanH");
        Assert.assertFalse(PasswordValidation.checkDifferentUserName(mUser.getPassword(), mUser.getUsename()));
    }

    @Test
    public void checkSpecialCharOrNumber() {
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh");
        Assert.assertTrue(PasswordValidation.checkSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh12");
        Assert.assertTrue(PasswordValidation.checkSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh7");
        Assert.assertTrue(PasswordValidation.checkSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.checkSpecialCharOrNumber(mUser.getPassword()));
    }

    @Test
    public void checkPasswordLenght() {
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.checkPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana");
        Assert.assertFalse(PasswordValidation.checkPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanaHH");
        Assert.assertFalse(PasswordValidation.checkPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("anhquach123");
        Assert.assertTrue(PasswordValidation.checkPasswordLenght(mUser.getPassword()));
    }

    @Test
    public void checkPassSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh2");
        Assert.assertTrue(PasswordValidation.checkPassSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngoc anh");
        Assert.assertFalse(PasswordValidation.checkPassSpace(mUser.getPassword()));
    }

    @Test
    public void checkMostThreeCapitalChar() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.checkMostThreeCapitalChar(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngocanhHHHH");
        Assert.assertFalse(PasswordValidation.checkMostThreeCapitalChar(mUser.getPassword()));
    }
}
