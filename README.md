# liveshop 서버
실시간 라이브 스트리밍 쇼핑몰

# 서버 구조도


## 프로젝트 목표
- 네이버 쇼핑 라이브와 같은 서비스를 구현해 내는 것이 목표입니다.
- 단순 기능 구현 뿐만 아니라 대용량 트래픽 처리까지 고려한 기능을 구현하는 것이 목표입니다.
- 객체지향 원리와 여러 이론적 토대위에서 올바른 코드를 작성하는 것이 목표입니다.
- 문서화, 단위테스트는 높은 우선순위를 두어 작성하고 있고 CI/CD 통한 자동화 또한 구현하여 쉽게 협업이 가능한 프로젝트로 만들어 보려고합니다.



## 기술적 Issue 해결 과정
<a href="https://codingmylife.tistory.com/41">[#1] 서버가 여러 대 일때 로그인 세션을 관리 하기 위한 방법들 </a>




## 프로젝트 중점 사항
- 버전관리
- 문서화
- Redis를 활용한 메시지 브로커
- MySQL을 활용한 성능 튜닝
- 상품 주문 시 Block 문제 해결
- 도메인 기반으로 작성된 기능
- Service Layer를 고립시켜 의존적이지 않은 단위테스트 작성
- 서버의 scale up, scale out 용이성
- JMeter를 사용한 성능 테스트
- VisualVM을 이용한 CPU 사용량과 스레드, 힙 모니터링


## 라이브 방송 동작 시퀀스



## DB ERD



## Use Case
<a href="https://github.com/khj745700/liveshop/wiki/Use-Case">Use Case</a>

## Front
