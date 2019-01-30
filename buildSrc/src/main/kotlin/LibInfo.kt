object LibInfo {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private const val majorMultiplier = 10000
    private const val minorMultiplier = 100

    val versionCode: Int = versionMajor * majorMultiplier + versionMinor * minorMultiplier + versionPatch
    val versionName: String = "${LibInfo.versionMajor}.${LibInfo.versionMinor}.${LibInfo.versionPatch}"

    const val repo = "fibelatti-core"
    const val repoUrl = "https://github.com/fibelatti/fibelatti-core"
    const val gitUrl = "https://github.com/fibelatti/fibelatti-core.git"

    const val licenseName = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    val licenses = listOf("Apache-2.0")

    const val libGroupId = "com.fibelatti.core"

    const val libDescription = "Commonly used classes and extensions functions. Used to build Android projects in Kotlin."

    const val devId = "fibelatti"
    const val devName = "Filipe Belatti"
    const val devEmail = "develatti@gmail.com"
}
