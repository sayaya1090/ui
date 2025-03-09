plugins {
    id("java")
    id("war")
    id("dev.sayaya.gwt") version "2.1.6"
    id("maven-publish")
}
repositories {
    mavenCentral()
    mavenLocal()
}
group = "dev.sayaya"
version = "material3-2.2.0"

dependencies {
    implementation("org.jboss.elemento:elemento-core:1.7.0")
    implementation("org.gwtproject:gwt-user:2.12.2")
    compileOnly("org.gwtproject:gwt-dev:2.12.2")
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

gwt {
    minHeapSize = "1024M"
    maxHeapSize = "2048M"
    sourceLevel = "auto"
    modules = listOf("dev.sayaya.Test")
    war = file("src/test/webapp")
}

tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    jar {
        from(sourceSets.main.get().allSource)
        enabled = true
        duplicatesStrategy = DuplicatesStrategy.WARN
    }
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/sayaya1090/maven")
                credentials {
                    username = project.findProperty("github_username") as String
                    password = project.findProperty("github_password") as String
                }
            }
        }
        publications {
            register("maven", MavenPublication::class) {
                groupId = "${project.group}"
                artifactId = "ui"
                version = "${project.version}"
                from(project.components["java"])
            }
        }
    }
}