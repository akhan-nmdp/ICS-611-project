## Spring Data JPA - Enterprise

This module contains articles about Spring Data JPA used in enterprise applications such as transactions, sessions, naming conventions and more 

### Relevant Articles: 
- [Custom Naming Convention with Spring Data JPA](https://www.baeldung.com/spring-data-jpa-custom-naming)
- [Working with Lazy Element Collections in JPA](https://www.baeldung.com/java-jpa-lazy-collections)
- [Spring Data Support for Java Optional, Streams, Future](https://www.baeldung.com/spring-data-java-8)

### Eclipse Config 
After importing the project into Eclipse, you may see the following error:  
"No persistence xml file found in project"

This can be ignored: 
- Project -> Properties -> Java Persistance -> JPA -> Error/Warnings -> Select Ignore on "No persistence xml file found in project"
Or: 
- Eclipse -> Preferences - Validation - disable the "Build" execution of the JPA Validator 

