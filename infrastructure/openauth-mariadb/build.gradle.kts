import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * lazy loading, noargs 관련 설명자료
 * https://techblog.woowahan.com/2675/
 */

plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"

    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.3.72"

    kotlin("jvm") version "1.7.22"

    kotlin("kapt") version "1.6.0"
}

group = "com.freshtuna.openshop"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
extra["springCloudVersion"] = "2022.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-vault-config")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.13.4")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
