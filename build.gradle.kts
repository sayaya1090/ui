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
version = "4.0"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.jboss.elemento:elemento-core:1.0.11")
    implementation("com.google.elemental2:elemental2-svg:1.1.0")
    implementation("org.gwtproject:gwt-user:2.10.0")
    compileOnly("org.gwtproject:gwt-dev:2.10.0")
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
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
                version = "4.0"
                from(project.components["java"])
            }
        }
    }
}
