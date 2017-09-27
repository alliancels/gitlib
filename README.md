# gitlib
Library of Git convenience methods. Extends the JGit library.

## Usage

To use this library in an application, add the following to your Gradle build script:

```
repositories {
    maven {
        url 'https://github.com/alliancels/maven-repo/raw/releases'
    }
}
dependencies {
    classpath 'com.alliancels:gitlib:0.0.1'
}
```

To use this library within a Gradle build script, nest the block above within the buildscript configuration block:

```
buildscript {
    ...
}
```

## Build

The project can be rebuilt by invoking the `gradlew build` command.

## Release new versions

Clone [the maven repo](https://github.com/alliancels/maven-repo) to the same root directory as this repo.

    Example:
        \repos\gitlib
        \repos\maven-repo

Push new version to this project's repo.

Publish library by invoking the `gradlew publish` command.  This will install the library, source, and doc jars into the
local maven repo.

Commit and push changes to the maven repo.