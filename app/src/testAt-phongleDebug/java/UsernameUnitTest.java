import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Account;
import vn.asiantech.internship.unittest.ValidateUsername;

/**
 * Created by phongle on 4/1/2561.
 * Class unit test username
 */
@RunWith(MockitoJUnitRunner.class)
public class UsernameUnitTest {
    @Mock
    private Account mAccount;

    @Test
    public void checkLengthUsername() {
        Mockito.when(mAccount.getUsername()).thenReturn("user");
        Assert.assertFalse(ValidateUsername.checkLengthUsername(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("useruseruseruseruseruseruseruseruseruseruseruseruseruser");
        Assert.assertFalse(ValidateUsername.checkLengthUsername(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("user01");
        Assert.assertTrue(ValidateUsername.checkLengthUsername(mAccount.getUsername()));
    }

    @Test
    public void checkAtLeastUppercaseUsername() {
        Mockito.when(mAccount.getUsername()).thenReturn("ronaldo");
        Assert.assertFalse(ValidateUsername.isAtLeastUpperCase(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("Ronaldo");
        Assert.assertTrue(ValidateUsername.isAtLeastUpperCase(mAccount.getUsername()));
    }

    @Test
    public void checkNonSpecialChar() {
        Mockito.when(mAccount.getUsername()).thenReturn("LionelMessi%");
        Assert.assertFalse(ValidateUsername.isNonSpecialChar(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("LionelMessi");
        Assert.assertTrue(ValidateUsername.isNonSpecialChar(mAccount.getUsername()));
    }

    @Test
    public void checkNonWhiteSpace() {
        Mockito.when(mAccount.getUsername()).thenReturn("cristiano ronaldo");
        Assert.assertFalse(ValidateUsername.isNonWhiteSpace(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("CristianoRonaldo");
        Assert.assertTrue(ValidateUsername.isNonWhiteSpace(mAccount.getUsername()));
    }

    @Test
    public void checkAtMostTwoNumber() {
        Mockito.when(mAccount.getUsername()).thenReturn("CR77777");
        Assert.assertFalse(ValidateUsername.isAtMostTwoNumber(mAccount.getUsername()));
        Mockito.when(mAccount.getUsername()).thenReturn("CR7");
        Assert.assertTrue(ValidateUsername.isAtMostTwoNumber(mAccount.getUsername()));
    }

    @Test
    public void checkUppercaseOrLowercase() {
        Mockito.when(mAccount.getUsername()).thenReturn("CrisTianoRoNaldo");
        Assert.assertTrue(ValidateUsername.isUserNameUpperCaseOrLowerCase(mAccount.getUsername()));
    }
}
