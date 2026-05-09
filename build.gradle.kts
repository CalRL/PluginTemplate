plugins {
    id("checkstyle")
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

checkstyle {
    toolVersion = "10.21.0"
}

tasks.withType<Checkstyle> {
    maxWarnings = 0
}

tasks.withType(Checkstyle).configureEach {
    reports {
        xml.required = true
        html.required = true
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.1.2")
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}
