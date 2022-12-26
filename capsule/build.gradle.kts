plugins {
    `java-library`
    `maven-publish`
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withJavadocJar()
    withSourcesJar()
}

dependencies {
    testImplementation(project(":capsule-util"))

    testImplementation("com.google.jimfs:jimfs:1.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jooq:joor:0.9.14")
    testImplementation("org.truth0:truth:0.23")
}

tasks.withType<Javadoc> {
    configure(options, closureOf<StandardJavadocDocletOptions> {
        noDeprecated(true)
    })
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "Capsule"
        attributes["Premain-Class"] = "Capsule"
    }
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String

            from(components["java"])

            pom {
                name.set("capsule")
                description.set("Dead-Simple Packaging and Deployment for JVM Apps.")
                url.set("https://github.com/ngyewch/capsule")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/ngyewch/capsule/blob/main/LICENSE")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:ngyewch/capsule.git")
                    developerConnection.set("scm:git:git@github.com:ngyewch/capsule.git")
                    url.set("https://github.com/ngyewch/capsule")
                }
                developers {
                    developer {
                        id.set("ngyewch")
                        name.set("Nick Ng")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                val ossrhUsername: String? by project
                val ossrhPassword: String? by project
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}
