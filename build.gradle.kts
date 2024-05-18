import org.docstr.gradle.plugins.gwt.GwtDev

plugins {
    id("java")
    id("org.docstr.gwt") version "1.1.30"
    id("maven-publish")
}
repositories {
    mavenCentral()
    mavenLocal()
}
group = "net.sayaya"
version = "material3-1.4.1"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

dependencies {
    implementation("org.jboss.elemento:elemento-core:1.4.12")
    implementation("org.gwtproject:gwt-user:2.11.0")
    compileOnly("org.gwtproject:gwt-dev:2.11.0")
    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

gwt {
    minHeapSize = "1024M"
    maxHeapSize = "2048M"
    sourceLevel = "auto"
    val lombok: File = project.configurations.annotationProcessor.get().filter { it.name.startsWith("lombok") }.single()
    extraJvmArgs = listOf("-XX:ReservedCodeCacheSize=512M", "-javaagent:${lombok}=ECJ")
}

tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}
if(project.gradle.startParameter.taskNames.contains("gwtDev")) {
    apply(plugin="gwt")
    apply(plugin="war")
    gwt {
        gwt.modules = listOf("net.sayaya.Test")
    }
    tasks {
        named<GwtDev>("gwtDev") {
            minHeapSize = "4096M"
            maxHeapSize = "4096M"
            codeServerPort = 19204
            port = 8888
            war = File("src/test/webapp")
        }
        java.sourceSets["main"].java {
            srcDir("src/test/java")
        }
        withType<War> {
            duplicatesStrategy = DuplicatesStrategy.WARN
        }
    }
} else {
    apply(plugin="gwt-base")
    tasks {
        jar {
            from(sourceSets.main.get().allSource)
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
                    groupId = "net.sayaya"
                    artifactId = "ui"
                    version = "material3-1.4.1"
                    from(project.components["java"])
                }
            }
        }
    }
}