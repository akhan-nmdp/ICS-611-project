package com.baeldung.springvault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.vault.repository.configuration.EnableVaultRepositories;

/**
 * This live test requires:
 * vault server up and running on the environment
 * 
 * <br>
 * For more info on setting up the vault server:
 * https://www.baeldung.com/vault
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringVaultApplication.class)
@EnableVaultRepositories
public class SpringContextLiveTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
