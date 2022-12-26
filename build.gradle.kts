subprojects {
    group = "com.github.ngyewch.capsule"
    version = "1.2.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Javadoc> {
        configure(options, closureOf<StandardJavadocDocletOptions> {
            addStringOption("Xdoclint:none", "-quiet")
            //.addStringOption("-release", "8")
            links = mutableListOf(
                "https://docs.oracle.com/javase/8/docs/api/",
            )
        })
    }
}
