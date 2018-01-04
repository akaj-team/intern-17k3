import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.Login;
import vn.asiantech.internship.unit_test.UserNameValidation;

import static org.mockito.Mockito.when;

/**
 * Test user name
 * Created by tiboo on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {
    private static final String TEXT_TEST = "CapHieU12";
    @Mock
    private Login mLogIn;

    @Test
    public void testLength() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UserNameValidation.checkLength(mLogIn.getUser()));
    }

    @Test
    public void testCapitalLetter() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UserNameValidation.checkCapitalLetter(mLogIn.getUser()));
    }

    @Test
    public void testCharacters() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UserNameValidation.checkCharacters(mLogIn.getUser()));
    }

    @Test
    public void testHaveSpace() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UserNameValidation.checkHaveSpace(mLogIn.getUser()));
    }

    @Test
    public void testDigitNumber() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);

        Assert.assertTrue(UserNameValidation.checkDigitNumber(mLogIn.getUser()));
    }

    @Test
    public void testUpperCaseOrLowerCase() {
        when(mLogIn.getUser()).thenReturn(TEXT_TEST);
        Assert.assertTrue(UserNameValidation.checkUpperCaseOrLowerCase(mLogIn.getUser()));
    }
}
