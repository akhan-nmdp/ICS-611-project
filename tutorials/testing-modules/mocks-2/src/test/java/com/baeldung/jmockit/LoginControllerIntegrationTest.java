package com.baeldung.jmockit;

import org.junit.Assert;
import org.junit.Test;

import com.baeldung.testCase.LoginController;
import com.baeldung.testCase.LoginDao;
import com.baeldung.testCase.LoginService;
import com.baeldung.testCase.UserForm;

import mockit.Delegate;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class LoginControllerIntegrationTest {

    @Injectable
    private LoginDao loginDao;

    @Injectable
    private LoginService loginService;

    @Tested
    private LoginController loginController;

    @Test
    public void assertThatNoMethodHasBeenCalled() {
        loginController.login(null);
        // no method called
        new FullVerifications(loginService) {
        };
    }

    @Test
    public void assertTwoMethodsHaveBeenCalled() {
        final UserForm userForm = new UserForm();
        userForm.username = "foo";
        new Expectations() {{
            loginService.login(userForm);
            result = true;
            loginService.setCurrentUser("foo");
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        new FullVerifications(loginService) {
        };
    }

    @Test
    public void assertOnlyOneMethodHasBeenCalled() {
        final UserForm userForm = new UserForm();
        userForm.username = "foo";
        new Expectations() {{
            loginService.login(userForm);
            result = false;
            // no expectation for setCurrentUser
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("KO", login);
        new FullVerifications(loginService) {
        };
    }

    @Test
    public void mockExceptionThrowing() {
        final UserForm userForm = new UserForm();
        new Expectations() {{
            loginService.login(userForm);
            result = new IllegalArgumentException();
            // no expectation for setCurrentUser
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("ERROR", login);
        new FullVerifications(loginService) {
        };
    }

    @Test
    public void mockAnObjectToPassAround(@Mocked final UserForm userForm) {
        new Expectations() {{
            userForm.getUsername();
            result = "foo";
            loginService.login(userForm);
            result = true;
            loginService.setCurrentUser("foo");
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        new FullVerifications(loginService) {
        };
        new FullVerifications(userForm) {
        };
    }

    @Test
    public void argumentMatching() {
        final UserForm userForm = new UserForm();
        userForm.username = "foo";
        // default matcher
        new Expectations() {{
            loginService.login((UserForm) any);
            result = true;
            // complex matcher
            loginService.setCurrentUser(with(new Delegate<String>() {
                public boolean matches(Object item) {
                    return item instanceof String && ((String) item).startsWith("foo");
                }
            }));
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        new FullVerifications(loginService) {
        };
    }

    
    @Test
    public void partialMocking() {
        LoginService partialLoginService = new LoginService();
        partialLoginService.setLoginDao(loginDao);
        loginController.loginService = partialLoginService;

        UserForm userForm = new UserForm();
        userForm.username = "foo";
        
        new Expectations(partialLoginService) {{
            // let's mock DAO call 
            loginDao.login(userForm); result = 1; 
            
            // no expectation for login method so that real implementation is used 
            
            // mock setCurrentUser call 
            partialLoginService.setCurrentUser("foo");
        }};

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        // verify mocked call 
        new Verifications() {{
            partialLoginService.setCurrentUser("foo");
        }};

    }
}
