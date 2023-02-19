allprojects {
    group = "site.j2k"
    version = "0.0.1"
}

//task("classes").enabled = false
//task("testClasses").enabled = false

task("build") {
    copy {
        from("frontend/src") {
            exclude(".cache", "dist")
        }
        into("backend/src/main/resources/files")
    }
}
