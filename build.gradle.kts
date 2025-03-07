plugins {
	id("java")
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.ryandens.javaagent-test") version "0.5.1"
}

group = "org.andrey"
version = "0.0.1-SNAPSHOT"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.springframework.data/spring-data-jdbc
	implementation("org.springframework.data:spring-data-jdbc:3.4.3")
	// https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.7.5")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc
	implementation("org.springframework.boot:spring-boot-starter-jdbc:3.4.3")
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	testJavaagent("net.bytebuddy:byte-buddy-agent:1.14.15")
	// https://mvnrepository.com/artifact/org.mockito/mockito-core
	testImplementation("org.mockito:mockito-core:5.14.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
