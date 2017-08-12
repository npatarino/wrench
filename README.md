WRENCH
========
[![](https://jitpack.io/v/npatarino/wrench.svg)](https://jitpack.io/#npatarino/wrench)
[![Build Status](https://travis-ci.org/npatarino/wrench.svg?branch=master)](https://travis-ci.org/npatarino/wrench)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)

![Wrench logo][wrenchlogo]

Wrench is Kotlin framework used at ![Idealista Labs][idealistalabs] to develop applications providing some datatypes and abstractions to work with MVP and Clean Architecture.

# Usage

## Use case

```kotlin
UseCase<SendEmailError, Message>()
        .bg(sendEmail(recipients), delay)
        .then { reverseMessage(it) }
        .then { upperCaseMessage(it) }
        .map { it.toModel() }
        .ui({ handleResult(it) })
        .run(DefaultExecutor())
```

## Validation

## Navigator

# Add it to your project

Use it at your own risk, the actual state is not production ready.

Add it in your root `build.gradle` at the end of repositories.

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency into project `build.gradle`

```groovy
dependencies {
    compile 'com.github.npatarino:wrench:0.15'
}
```

# License

    Copyright (C) 2017 Nibble App

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[wrenchlogo]: ./assets/logo.png
[idealistalabs]: (https://www.idealista.com/labs/blog/)
