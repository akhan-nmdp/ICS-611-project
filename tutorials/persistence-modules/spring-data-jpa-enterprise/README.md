## Spring Data JPA - Enterprise

This module contains articles about Spring Data JPA used in enterprise applications such as transactions, sessions, naming conventions and more 

### Relevant Articles: 

- [DB Integration Tests with Spring Boot and Testcontainers](https://www.baeldung.com/spring-boot-testcontainers-integration-test)
- [A Guide to Spring’s Open Session in View](https://www.baeldung.com/spring-open-session-in-view)
- [Partial Data Update With Spring Data](https://www.baeldung.com/spring-data-partial-update)
- [Spring JPA – Multiple Databases](https://www.baeldung.com/spring-data-jpa-multiple-databases)

### Eclipse Config 
After importing the project into Eclipse, you may see the following error:  
"No persistence xml file found in project"

This can be ignored: 
- Project -> Properties -> Java Persistance -> JPA -> Error/Warnings -> Select Ignore on "No persistence xml file found in project"
Or: 
- Eclipse -> Preferences - Validation - disable the "Build" execution of the JPA Validator 

