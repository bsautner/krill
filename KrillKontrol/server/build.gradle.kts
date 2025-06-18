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
    implementation(libs.bundles.pi4j)
    implementation(libs.bundles.koin)
    implementation("io.insert-koin:koin-ktor:3.5.6")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.6")

    testImplementation(libs.kotlin.test.junit)
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}