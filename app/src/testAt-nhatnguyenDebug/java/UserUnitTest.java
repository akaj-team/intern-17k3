import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import vn.asiantech.internship.models.TestUser;
import vn.asiantech.internship.ui.unittest.ValidatePassWord;
import vn.asiantech.internship.ui.unittest.ValidateUserName;

/**
 * Class UnitTest
 * Created by hoangnhat on 03/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUnitTest {

    @Mock
    private TestUser mTestUser;

    @Test
    public void checkUserNameLikePassword() {
        Mockito.when(mTestUser.getUserName()).thenReturn("nhat");
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat");
        Assert.assertTrue(ValidatePassWord.isPassWordLikeUserName(mTestUser.getUserName(), mTestUser.getPassword()));
        Mockito.when(mTestUser.getUserName()).thenReturn("nhat");
        Mockito.when(mTestUser.getPassword()).thenReturn("nguyen");
        Assert.assertFalse(ValidatePassWord.isPassWordLikeUserName(mTestUser.getUserName(), mTestUser.getPassword()));
    }

    @Test
    public void checkSpecialNumberPassWord() {
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat**");
        Assert.assertTrue(ValidatePassWord.isSpecialNumberPassWord(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat1");
        Assert.assertTrue(ValidatePassWord.isSpecialNumberPassWord(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("nhatnguyen");
        Assert.assertFalse(ValidatePassWord.isSpecialNumberPassWord(mTestUser.getPassword()));
    }

    @Test
    public void checkLengthPassWord() {
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat");
        Assert.assertFalse(ValidatePassWord.isLengthPassWord(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("nhatnguyen");
        Assert.assertTrue(ValidatePassWord.isLengthPassWord(mTestUser.getPassword()));
    }

    @Test
    public void checkAppearCase() {
        Mockito.when(mTestUser.getPassword()).thenReturn("nhatnguyen");
        Assert.assertFalse(ValidatePassWord.isAppearCase(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat");
        Assert.assertTrue(ValidatePassWord.isAppearCase(mTestUser.getPassword()));
    }

    @Test
    public void checkSpaceCase() {
        Mockito.when(mTestUser.getPassword()).thenReturn("abc asasd");
        Assert.assertFalse(ValidatePassWord.isSpaceCase(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("nhat");
        Assert.assertTrue(ValidatePassWord.isSpaceCase(mTestUser.getPassword()));
    }

    @Test
    public void checkLeastThreeUpperCase() {
        Mockito.when(mTestUser.getPassword()).thenReturn("abc");
        Assert.assertFalse(ValidatePassWord.isLeastThreeUpperCase(mTestUser.getPassword()));
        Mockito.when(mTestUser.getPassword()).thenReturn("abcABCD");
        Assert.assertTrue(ValidatePassWord.isLeastThreeUpperCase(mTestUser.getPassword()));
    }

    @Test
    public void checkLengthUserName() {
        Mockito.when(mTestUser.getUserName()).thenReturn("abc");
        Assert.assertFalse(ValidateUserName.isLengthUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("nhatnguyen");
        Assert.assertTrue(ValidateUserName.isLengthUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("nhatnguyennhatnguyennhatnguyen");
        Assert.assertFalse(ValidateUserName.isLengthUserName(mTestUser.getUserName()));
    }

    @Test
    public void checkUpperCaseUserName() {
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen");
        Assert.assertTrue(ValidateUserName.isUpperCaseUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("nhatnguyen");
        Assert.assertFalse(ValidateUserName.isUpperCaseUserName(mTestUser.getUserName()));
    }

    @Test
    public void checkSpecialCaseUserName() {
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen");
        Assert.assertTrue(ValidateUserName.isSpecialCaseUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen*");
        Assert.assertFalse(ValidateUserName.isSpecialCaseUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen ");
        Assert.assertFalse(ValidateUserName.isSpecialCaseUserName(mTestUser.getUserName()));
    }

    @Test
    public void checkNumberUserName() {
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen1");
        Assert.assertTrue(ValidateUserName.isNumberUserName(mTestUser.getUserName()));
        Mockito.when(mTestUser.getUserName()).thenReturn("Nhatnguyen123");
        Assert.assertFalse(ValidateUserName.isNumberUserName(mTestUser.getUserName()));
    }

    @Test
    public void isUserNameUpperCaseOrLowerCase() {
        Mockito.when(mTestUser.getUserName()).thenReturn("NhatnguyEn");
        Assert.assertTrue(ValidateUserName.isUserNameUpperCaseOrLowerCase(mTestUser.getUserName()));
    }
}
