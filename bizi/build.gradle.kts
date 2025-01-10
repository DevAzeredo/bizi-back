import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "bizi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
   // JWT
   implementation("io.jsonwebtoken:jjwt-api:0.11.5")
   implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
   implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
   
   // Spring Boot Starter (Web, Security, WebSocket, etc.)
   implementation("org.springframework.boot:spring-boot-starter-web")
   implementation("org.springframework.boot:spring-boot-starter-security")
   implementation("org.springframework.boot:spring-boot-starter-data-jpa")  
   implementation("org.springframework.boot:spring-boot-starter-websocket")
   
   // Jackson para Kotlin
   implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
   
   // Kotlin Reflection
   implementation("org.jetbrains.kotlin:kotlin-reflect")
   
   // Lombok
   compileOnly("org.projectlombok:lombok")
   annotationProcessor("org.projectlombok:lombok")
   
   // Banco de dados (PostgreSQL)
   runtimeOnly("org.postgresql:postgresql")
   
   // Testes
   testImplementation("org.springframework.security:spring-security-test")
   testImplementation("org.springframework.boot:spring-boot-starter-test")
   testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
   testRuntimeOnly("org.junit.platform:junit-platform-launcher")
   
   // Adicionando Hibernate (caso não tenha)
   //implementation("org.hibernate:hibernate-core:5.6.9.Final")
   
   // Adicionando Jakarta Persistence (caso necessário)
   //implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
}

kotlin {
   jvmToolchain(21) 
   compilerOptions {
   
       freeCompilerArgs.addAll("-Xjsr305=strict")
   }
}

tasks.withType<Test> {
	useJUnitPlatform()
}
