import org.jetbrains.compose.ComposeExtension

plugins {
    kotlin("multiplatform") apply false
    id("org.jetbrains.compose") apply false
}

allprojects {
    repositories {
        //google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }

    afterEvaluate {
        extensions.findByType(ComposeExtension::class.java)?.apply {
            val kotlinGeneration = project.property("kotlin.generation")
            val composeCompilerVersion = project.property("compose.compiler.version.$kotlinGeneration") as String
            kotlinCompilerPlugin.set(composeCompilerVersion)
            val kotlinVersion = project.property("kotlin.version.$kotlinGeneration") as String
            kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
        }
    }
}
