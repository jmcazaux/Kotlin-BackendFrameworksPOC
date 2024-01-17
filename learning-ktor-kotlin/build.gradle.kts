val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project
val hikari_version: String by project
val junit_version: String by project
val kotest_version: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    id("io.ktor.plugin") version "2.3.7"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"
    id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "com.ironbird"
version = "0.0.1"

application {
    mainClass.set("com.ironbird.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}


tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.ktor:ktor-server-swagger-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-thymeleaf-jvm:$ktor_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-json:$kotest_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
}


graalvmNative {
    binaries {

        named("main") {
            fallback.set(false)

            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin")
            buildArgs.add("--initialize-at-build-time=org.slf4j.LoggerFactory")
            buildArgs.add("--initialize-at-build-time=kotlinx.serialization.json.JsonImpl")
            buildArgs.add("--initialize-at-build-time=org.h2.fulltext.FullTextLucene")
            buildArgs.add("--initialize-at-build-time=org.apache.lucene.index.IndexFormatTooOldException")


            buildArgs.add("-H:+InstallExitHandlers")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            imageName.set("graalvm-server")
        }

        named("test") {
            fallback.set(false)

            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin")
            buildArgs.add("--initialize-at-build-time=org.slf4j.LoggerFactory")
            buildArgs.add("--initialize-at-build-time=kotlinx.serialization.json.JsonImpl")
            buildArgs.add("--initialize-at-build-time=org.h2.fulltext.FullTextLucene")

            buildArgs.add("-H:+InstallExitHandlers")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            val path = "${projectDir}/src/test/resources/META-INF/native-image/"
            buildArgs.add("-H:ReflectionConfigurationFiles=${path}reflect-config.json")
            buildArgs.add("-H:ResourceConfigurationFiles=${path}resource-config.json")

            imageName.set("graalvm-test-server")
        }
    }
}
