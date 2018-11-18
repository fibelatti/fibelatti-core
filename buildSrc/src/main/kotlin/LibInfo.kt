object LibInfo {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private const val majorMultiplier = 10000
    private const val minorMultiplier = 100

    val versionCode: Int = versionMajor * majorMultiplier + versionMinor * minorMultiplier + versionPatch
    val versionName: String = "${LibInfo.versionMajor}.${LibInfo.versionMinor}.${LibInfo.versionPatch}"

    const val repo = "maven"
    const val gitUrl = "https://github.com/fibelatti/fibelatti-core.git"
    val licenses = listOf("Apache-2.0")

    const val libGroupId = "com.fibelatti"

    const val artifactCore = "core"
    const val artifactCoreName = "FibelattiCore"
    const val artifactDescription = "Commonly used classes and extensions functions."
}
