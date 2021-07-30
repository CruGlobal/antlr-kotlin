buildscript {
    val kotlinVersion = "1.7.21"

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven("https://oss.jfrog.org/oss-snapshot-local/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

plugins {
    `maven-publish`
    alias(libs.plugins.grgit)
}

allprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.compilerArgs.add("-Xlint:all")
        options.isDeprecation = true
    }

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven("https://oss.jfrog.org/oss-snapshot-local/")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "7.5.1"
    distributionType = Wrapper.DistributionType.ALL
}

version = "${version}_${grgit.log { includes = listOf("HEAD") }.size}"
allprojects {
    group = "org.cru.mobile.fork.antlr-kotlin"
    version = rootProject.version
}

subprojects {
    apply(plugin = "maven-publish")

    publishing {
        repositories {
            maven {
                name = "cruGlobalMavenRepository"
                setUrl("https://cruglobal.jfrog.io/artifactory/maven-cru-mobile-forks-local/")
                credentials(PasswordCredentials::class)
            }
        }
    }
}
