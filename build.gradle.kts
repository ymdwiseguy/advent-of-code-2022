plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.6.0")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }
        test {
            java.srcDirs("src/test/kotlin")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
