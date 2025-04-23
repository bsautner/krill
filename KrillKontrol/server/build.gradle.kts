plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.example.project"
version = "1.0.0"
application {
    mainClass.set("org.example.project.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    jvmToolchain(17)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
//    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}