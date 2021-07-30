plugins {
    id("org.jetbrains.kotlin.multiplatform")
    `maven-publish`
    id("org.ajoberstar.grgit") version "4.1.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

apply(plugin = "maven-publish")

kotlin {
    jvm()
    js(BOTH) {
        browser {
        }
        nodejs {
        }
    }

    ios("ios") {
        binaries {
            staticLib()
        }
    }
    linuxX64("linux") {
        binaries {
            staticLib()
        }
    }
    macosX64("mac") {
        binaries {
            staticLib()
        }
    }
    mingwX64("windows") {
        binaries {
            staticLib()
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("com.benasher44:uuid:0.2.2")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by creating {
        }
        val iosMain by getting {
            dependsOn(nativeMain)
        }
        val linuxMain by getting {
            dependsOn(nativeMain)
        }
        val macMain by getting {
            dependsOn(nativeMain)
        }
        val windowsMain by getting {
            dependsOn(nativeMain)
        }
    }
}

group = "org.cru.mobile.fork.antlr-kotlin"
version = "${version}_${grgit.log { includes = listOf("HEAD") }.size}"
publishing {
    repositories {
        maven {
            name = "cruGlobalMavenRepository"
            setUrl("https://cruglobal.jfrog.io/artifactory/maven-cru-mobile-forks-local/")
            credentials(PasswordCredentials::class)
        }
    }
}
