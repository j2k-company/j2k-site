allprojects {
    group = "com.j2k"
    version = "0.0.1"
}

//task("classes").enabled = false
//task("testClasses").enabled = false

task("build") {
    copy {
        from("frontend/src")
        into("backend/src/main/resources/files")
    }
}
