plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    testRuntimeOnly(project(":capsule"))

    testImplementation("com.google.jimfs:jimfs:1.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.truth0:truth:0.23")
}

tasks.test {
    systemProperties["java.protocol.handler.pkgs"] = "co.paralleluniverse.filesystem"
    useJUnit()
    testLogging {
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
        )
    }
}
