rootProject.name = "openshop"
include("domain")
include("domain:application")
findProject(":domain:application")?.name = "application"
include("application")
include("api")
