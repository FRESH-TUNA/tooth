rootProject.name = "openshop"

include("domain")
include("service")
include("api:auth-local-jwt-api")
include("common")

include("infrastructure:openauth-mariadb")
