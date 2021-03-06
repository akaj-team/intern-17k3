import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.UserTesting;
import vn.asiantech.internship.ui.unittest.UserValidation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserUnitTest
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {
    @Mock
    private UserTesting mUser;

    @Test
    public void checkLengthUserName() {
        when(mUser.getUserName()).thenReturn("admin123");
        assertTrue(UserValidation.isLengthUserName(mUser.getUserName()));
    }

    @Test
    public void checkLeastOneCapitalLetterUserName() {
        when(mUser.getUserName()).thenReturn("Admin");
        assertTrue(UserValidation.isLeastOneCapitalLetterUserName(mUser.getUserName()));
    }

    @Test
    public void checkSpecialCharAndSpaceUserName() {
        when(mUser.getUserName()).thenReturn("admin");
        assertTrue(UserValidation.isSpecialCharAndSpaceUserName(mUser.getUserName()));
    }

    @Test
    public void checkMostTwoNumberUserName() {
        when(mUser.getUserName()).thenReturn("12admin");
        assertTrue(UserValidation.isMostTwoNumberUserName(mUser.getUserName()));
    }

    @Test
    public void checkUpperCaseLowercaseUserName() {
        when(mUser.getUserName()).thenReturn("aDmIn");
        assertTrue(UserValidation.isUpperCaseLowercaseUserName(mUser.getUserName()));
    }

    @Test
    public void checkPasswordDifferentUserName() {
        when(mUser.getPassword()).thenReturn("passAdmin");
        when(mUser.getUserName()).thenReturn("admin");
        assertTrue(UserValidation.isPasswordDifferentUserName(mUser.getUserName(), mUser.getPassword()));
    }

    @Test
    public void checkAtLeastSpecialOrNumber() {
        when(mUser.getPassword()).thenReturn("_asd123");
        assertTrue(UserValidation.isAtLeastSpecialOrNumber(mUser.getPassword()));
    }

    @Test
    public void checkLengthPassword() {
        when(mUser.getPassword()).thenReturn("admin123");
        assertTrue(UserValidation.isLengthPassword(mUser.getPassword()));
    }

    @Test
    public void checkRepeatCharacterPassword() {
        when(mUser.getPassword()).thenReturn("adminn");
        assertTrue(!UserValidation.isRepeatCharacterPassword(mUser.getPassword()));
    }

    @Test
    public void checkSpacePassword() {
        when(mUser.getPassword()).thenReturn("");
        assertTrue(UserValidation.isSpacePassword(mUser.getPassword()));
    }

    @Test
    public void checkLestThreeCharacters() {
        when(mUser.getPassword()).thenReturn("ADMin");
        assertTrue(UserValidation.isLestThreeCharacters(mUser.getPassword()));
    }
}
