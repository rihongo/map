# map
아래와 같은 기능을 지원합니다.
* 장소 검색
* 장소 상세보기
* 장소 검색 순위 

## Install 및 실행 가이드

1. 해당 프로젝트 Import or 다운로드
2. $./gradlew build
3. $./gradlew bootRun
4. http://localhost:8080 접속

> #### 사용자 데이터
> ##### userId : tester, password : password
> ##### userId : test1234, password : test1234

-------------
## Dependencies & External Libraries
 

 ### Spring 
 
 **WEB**   
 - spring-boot-starter-web   
 - spring-boot-starter-tomcat   
 - spring-boot-starter-validation   
 - spring-boot-starter-aop   
 - spring-boot-starter-security   
 - spring-boot-devtools   
 - spring-boot-starter-data-jpa   
 - spring-boot-starter-data-redis   
 - spring-session-data-redis   
 - spring-boot-starter-thymeleaf   
 - spring-boot-configuration-processor   
 
 **TEST**
 - spring-boot-starter-test      
 - spring-security-test    
    
 ### External Libraries
 
 - retrofit   
    - API 호출을 지원하는 오픈소스   
 - retrofit:converter-jackson   
    - API 호출 후 Object 변환
 - embedded-redis
    - 로그인 테스트를 위한 내장 redis 사용
 - modelmapper
    - DTO <-> Entity 변환을 위해 사용

 **VIEW**
 - jquery
    - Dom event 처리 및 ajax 호출을 위해 사용
 - bootstrap
    - view css 템플릿사용
 - pagenation.js
    - view에서 페이징 처리를 하기 위해서 사용
    
 **ETC**
 - lombok
    - annotation으로 코드 자동생성
 - com.h2database:h2
    - 인메모리 사용
 
