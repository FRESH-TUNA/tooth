rootProject.name = "tooth"

include("domain")
include("service")
include("common")

include("infrastructure:tooth-mariadb")

include("api:auth-local-jwt-api")
include("api:member-api")
