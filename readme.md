# project Tooth

## 프로젝트 개요
사용자 인증, 관리에 관한 기능을 구축하기 위해 적지 않는 시간이 요구됩니다. 
그래서 사용자 인증, 관리 기능들을 가진 앱을 미리 구축해두고 향후 신규프로젝트에 활용할수 있게 도와주는 프로젝트인 tooth를 시작하게 되었습니다.

## 프로젝트 구성
### [api](./api)
웹 요청을 처리하기 위한 엔드포인트(어댑터)를 관리합니다. 현재 다음의 1개의 모듈로 구성되어있습니다.
- [auth-local-jwt-api](./api/auth-local-jwt-api) (jwt 기반의 로컬 인증을 처리하는 엔드포인트)

### [common](./common)
유틸, 공통으로 사용되는 클래스를 관리합니다.

### [domain](./domain)
육각형 아키텍처의 핵심인 도메인과, 도메인과의 요청을 위한 인터페이스(유스케이스, 포트)를 관리합니다.

### [infrastructure](./infrastructure)
도메인에서 어댑터에 요청하기 위한 포트의 구현체들을 관리합니다.
- [tooth-mariadb](./infrastructure/tooth-mariadb) (mariadb 데이터베이스 접근을 위한 어댑터)

### [service](./service)
어댑터에서 도메인에 요청하기 위한 유스케이스의 구현체들을 관리합니다.

## 프로젝트 배경지식
### JWT 기반의 로컬 인증 플로우
![jwt 기반 인증](./docs/jwt-workflow1.png)
JWT(JSON WEB TOKEN)는 정보를 JSON을 사용하여 안전하게(손상, 위조여부 확인) 통신하기 위한 개방된 표준입니다. JWT는 웹 애플리케이션에서 인증 및 권한 부여 목적으로 자주 사용되고 있습니다.

JWT는 헤더, 페이로드, 서명의 세 부분으로 구성됩니다. 헤더는 토큰의 유형과 서명에 사용되는 알고리즘을 정의합니다. 페이로드에는 사용자와 관련된 정보와 추가로 필요로 하는 데이터들이 포함됩니다. JWT의 서명은 헤더와 페이로드정보를 기반으로 비밀키를 사용하여 생성됩니다. 이후 수신자가 헤더와 페이로드정보를 기반으로 비밀키를 사용하여 서명을 만든후 jwt토큰의 서명과 비교해보면 위조여부를 확인할수 있습니다.

JWT 사용은 다음과 같은 장점이 있습니다.
- 범용성: JSON 양식은 대부분의 프로그래밍 언어에서 지원합니다. 즉 JWT 는 웹브라우저에 국한되지 않고 네이티브 어플리케이션, 모바일 앱등에서도 활용할수 있습니다.
- 토큰의 용량제한 없음: 토큰에 데이터를 담고 싶으면 얼마든지 상관없습니다. 다만 너무 무거워진 토큰은 네트워크 통신시 지연을 발생시킬수 있습니다.

JWT는 유효기간을 가지고 있기 때문에 갱신이 필요하고 보안관련 이슈가 발생합니다. 토큰을 발행하고 세션으로 관리하지 않는다면, 이 토큰이 부정사용되었을때 정지시킬 방법이 없습니다. 여러 대응전략이 있는데, 갱신전용 토큰과 엑세스전용 토큰을 분리한후, 갱신토큰은 세션으로 관리 & 유효기간을 길게 가져가고, 엑세스 토큰은 유효기간을 짧게 가져가는 방법이 있습니다. 그리고 리프레시 토큰으로 재발행할수 있는 횟수을 제한하거나, 아예 jwt 자체를 세션으로 관리하는방법이 있습니다.

JWT는 base64 인코딩만 거친것으로 누구나 토큰의 내용물을 확인할수 있습니다. 보안성을 높이기 위해 jwt 규격의 jwe 토큰을 사용하거나, 토큰에 공개키를 담은후 서버단에서 DB에 저장된 실제데이터로 교환하는 방법이 있습니다.

### base64 인코딩
이진데이터를 텍스트 형식으로 변환하는 인코딩 방식중에 하나입니다.

base64는 텍스트로 변환시 CR, LF 같은 개행문자로의 변환을 사용하지 않기 때문에 운영체제에 종속되지 않은 인코딩이 가능합니다.
다만 6비트씩 묶어 용량이 4/3 배가 되는것은 단점입니다.

### CORS 처리
서버와 다른 오리진(받은 응답의 출처)에서 받은 응답에서 서버로 요청하는 상황을 의미합니다. 
(응답을 보낸 서버의 scheme, host, port 가 동일하면 같은 오리진으로 간주합니다.)

