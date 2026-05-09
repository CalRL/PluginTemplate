# PluginTemplate

[![Java](https://img.shields.io/badge/Java-25-red)](https://openjdk.org/projects/jdk/25/)
[![Minecraft](https://img.shields.io/badge/Minecraft-26.1.2-blue)](https://papermc.io)
[![Build](https://github.com/AegirMC/PluginTemplate/actions/workflows/java.yml/badge.svg?branch=add-checkstyle)](https://github.com/AegirMC/PluginTemplate/actions)

A PaperMC plugin template with Checkstyle, GitHub Actions CI, and sensible defaults for getting started quickly.

## Features

- Gradle Kotlin DSL with `run-paper` for local testing
- Checkstyle with zero-warning enforcement
- GitHub Actions CI — builds + publishes Checkstyle reports + uploads JAR artifacts

## Getting Started

```bash
./gradlew runServer      # Start a test Paper server
./gradlew build          # Build the plugin JAR
./gradlew checkstyleMain # Run Checkstyle
```
