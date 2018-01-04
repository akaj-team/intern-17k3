import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Login;
import vn.asiantech.internship.unit_test.PasswordValidation;

import static org.mockito.Mockito.when;

/**
 * Test validation password
 * Created by tiboo on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class passWordUnitTest {

    @Mock
    private Login mLogIn;

    @Test
    public void testDifferentUserName() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sad");
        Assert.assertTrue(PasswordValidation.checkDifferentUserName(mLogIn.getUser(), mLogIn.getPassword()));
    }

    @Test
    public void testCharacterSpecialAndDigitNumber() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sad");
        Assert.assertTrue(PasswordValidation.checkCharacterSpecialAndDigitNumber(mLogIn.getPassword()));
    }

    @Test
    public void testLength() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sad");
        Assert.assertTrue(PasswordValidation.checkLength(mLogIn.getPassword()));
    }

    @Test
    public void testSameCharacter() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sad");
        Assert.assertTrue(PasswordValidation.checkSameCharacter(mLogIn.getPassword()));
    }

    @Test
    public void testSpace() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sad");
        Assert.assertTrue(PasswordValidation.checkHaveSpace(mLogIn.getPassword()));
    }

    @Test
    public void testCapitalLetter() {
        when(mLogIn.getPassword()).thenReturn("dsad7ád7sAADad");
        Assert.assertTrue(PasswordValidation.checkCapitalLetter(mLogIn.getPassword()));
    }
}
