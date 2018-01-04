import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.model.User;
import vn.asiantech.internship.validation.PasswordValidation;

/**
 * Created by anh.quach on 1/3/18.
 * password unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordUnitTest {
    @Mock
    private User mUser;

    @Test
    public void checkDifferentUserName() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.checkDifferentUserName(mUser.getPassword(), "ngocanhH"));
        Mockito.when(mUser.getPassword()).thenReturn("ngocaH");
        Assert.assertFalse(PasswordValidation.checkDifferentUserName(mUser.getPassword(), "ngocaH"));
    }

    @Test
    public void isContainSpecialCharOrNumber() {
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh");
        Assert.assertTrue(PasswordValidation.isContainSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh12");
        Assert.assertTrue(PasswordValidation.isContainSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh7");
        Assert.assertTrue(PasswordValidation.isContainSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.isContainSpecialCharOrNumber(mUser.getPassword()));
    }

    @Test
    public void isIncorrectPasswordLenght() {
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.isCorrectPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana");
        Assert.assertFalse(PasswordValidation.isCorrectPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanaHH");
        Assert.assertFalse(PasswordValidation.isCorrectPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("asdfghjkl");
        Assert.assertTrue(PasswordValidation.isCorrectPasswordLenght(mUser.getPassword()));
    }

    @Test
    public void isContainPasswordSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh2");
        Assert.assertTrue(PasswordValidation.isContainPasswordSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngoc anh");
        Assert.assertFalse(PasswordValidation.isContainPasswordSpace(mUser.getPassword()));
    }

    @Test
    public void isAtLeastThreeCapitalChar() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.isAtLeastThreeCapitalChar(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngocanhHHHH");
        Assert.assertFalse(PasswordValidation.isAtLeastThreeCapitalChar(mUser.getPassword()));
    }
}
