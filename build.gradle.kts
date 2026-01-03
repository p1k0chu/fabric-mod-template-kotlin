plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    kotlin("jvm") version "2.3.0"
}

val minecraftVersion = project.property("minecraft_version") as String
val loaderVersion = project.property("loader_version") as String
val fabricVersion = project.property("fabric_version") as String
val fabricLanguageKotlinVersion = project.property("fabric_language_kotlin_version") as String
val modId = project.property("mod_id") as String

repositories {
    mavenCentral()
}

base {
    archivesName = modId
}

dependencies {
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricLanguageKotlinVersion")

    // optional
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            "version" to version,
            "loader_version" to loaderVersion,
            "minecraft_version" to minecraftVersion,
            "fabric_api_version" to fabricVersion,
            "fabric_kotlin_version" to fabricLanguageKotlinVersion
        )
    }
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create(modId) {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }

}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_$modId" }
    }
}

tasks.test {
    useJUnitPlatform()
}
