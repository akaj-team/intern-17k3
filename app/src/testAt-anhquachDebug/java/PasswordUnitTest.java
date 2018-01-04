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
    public void isDifferentUserName() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.isDifferentUserName(mUser.getPassword(), "ngocanhH"));
        Mockito.when(mUser.getPassword()).thenReturn("ngocaH");
        Assert.assertFalse(PasswordValidation.isDifferentUserName(mUser.getPassword(), "ngocaH"));
    }

    @Test
    public void isSpecialCharOrNumber() {
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh");
        Assert.assertTrue(PasswordValidation.isSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh12");
        Assert.assertTrue(PasswordValidation.isSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana@ngocanh7");
        Assert.assertTrue(PasswordValidation.isSpecialCharOrNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.isSpecialCharOrNumber(mUser.getPassword()));
    }

    @Test
    public void isPasswordLenght() {
        Mockito.when(mUser.getPassword()).thenReturn("hanangocanh");
        Assert.assertFalse(PasswordValidation.isPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hana");
        Assert.assertFalse(PasswordValidation.isPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("hanaHH");
        Assert.assertFalse(PasswordValidation.isPasswordLenght(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("asdfghjkl");
        Assert.assertTrue(PasswordValidation.isPasswordLenght(mUser.getPassword()));
    }

    @Test
    public void isPassSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh2");
        Assert.assertTrue(PasswordValidation.isPassSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngoc anh");
        Assert.assertFalse(PasswordValidation.isPassSpace(mUser.getPassword()));
    }

    @Test
    public void isMostThreeCapitalChar() {
        Mockito.when(mUser.getPassword()).thenReturn("ngocanh");
        Assert.assertTrue(PasswordValidation.isMostThreeCapitalChar(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ngocanhHHHH");
        Assert.assertFalse(PasswordValidation.isMostThreeCapitalChar(mUser.getPassword()));
    }
}
