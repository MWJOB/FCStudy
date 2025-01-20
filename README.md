## Spring Jpa

1. aop 실습
   - aop는 비즈니스 로직에 적용되는 반복적이고 횡단 형태로 들어가 있는 것들을 다 꺼내서 AOP에 작성하면 좋다.
  
2. 어노테이션을 통한 aop 실습
   - AOP 같은 구간에서 필터와 인터셉터를 지나서 값 자체가 객체화 되어서 그 값을 변환하거나 AOP에서 특정한 객체를 넣어 줄 수 있다. 외부에서 특정 암호화된 파일이 들어오면 코드에서가 아닌 AOP단에서 복호화가 완료되어서 받을 수 있다. AOP 단계에서 밖으로 내보낼 때 변경도 가능하다.
   
3. Object Mapper 실습
   - Jaskson.core 를 사용하여 JSON node 고유의 값을 읽거나 수정하는 법을 실습.
  
4. Validation 실습
   - 어노테이션을 사용하여 기본적인 validation 기능 실습
5. exception 실습
   - @RestControllerAdvice 또는 ControllerAdvice 를 통한 예외 처리 실습
6. @RestControllerAdvice 실습
   - advice를 통한 일괄적인 exception, validation 처리 실습
7. Filter 처리 실습
8. interceptor 실습
9. interceptor 활용
   - 권한 등을 인터셉트가 확인하고 어노테이션과 핸들러를 통해 처리, 필터는 웹 애플리케이션에서 관리되기 때문에 핸들러와 같은 오브젝트가 없다.
   - intercetor는 spring context에서 관리된다.
10. exchange generic<T>을 사용한 RestTemplete 실습
   - 공통 Response객체에 "data"필드의 객체를 원하는 객체로 설정을 하는 경우 generic을 사용한다.
11. JUnit 실습
12. Swagger 실습
   - 스프링부트 3.0 부터는 Springfox를 지원하지 않고 springdoc을 사용하면 좋다.
13. 네이버 api를 이용한 controller service dto 구현 및 테스트 실습
14. Spring Data Jpa 기능 실습
   - Repository interface 계층, 메소드
   - QueryMethod
   - Entity 기본 속성 (annotation)
   - Listener
15. @Query- 커스텀 쿼리 실습
15. 영속성 컨텍스트 실습





## Spring Security

1. 멀티 모듈 프로젝트 구성
