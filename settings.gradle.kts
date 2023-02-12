rootProject.name = "openshop"

include("domain")
include("service")
include("api")
include("api:auth-local-jwt-api")
findProject(":api:auth-local-jwt-api")?.name = "auth-local-jwt-api"
include("common")