CORS 상황에서의 처리 과정은 서버의 응답에 포함된 헤더들을 바탕으로 웹브라우저(클라이언트)가 결정합니다. 그래서 주로 클라이언트에서 요청을 바로 하는것이 아니라 서버에 preflight를 보내서(HEAD 요청) 서버로부터 받아온 Access-Control-Allow-Origin 헤더정보를 바탕으로 허용된 출처 인지를 검사하게 됩니다.

```java
@Configuration
class CORSConfig(private val corsProperties: CORSProperties) {

    /** CORS 상황에서 허용하는 동작을 서버에서 지정한다 **/
    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val corsConfigSource = UrlBasedCorsConfigurationSource()
        val corsConfig = CorsConfiguration()

        // CORS 상황에서 어떤 header를 뜯어볼수 있는지
        corsConfig.allowedHeaders = corsProperties.allowedHeaders.split(",")

        // CORS 상황에서 어떤 method를 사용할수 있는지
        corsConfig.allowedMethods = corsProperties.allowedMethods.split(",")

        // CORS 상황에서 허용되는 origin
        corsConfig.allowedOrigins = corsProperties.allowedOrigins.split(",")

        // CORS 상황에서 노출되는 header
        corsProperties.exposedHeaders.split(",")

        corsConfig.maxAge = corsProperties.maxAge
        corsConfig.allowCredentials = true
        corsConfigSource.registerCorsConfiguration("/**", corsConfig)

        return corsConfigSource
    }
}
```

### OAuth?
OAuth는 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 개방형 표준 입니다.
많은 어플리케이션이 OAuth를 통해 3자 업체(google, facebook)에 접근하여 사용자 정보를 가져와 소셜로그인 기능에 활용하고 있습니다. OAuth를 통한 인증플로우는 다음과 같습니다.

- 사용자가 소셜로그인 기능을 호출합니다.
- 소셜로그인 요청을 받은 서버는 사전에 OAuth기관에서 발급받은 ID와 성공후 redirect 시킬 path와 함께 OAuth 업체의 로그인화면으로 redirect 합니다.
- OAuth는 id와 redirect path를 확인후 사용자에게 소셜로그인 화면을 응답으로 보내줍니다.
- 사용자가 소셜로그인을 진행합니다.
- 로그인 성공시 OAuth는 grant code와 함께 사전에 등록된 redirect 경로(서버)로 redirect 합니다.
- 서버는 grant code와 OAuth기관에서 발급받은 id, secret code와 함께 oauth 업체에 token을 요청합니다.
- oauth 업체에서 token이 발급되면, 이제 토큰을 이용하여 OAuth업체로부터 사용자관련 정보(닉네임, 이메일, 프로필사진등)를 조회할수 있습니다.
- 사용자관련 정보를 바탕으로 소셜로그인을 진행합니다.

### openid connect
OpenID Connect는 OAuth 2.0을 기반으로 상위계층에서 좀 더 간편하게 인증을 처리할 수 있도록 고안되었습니다.

OpenID Connect는 Access 토큰과 함께 ID 토큰을 전달합니다. 이 JWT(JSON Web Tokens)를 통해 암호화 된 토큰 안에 사용자 정보를 비롯한 다양한 정보를 HTTP 헤더의 최대 사이즈인 4KB 이내로 저장할 수 있습니다. 이로 인해 eBay는 Access 토큰을 사용하여 한 번 더 OAuth 2.0 API를 호출할 필요 없이 사용자 정보가 담긴 ID 토큰을 복호화 하여 바로 사용할 수 있게 됩니다.

예를 들어 5천만 명의 사용자들이 eBay를 이용할 경우, 사용자 정보를 가져오기 위해서는 최소 1억 번의 API 호출이 필요했었지만 ID 토큰을 이용하면 그 절반인 5천만 번 정도만 호출하면 됩니다.

### SSL 핸드쉐이크
- client hello: 클라이언트가 랜덤데이터를 생성후, 지원하는 암호화방식 후보들과 함께 server에 hello 요청합니다.
- server hello: 서버도 랜덤데이터를 생성한후, 결정된 암호화방식과 CA의 서명이 있는 인증서와 함께 hello를 보냅니다.
- 클라이언트에서 인증서를 CA의 공개키로 검증한다. 그후 클라이언트, 서버 랜덤데이터를 사용해 pre-master-secret를 생성합니다.
- 클라이언트에서 인증서에 있는 서버의 공개키로 pre-master-secret를 암호화하여 서버로 보냅니다.
- 서버는 개인키로 pre-master-secret를 얻어냅니다.
- 이로써 서버와 클라이언트는 같은 pre-master-secret을 보유하게 되었습니다. 동일한 pre-master-secret을 통해 동일한 master secret과 세션키를 만들고 같은 세션키를 사용해 서로 finished 패킷을 보내면 ssl handshake가 종료됩니다.
