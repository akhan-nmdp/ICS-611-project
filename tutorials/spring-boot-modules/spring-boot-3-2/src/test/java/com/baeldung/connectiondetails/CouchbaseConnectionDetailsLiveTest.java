/**
 * These test cases have dependency with docker because they pull the docker images from docker hub
 * and run the container. So, please make sure to install docker before running the tests.
 * For the image details please look into the docker-compose files under resources/connectiondetails/docker
 **/
package com.baeldung.connectiondetails;

import com.baeldung.connectiondetails.configuration.CustomCouchBaseConnectionDetailsConfiguration;
import com.couchbase.client.java.Cluster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConnectionDetailsApplication.class)
@Import(CustomCouchBaseConnectionDetailsConfiguration.class)
@ComponentScan(basePackages = "com.baeldung.connectiondetails")
@TestPropertySource(locations = {"classpath:connectiondetails/application-couch.properties"})
@ActiveProfiles("couch")
public class CouchbaseConnectionDetailsLiveTest {
    @Autowired
    private Cluster cluster;
    @Test
    public void givenSecretVault_whenConnectWithCouch_thenSuccess() {
        assertDoesNotThrow(cluster.ping()::version);
    }
}
