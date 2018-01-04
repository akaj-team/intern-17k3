import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Login;
import vn.asiantech.internship.unit_test.UsernameValidationTest;

import static org.mockito.Mockito.when;

/**
 * Test user name
 * Created by tiboo on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class UsernameUnitTest {
    private static final String TEXT_TEST = "CapHieU12";
    @Mock
    private Login mLogIn;

    @Test
    public void testLength() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkLength(mLogIn.getUser()));
    }

    @Test
    public void testCapitalLetter() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkCapitalLetter(mLogIn.getUser()));
    }

    @Test
    public void testCharacters() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkCharacters(mLogIn.getUser()));
    }

    @Test
    public void testHaveSpace() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkHaveSpace(mLogIn.getUser()));
    }

    @Test
    public void testDigitNumber() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkDigitNumber(mLogIn.getUser()));
    }

    @Test
    public void testUpperCaseOrLowerCase() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UsernameValidationTest.checkUpperCaseOrLowerCase(mLogIn.getUser()));
    }
}
