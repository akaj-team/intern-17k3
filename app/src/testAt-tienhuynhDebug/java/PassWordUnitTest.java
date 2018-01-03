import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import vn.asiantech.internship.unittest.PasswordValidation;
import vn.asiantech.internship.unittest.User;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
@RunWith(MockitoJUnitRunner.class)
public class PassWordUnitTest {

    @Mock
    private User mUser;

    @Test
    public void checkDifferentUserName() {
        Mockito.when(mUser.getUser()).thenReturn("tienhuynh");
        Mockito.when(mUser.getPassword()).thenReturn("tiennguyen");
        Assert.assertTrue(PasswordValidation.isDifferentUserName(mUser.getUser(), mUser.getPassword()));
        Mockito.when(mUser.getUser()).thenReturn("tienhuinh");
        Mockito.when(mUser.getPassword()).thenReturn("tienhuinh");
        Assert.assertFalse(PasswordValidation.isDifferentUserName(mUser.getUser(), mUser.getPassword()));
    }

    @Test
    public void checkPasswordSpace() {
        Mockito.when(mUser.getPassword()).thenReturn("");
        Assert.assertTrue(PasswordValidation.isPasswordSpace(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn(" ");
        Assert.assertFalse(PasswordValidation.isPasswordSpace(mUser.getPassword()));
    }

    @Test
    public void checkAtLeastNumber() {
        Mockito.when(mUser.getPassword()).thenReturn("tienhuynh2%");
        Assert.assertTrue(PasswordValidation.isAtLeastNumber(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("tienhuenh");
        Assert.assertFalse(PasswordValidation.isAtLeastNumber(mUser.getPassword()));
    }

    @Test
    public void checkCapitalLetter() {
        Mockito.when(mUser.getPassword()).thenReturn("HUYtien");
        Assert.assertTrue(PasswordValidation.isCapitalLetter(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("tienhumnh");
        Assert.assertFalse(PasswordValidation.isCapitalLetter(mUser.getPassword()));
    }

    @Test
    public void checkLength() {
        Mockito.when(mUser.getPassword()).thenReturn("HUYtien27");
        Assert.assertTrue(PasswordValidation.isLength(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("ti");
        Assert.assertFalse(PasswordValidation.isLength(mUser.getPassword()));
    }

    @Test
    public void checkRepeatCharacter() {
        Mockito.when(mUser.getPassword()).thenReturn("Tien270197");
        Assert.assertTrue(PasswordValidation.isRepeatCharacter(mUser.getPassword()));
        Mockito.when(mUser.getPassword()).thenReturn("aabccc");
        Assert.assertFalse(PasswordValidation.isRepeatCharacter(mUser.getPassword()));
    }
}
