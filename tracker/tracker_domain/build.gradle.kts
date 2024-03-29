apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(Coroutines.coroutines)
    "implementation"(project(":onboarding:onboarding_presentation"))
}