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
10. exchange generic을 사용한 RestTemplete 실습
   - generic을 사용하여 RestTemplete을 작성하는 것이 재사용성이 더 높다.
