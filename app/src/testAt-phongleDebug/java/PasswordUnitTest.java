import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Account;
import vn.asiantech.internship.unittest.ValidatePassword;

/**
 * Created by phongle on 4/1/2561.
 * Class UnitTest Password
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordUnitTest {
    @Mock
    private Account mAccount;

    @Test
    public void checkDifferenceUsername() {
        Mockito.when(mAccount.getUsername()).thenReturn("Phongle");
        Mockito.when(mAccount.getPassword()).thenReturn("Phongle");
        Assert.assertFalse(ValidatePassword.isDifferenceUsername(mAccount.getUsername(), mAccount.getPassword()));
        Mockito.when(mAccount.getUsername()).thenReturn("Phonglet");
        Mockito.when(mAccount.getPassword()).thenReturn("Phonglethanh");
        Assert.assertTrue(ValidatePassword.isDifferenceUsername(mAccount.getUsername(), mAccount.getPassword()));
    }

    @Test
    public void checkLengthPassword() {
        Mockito.when(mAccount.getPassword()).thenReturn("admin");
        Assert.assertFalse(ValidatePassword.isCheckMinLength(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("adminPhong");
        Assert.assertTrue(ValidatePassword.isCheckMinLength(mAccount.getPassword()));
    }

    @Test
    public void checkAtLeastSpecialCharOrNum() {
        Mockito.when(mAccount.getPassword()).thenReturn("phongleth");
        Assert.assertFalse(ValidatePassword.isAtLeastSpecialCharOrNumber(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("phongle1");
        Assert.assertTrue(ValidatePassword.isAtLeastSpecialCharOrNumber(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("phongle@");
        Assert.assertTrue(ValidatePassword.isAtLeastSpecialCharOrNumber(mAccount.getPassword()));
    }

    @Test
    public void checkAtThreeUppercase() {
        Mockito.when(mAccount.getPassword()).thenReturn("phongleTH");
        Assert.assertFalse(ValidatePassword.isAtLeastThreeUpperCase(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("phongTHA");
        Assert.assertTrue(ValidatePassword.isAtLeastThreeUpperCase(mAccount.getPassword()));
    }

    @Test
    public void checkNonRepeat() {
        Mockito.when(mAccount.getPassword()).thenReturn("lelelephong");
        Assert.assertFalse(ValidatePassword.isNonRepeat(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("lephongle");
        Assert.assertTrue(ValidatePassword.isNonRepeat(mAccount.getPassword()));
    }

    @Test
    public void checkNonSpace() {
        Mockito.when(mAccount.getPassword()).thenReturn("admin cui");
        Assert.assertFalse(ValidatePassword.isNonWhiteSpace(mAccount.getPassword()));
        Mockito.when(mAccount.getPassword()).thenReturn("admincuibap");
        Assert.assertTrue(ValidatePassword.isNonWhiteSpace(mAccount.getPassword()));
    }
}
