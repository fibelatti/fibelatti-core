fibelatti-core
=====

[![Language](https://img.shields.io/badge/language-kotlin-brightgreen.svg)](https://www.github.com/fibelatti/fibelatti-core)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![CircleCI](https://circleci.com/gh/fibelatti/fibelatti-core/tree/master.svg?style=svg)](https://circleci.com/gh/fibelatti/fibelatti-core/tree/master)

A collection of base classes and extension functions used to build a modern Android project in Kotlin.

Built on top of
--------

```groovy
"org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71"

"androidx.appcompat:appcompat:1.1.0"
"androidx.fragment:fragment:1.2.0"
"com.google.android.material:material:1.1.0"

"androidx.lifecycle:lifecycle-extensions:2.2.0"
"androidx.lifecycle:lifecycle-compiler:2.2.0"

"org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
"org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"

"io.reactivex.rxjava2:rxjava:2.2.4"
"io.reactivex.rxjava2:rxandroid:2.1.0"

"javax.inject:javax.inject:1" // to support Dagger 2
```

Download
--------

```groovy
dependencies {
    implementation 'com.fibelatti.core:core:2.0.0-alpha3'
    implementation 'com.fibelatti.core:archcomponents:2.0.0-alpha3'

    testImplementation 'com.fibelatti.core:core-test:2.0.0-alpha3'
    testImplementation 'com.fibelatti.core:archcomponents-test:2.0.0-alpha3'
}
```

Example usage
--------

For an example usage of these modules please see [Pinkt for Pinboard](https://github.com/fibelatti/pinboard-kotlin).

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
