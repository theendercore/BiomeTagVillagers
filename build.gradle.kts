@file:Suppress("PropertyName", "VariableNaming")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.iridium)
    alias(libs.plugins.iridium.publish)
    alias(libs.plugins.iridium.upload)
}

repositories {
    maven("https://teamvoided.org/releases")
    maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
    mavenCentral()
}

modSettings {
    entrypoint("main", "com.theendercore.biome_tag_villagers.BiomeTagVillagers")
    entrypoint("fabric-datagen", "com.theendercore.biome_tag_villagers.data.gen.BiomeTagVillagersData")
    mixinFile("${modId()}.mixins.json")
}

dependencies {
    modImplementation(fileTree("libs"))
    modImplementation(libs.modmenu)
}

loom {
    runs {
        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modSettings.modId()}")
            runDir("build/datagen")
        }

        create("TestWorld") {
            client()
            ideConfigGenerated(true)
            runDir("run")
            programArgs("--quickPlaySingleplayer", "test")
        }
    }
}

sourceSets["main"].resources.srcDir("src/main/generated")

tasks {
    val targetJavaVersion = 21
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        compilerOptions.jvmTarget = JvmTarget.JVM_21
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }

    jar {
        val valTaskNames = gradle.startParameter.taskNames
        if (!valTaskNames.contains("runDataGen")) {
            exclude("com/theendercore/biome_tag_villagers/data/gen/*")
        } else {
            println("Running datagen for task ${valTaskNames.joinToString(" ")}")
        }
    }
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), false)
    publishSources(true)
}

uploadConfig {
//    debugMode = true
    modrinthId = "Ah3WA8lg"
    curseId = "912044"

    changeLog = File("./changelog.md").readText()
    // FabricApi
    modrinthDependency("P7dR8mSH", uploadConfig.REQUIRED)
    curseDependency("fabric-api", uploadConfig.REQUIRED)
    // Fabric Language Kotlin
//    modrinthDependency("Ha28R6CL", uploadConfig.REQUIRED)
//    curseDependency("fabric-language-kotlin", uploadConfig.REQUIRED)
}