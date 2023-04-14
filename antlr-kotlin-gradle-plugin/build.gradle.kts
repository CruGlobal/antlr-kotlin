plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.grgit)
}

dependencies {
    implementation("org.antlr:antlr4:${Versions.antlr}")
    implementation(gradleApi())
    implementation(project(":antlr-kotlin-target"))
}

group = "org.cru.mobile.fork.antlr-kotlin"
version = "${version}_${grgit.log { includes = listOf("HEAD") }.size}"
publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "cruGlobalMavenRepository"
            setUrl("https://cruglobal.jfrog.io/artifactory/maven-cru-mobile-forks-local/")
            credentials(PasswordCredentials::class)
        }
    }
}
