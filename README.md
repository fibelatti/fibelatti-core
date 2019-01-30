fibelatti-core
=====

[![Language](https://img.shields.io/badge/language-kotlin-brightgreen.svg)](https://www.github.com/fibelatti/fibelatti-core)
[![CircleCI](https://circleci.com/gh/fibelatti/fibelatti-core/tree/master.svg?style=svg)](https://circleci.com/gh/fibelatti/fibelatti-core/tree/master)

A collection of base classes and extension functions used to build a modern Android project in Kotlin.

- MVVM (using Architecture components) or MVP
- Coroutines or RxJava + RxAndroid

Built on top of
--------

```groovy
"org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.20"

"androidx.appcompat:appcompat:1.1.0-alpha01"
"com.google.android.material:material:1.1.1-alpha02"

"androidx.lifecycle:lifecycle-extensions:2.0.0"
"androidx.lifecycle:lifecycle-compiler:2.0.0"

"org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"
"org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1"

"io.reactivex.rxjava2:rxjava:2.2.4"
"io.reactivex.rxjava2:rxandroid:2.1.0"

"javax.inject:javax.inject:1" // to support Dagger 2

"com.squareup.moshi:moshi-kotlin:1.8.0"
```

Download
--------

```groovy
dependencies {
    implementation 'com.fibelatti.core:core:1.0.0'
    implementation 'com.fibelatti.core:archcomponents:1.0.0'

    testImplementation 'com.fibelatti.core:core-test:1.0.0'
    testImplementation 'com.fibelatti.core:archcomponents-test:1.0.0'
}
```

License
--------

    Copyright 2018 Filipe Belatti

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
