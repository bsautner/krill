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
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    jvmToolchain(21)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.bundles.ktorServer)

    implementation("com.pi4j:pi4j-core:3.0.1")
    implementation("com.pi4j:pi4j-plugin-raspberrypi:3.0.1")
    implementation("com.pi4j:pi4j-plugin-gpiod:3.0.1")
    implementation("io.ktor:ktor-server-default-headers:3.1.3")
    testImplementation(libs.kotlin.test.junit)
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}