plugins {
	java
	id("org.springframework.boot") version "3.5.8"
	id("io.spring.dependency-management") version "1.1.7"
	// Spring REST Docs의 결과물을 OpenAPI 3 스펙으로 변환
	id("com.epages.restdocs-api-spec") version "0.17.1"
	// OpenAPI 3 Spec을 기반으로 Swagger UI 생성
	id("org.hidetake.swagger.generator") version "2.18.2"
	// AVRO
	id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

group = "net.happykoo"
version = "0.0.1-SNAPSHOT"
description = "Payment Service"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//MySql
	runtimeOnly("com.mysql:mysql-connector-j")

	//Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.10.0")
	implementation("com.squareup.retrofit2:converter-jackson:2.10.0")
	implementation("com.squareup.retrofit2:converter-gson:2.10.0")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0")
	implementation("com.google.code.gson:gson")

	// Kafka Client
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.apache.avro:avro:1.11.3")
	implementation("io.confluent:kafka-avro-serializer:7.0.1")

	//H2
	testRuntimeOnly("com.h2database:h2")

	//Rest Docs
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
	testImplementation("com.epages:restdocs-api-spec-mockmvc:0.17.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask> {
	fieldVisibility = "PRIVATE"
	setCreateSetters("false")
	setSource("src/main/avro")
	setOutputDir(file("build/generated-sources"))
}

openapi3 {
	this.setServer("https://localhost:8080")
	title = "Payment Service"
	description = "Order/Payment API"
	version = "0.1.0"
	format = "yaml"
}

tasks.register<Copy>("copyOasToSwagger") {
	delete("src/main/resources/static/swagger-ui/openapi3.yaml") //기존 yaml 삭제
	from("$buildDir/api-spec/openapi3.yaml") //복제할 yaml
	into("src/main/resources/static/swagger-ui/.") //타겟 디렉토리로 복제
	dependsOn("openapi3") //openapi3 task 먼저 실행
}