## application.yml 설정 가이드
### 관계형 데이터베이스 구축하기
- infrastructre/tooth-mariadb 의 리소스 폴더에 flyway 마이그레이션을 위한 application.yml 작성
```
spring:
  datasource:
    url: <STRING>
    username: <STRING>
    password: <STRING>
    driver-class-name: org.mariadb.jdbc.Driver
  flyway:
    url: <STRING>
    user: <STRING>
    password: <STRING>
```
- infrastructre/tooth-mariadb 의 DBMigrationRunner.kt 실행


### 배포할 api의 리소스 폴더에 application.yml 작성
```
spring:
  datasource:
    url: <STRING>
    username: <STRING>
    password: <STRING>
    driver-class-name: org.mariadb.jdbc.Driver

tooth:
  jwt:
    secret: <STRING>
    refresh-token-secret: <STRING>
    access-token-expired-mile-seconds: <NUMBER>
    refresh-token-expired-mile-seconds: <NUMBER>

    role-key: 'ROLES'
    prefix: 'Bearer '

    local-member-access-token-for-test: jwt.io 등의 사이트를 통해 설정
    local-member-refresh-token-for-test: <STRING, 설정안해도됨>

  cors:
    allowed-origins: 'http://localhost:8080'
    allowed-methods: '*'
    allowed-headers: '*'
    exposed-headers: 'Authorization'
    max-age: 3600

  local:
    id-policy: 'EMAIL'

```
