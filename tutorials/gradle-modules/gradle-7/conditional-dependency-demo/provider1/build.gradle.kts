plugins {
    id("java")
}

group = "com.baeldung.gradle"
version = "0.0.1-SNAPSHOT"
sourceCompatibility = "1.8"
targetCompatibility = "1.8"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
