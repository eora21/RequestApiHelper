# 설명
`RestTemplate`를 좀 더 손쉽게 사용할 수 없을까 하는 마음에 작성해 보았습니다.

`Spring Security`가 적용되어 있습니다.

`Github private Email`을 받아오는 로직이 포함되어 있습니다.

# 사용법
## RequestApiHelper
`RequestApiHelper`를 통해 다른 서버로 api를 요청할 수 있습니다(`OAuth2GithubUserService`의 예시와 `RequestApiHelper`의 메서드들을 참조해주세요).

## Github private Email
Github OAuth 발급 후 id와 secret을 `application.properties`에 적어주세요.

Github에서 사용자의 email을 받아오는 토큰을 생성한 후 `application.properties`에 적어주세요.

> 버그 제보는 언제나 환영입니다!
> 
> 도움을 준 현준님 감사합니다!