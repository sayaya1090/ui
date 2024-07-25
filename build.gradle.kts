plugins {
    id("java")
    id("org.wisepersist.gwt") version "1.1.19"
    id("maven-publish")
}
apply(plugin="gwt-base")
repositories {
    mavenCentral()
    mavenLocal()
}
group = "net.sayaya"
version = "4.1"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.jboss.elemento:elemento-core:1.4.2")
    implementation("com.google.elemental2:elemental2-svg:1.2.1")
    implementation("org.gwtproject:gwt-user:2.11.0")
    compileOnly("org.gwtproject:gwt-dev:2.11.0")
    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    gwt {
        minHeapSize = "1024M"
        maxHeapSize = "2048M"
        sourceLevel = "auto"
    }
    compileGwt {
        val lombok: File = project.configurations.annotationProcessor.get().filter { it.name.startsWith("lombok") }.single()
        extraJvmArgs = listOf("-XX:ReservedCodeCacheSize=512M", "-javaagent:${lombok}=ECJ")
    }
    jar {
        from(sourceSets.main.get().allSource)
    }
    publishing {
        publications {
            register("maven", MavenPublication::class) {
                groupId = "net.sayaya"
                artifactId = "ui"
                version = "4.1"
                from(project.components["java"])
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/GC-Genome/maven")
                credentials {
                    username = project.findProperty("github_username") as String? ?: System.getenv("GITHUB_USERNAME")
                    password = project.findProperty("github_password") as String? ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
