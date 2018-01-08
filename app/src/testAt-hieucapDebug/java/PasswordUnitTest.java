import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Login;
import vn.asiantech.internship.unit_test.PasswordValidationTest;

import static org.mockito.Mockito.when;

/**
 * Test validation password
 * Created by tiboo on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordUnitTest {
    private static final String TEXT_TEST = "Ca%apHieU";
    @Mock
    private Login mLogIn;

    @Test
    public void testDifferentUserName() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertTrue(PasswordValidationTest.isDifferentUserName(mLogIn.getUser(), mLogIn.getPassword()));
    }

    @Test
    public void testCharacterSpecialAndDigitNumber() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertFalse(PasswordValidationTest.isCharacterSpecialAndDigitNumber(mLogIn.getPassword()));
    }

    @Test
    public void testLength() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertTrue(PasswordValidationTest.isLengthAllowed(mLogIn.getPassword()));
    }

    @Test
    public void testSameCharacter() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertTrue(PasswordValidationTest.isSameCharacter(mLogIn.getPassword()));
    }

    @Test
    public void testSpace() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertTrue(PasswordValidationTest.isHaveSpace(mLogIn.getPassword()));
    }

    @Test
    public void testCapitalLetter() {
        when(mLogIn.getPassword()).thenReturn(TEXT_TEST);
        Assert.assertTrue(PasswordValidationTest.isCapitalLetter(mLogIn.getPassword()));
    }
}
