# bitcamp-project-web2

## 3.4 - jQuery 적용
1) CDN(Content Delivery Network) 방식
  - CDN? 콘텐츠(파일)을 전세계 클라우드 센터에 분산 배치시키는 방법
    -각 사용자 별로 가장 가까운 서버로부터 콘텐츠를 다운받는다.
  - 신뢰할 만한 서버에 올려져 있는 라이브러리 파일의 링크를 건다.
    - 즉 직접 자신들의 웹 서버에 해당 파일을 놓지 않는다.
    - 최종 사용자는 같은 URL로부터 다운로드 받은 파일을 재사용할 수 있다.
    - 사이트마다 같은 URL의 파일을 사용한다면 다시 다운받지 않아도 되기 때문에 
      페이지 로딩 속도가 높아진다.
  - 단점?
    - 핵심 라이브러리가 외부사이트에 있기 때문에 의도적인 조작을 막을 수 없다.
    - 즉 보안에 좋지 않다.
    - 그래서 보안을 중시하는 경우, 자신들의 웹 서버에 다운로드 받아 사용한다.
    
2) 로컬 다운로드 방식
  - 라이브러리를 로컬(자신의) 웹서버에 다운로드 받아서 링크를 건다.

## 3.3 - 자바스크립트 라이브러리 압축하기
- zip 형식으로 압축하는 것이 아니다.
- 자바스크립트는 zip 형식으로 압축하면 안된다.
  왜? 소스 코드를 직접 인터프리팅하기 때문이다.
  압축되어 있으면 해석이 불가능하다.
- 그러면 "자바스크립트 압축"은 무슨 의미냐?
  - 공백제거, 주석제거, 변수명은 가능한 알파벳 1자나 2자로 줄인다.
  - 이렇게 해서 물리적인 파일의 크기를 줄이는 것을 말한다.
  - 사람의 손으로? NO! 프로그램 이용하라!
  
## 3.2 - 자바스크립트 정리 : 자주 사용하는 함수를 라이브러리로 만들기
- 태그 찾는 것

## 3.1 - 프론트 엔드 정리 : HTML과 자바스크립트, CSS를 분리하라.

## 3.0 - AJAX를 이용하여 서버와 클라이언트를 분리하기
- /WEB-INF/web.xml에 *.json 요청을 처리할 프론트 컨트롤러 서블릿 추가
- /WEB-INF/conf/json-servlet.xml 스프링 설정 파일 추가
- JSON 문자열 출력 ViewResolver 라이브러리 가져오기
  - build.gradle 변경
- JSON 문자열을 출력하는 페이지 컨트롤러 생성
  - StudentJsonControl, AuthJsonControl
- HTML 생성
  - /webapp/student/main.html 생성
  - /webapp/student/view.html 생성
  - /webapp/auth/main.html 생성
  - /webapp/js/bit.js 생성
  - /webapp/js/common.js 생성
  - /webapp/header.html
  - /webapp/footer.html
  - /webapp/sidebar.html
- 설정 파일 
  - /webapp/WEB-INF/web.xml 변경 : json 요청 처리 프론트 컨트롤러 추가
  - /webapp/WEB-INF/conf/json-servlet.xml 추가
  - /webapp/WEB-INF/conf/dispatcher-servlet.xml 변경

## 2.9 - 클라이언트에서 JSP를 직접 접근하지 못하게 막아야 한다. 
- JSP 파일을 WEB-INF 밑으로 옮긴다.
  - 왜? WEB-INF 폴더는 클라이언트에서 접근할 수 없다.
  - WEB-INF 폴더는 웹애플리케이션의 설정 파일을 두는 폴더이기 때문이다.
- 작업
  1) /WEB-INF/view 폴더 생성  
  2) view 폴더로 JSP 파일을 옮긴다.
  3) dispatcher-servlet.xml 파일에 JSP prefix 경로를 바꾼다.
  4) form.html을 JSP로 바꾼다.
  5) 각 페이지 컨트롤러에 form.jsp로 보내는 메서드를 추가한다.
  6) list.jsp에서 form.html 링크를 form.do로 바꾼다.
  
## 2.8 - Log4J 적용
- log4j 의존 라이브러리 추가
  - build.gradle 변경
- log4j.properties 파일 추가
  - 콘솔 출력 설정
  
## 2.7 - 요청 파라미터의 커스텀 에디터 등록하기
- @ControllerAdvice를 이용하여 등록
- WebControllerAdvice 클래스 추가

## 2.6 - 트랜잭션 처리
- 트랜잭션? 여러 개의 작업을 한 단위로 묶은 것.
- 작업
  - aop 관련 의존 라이브러리 추가: build.gradle
  - 트랜잭션 advice 설정 추가: application-context.xml

## 2.5 - 페이지 컨트롤러에서 비즈니스 로직 분리하여 Service 객체 만들기
- 비즈니스 로직?
  - 업무를 처리하는 로직
  - 화면 데이터 준비와는 상관없다.
  - 파라미터로 들어온 데이터를 가공하는 것과 상관없다.
  - 트랜잭션을 다룬다.
  - 업무로직을 분리하면 해당 객체의 재 사용성이 높아진다.
- service 패키지 생성
- Service 객체의 인터페이스 정의
  - StudentService, ManagerService, TeacherService, AuthService
- Service 객체의 구현체 생성
  - StudentServiceImpl, ManagerServiceImpl, TeacherServiceImpl, AuthServiceImpl

## 2.4 - 로그인 여부 검사하기
-방법1) 서블릿 필터 이용하기
- LoginCheckFilter 생성
  - 로그인을 수행하는 URL을 제외하고, 나머지 모든 URL에 대해서는 로그인 여부를 검사한다.
  - 로그인이 되어 있지 않으면 로그인 화면으로 리다이렉트 한다.
-방법2) 스프링의 인터셉터 이용하기
- LoginInterceptor 생성
  - 위의 필터와 같이 동작하게 한다.

## 2.3 - Persistence Framework인 Mybatis 적용하기
- DBMS 접속 정보를 담은 프로퍼티 파일 준비
  - /WEB-INF/conf/jdbc.properties 준비
- SQL Mapper 파일 준비
  - resources/bitcamp/java89/ems2/dao/*Dao.xml 준비
- 스프링 IoC 컨테이너 설정 파일에 Mybatis 관련 객체 등록
  - /WEB-INF/conf/application-context.xml 변경
- 의존 라이브러리 추가
  - build.gradle 변경 : mybatis, mybatis-spring, spring-jdbc, commons-dbcp
  - "gradle eclipse" 실행
  - 프로젝트 리프래시
  
## 2.2 - 페이지 컨트롤러에서 요청 핸들러의 파라미터 값 적용
- /WEB-INF/conf/dispatcher-servlet.xml 에 부가 장치 등록
  - 뷰 리졸버 적용
  - MVC 애노테이션 처리기 적용.
  - 파일 업로드 처리기 적용
- 페이지 컨트롤러 변경
  - HttpServletRequest, HttpServletResponse를 파라미터로 받는 대신 직접 파라미터 값을 받기
  - JSP URL 리턴 값 변경


## 2.1 - DispatcherServlet을 스프링의 프론트 컨트롤러로 대체하기
- web.xml에 스프링 DispatcherServlet 등록하기
  - /WEB-INF/conf/dispatcher-servlet.xml 생성
  - /WEB-INF/conf/application-context.xml 변경
- 우리가 만든 애노테이션과 프론트 컨트롤러와 RequestHandlerMapping 제거
- 페이지 컨트롤러에 스프링의 애노테이션을 적용하라!


## 2.0 - 애노테이션을 적용하여 페이지 컨트롤러에서 호출될 메서드를 지정하기
- @RequestMapping, @RequestParam 애노테이션 추가
  - annotation 패키지 생성
  - 애노테이션 생성
- RequestHandlerMapping 클래스 생성
  - 페이지 컨트롤러의 메서드 중에서 @RequestMapping이 붙은 메서드를 추출하여
    별도로 관리한다.
  - 요청이 들어오면 이 클래스에 보관된 메서드를 찾아 호출한다.
- 페이지 컨트롤러에 적용
  - C(reate), R(ead), U(pdate), D(elete)에 해당하는 페이지 컨트롤러를
    한 클래스로 합친다.
  - StudentControl, ManagerControl, TeacherControl, AuthControl 클래스 생성

## 1.9 - 스프링 필터와 리스너 사용하기
- 스프링 필터와 리스너를 사용하려면 spring-webmvc 라이브러리를 추가해야 한다.
  - build.gradle 변경
  - "gradle eclipse" 다시 실행
- CharacterEncodingFilter를 스프링 클래스로 대체한다.
  - web.xml에 필터 설정 변경
- ContextLoaderListener를 스프링 클래스로 대체한다.
  -web.xml를 컨텍스트 파라미터 추가 
- 위에 설정에 맞추어 프론트 컨트롤러 변경
  - DispatcherServlet 클래스 변경

## 1.8 - 왼쪽에 메뉴 추가
- 왼쪽 메뉴를 출력하는 JSP 생성
  - /webapp/sidemenu.jsp 생성
- main.jsp에서 CSS 및 HTML 조정

## 1.7 - 화면의 틀을 공유하여 공통 코드 작성을 줄이는 방법
- 공통 화면의 틀을 작성한다.
  - /webapp/main.jsp
- 기존의 JSP 파일을 변경한다.
  - 공통 코드 제거한다.
- 로그인폼을 위한 페이지 컨트롤러 추가
  - LoginFormControl 생성
- 에러 출력 페이지에도 적용
  - DispatcherServlet 변경

## 1.6 - 프론트 컨트롤러 패턴 적용
- 모든 클라이언트의 요청을 한 서블릿에서 받는다.
  그리고 요청에 따라 작업할 객체에 실행을 위임한다.
  - DispatcherServlet 클래스 작성
- 프론트 컨트롤러와 페이지 컨트롤러의 호출 규칙을 정의
  - PageController 인터페이스 생성
-나머지 서블릿들은 일반 POJO 객체로 만든다.
  - 기존 서블릿을 PageController 구현체로 변경한다.
- AuthFilter를 이용하여 로그인 사용자의 사진 파일명을 준비한다.

## 1.5 - JSP를 사용하여 화면 출력 부분을 분리한다.
- MVC 구조를 완성한다.
- StudentListServlet에서 출력 분리
  - /webapp/student/list.jsp 파일 생성
  - /header.jsp 파일 생성
  - /footer.jsp 파일 생성
- JSTL 라이브러리 사용
  - build.gradle 변경
  - "gradle eclipse" 실행하여 라이브러리 다운로드 및 이클립스 설정 파일 생성
  - JSP 파일에 JSP 확장 태그 적용
- 강사 목록, 매니저 목록에 적용
- 상세 화면 출력에 JSP 적용
  - 학생, 강사, 매니저에 적용
- 로그인 화면에 JSP 적용
  - LoginServlet.java 변경
  - /auth/loginform.jsp 생성
  - /auth/loginfail.jsp 생성
- 머리말, 꼬리말에 JSP 적용
  - HeaderServlet.java 변경, /webapp/header.jsp 생성
  - FooterServlet.java 삭제, /webapp/footer.jsp 생성
- 오류 처리 서블릿에 JSP 적용
  - ErrorServlet.java 삭제, /webapp/error.jsp 생성

## 1.4 - 스프링 IoC 컨테이너 적용
- 스프링 라이브러리 파일 준비
  - build.gradle 파일에 스프링 의존 라이브버리 추가
  - 이클립스 설정 파일 갱신
- 스프링 IoC 컨테이너가 사용할 설정 파일 준비
  - /WEB-INF/conf/application-context.xml
- ContextLoaderListener 클래스 변경
  - 스프링 IoC 컨테이너 생성
- DAO 또는 DataSource 객체에 @Component 애노테이션을 붙인다.
  - 즉 스프링 IoC 컨테이너가 이런 애노테이션이 붙은 객체를 관리해준다.
- Servlet 클래스 변경
  - 스프링 IoC 컨테이너를 통해 DAO 객체를 얻어야 한다.

## 1.3 - 로그인 할 때 사용자의 유형을 지정하고, 로그인 사용자 사진 출력
- 로그인 폼에 사용자 유형 선택 추가
  - LoginServlet.doGet() 변경
- MemberDao 클래스 변경
  - exist(email, password)를 getOne(email, password)로 변경
- 로그인 사용자 사진 출력
  - HeaderServlet 클래스 변경

## 1.2 - 학생/매니저/강사 등록, 변경할 때 사진 업로드하기
- 의존 라이브러리 설정하기
  - build.gradle 파일에 apache-fileupload 라이브러리 추가
  - "gradle eclipse" 명령을 수행하여 이클립스 설정 파일을 갱신
- 학생 사진 업로드
  - student/form.html 파일 변경
    - form 태그에 enctype 속성 추가
  - StudentAddServlet, StudentUpdateServlet 클래스 변경
    - fileupload 라이브러리의 클래스를 사용하여 멀티파트 데이터 처리
  - StudentDetailServlet 클래스 변경
    - 사진 출력
- 매니저 사진 업로드
  - manager/form.html 파일 변경
    - form 태그에 enctype 속성 추가
  - ManagerAddServlet, ManagerUpdateServlet 클래스 변경
    - fileupload 라이브러리의 클래스를 사용하여 멀티파트 데이터 처리
  - ManagerDetailServlet 클래스 변경
    - 사진 출력
- 강사 사진 업로드
  - teacher/form.html 파일 변경
    - form 태그에 enctype 속성 추가
  - 도메인 객체 준비
    - 사진 정보를 저장할 클래스 생성 : Photo.java 
    - Teacher 클래스 변경 : 사진 정보를 저장할 인스턴스 변수 추가
  - TeacherAddServlet, TeacherUpdateServlet 클래스 변경
    - fileupload 라이브러리의 클래스를 사용하여 멀티파트 데이터 처리
  - TeacherDetailServlet 클래스 변경
    - 사진 출력
  - TeacherMysqlDao 클래스 변경

## 1.1 - 세션을 이용하여 로그인 사용자 정보 저장하고 꺼내기 
- LoginServlet의 doPost() 변경
  - MemberDao를 이용하여 로그인 사용자 정보를 가져온다.
  - 그리고 HttpSession 보관소에 저장한다.
- HeaderServlet 변경
  - 로그인 하지 않은 경우 "로그인 링크" 출력
  - 로그인 한 경우 "이름과 로그아웃 링크" 출력
- LogoutServlet 생성
  - 세션 무효화시킨다.

## 1.0 - 쿠키를 이용하여 로그인 아이디 저장하기, 로그인 처리
- 로그인 폼 생성
  - LoginFormServlet.java 생성
- MemberDao와 MemberMysqlDao 클래스에 exist(email, password) 메서드 추가
  - LoginServlet 클래스에 로그인 처리 코드 추가
  
## 0.9 - Listener 컴포넌트를 이용하여 웹 애플리케이션에서 사용할 도구 준비하기
- 웹 애플리케이션이 시작되면 서블릿들이 공동으로 사용할 객체를 준비시킨다.
  - ServletContextListener 구현체를 만든다.
  - 즉 ContextLoaderListener 클래스 정의
    이 클래스는 기존의 ContextLoaderServlet의 역할을 대체할 것이다.
- web.xml 파일에 리스너 등록

## 0.8 - Filter 컴포넌트를 사용하여 POST 요청 데이터의 문자집합을 자동으로 설정한다.
- CharacterEncodingFilter 클래스 정의
  - POST 요청 데이터의 문자 집합 지정하는 코드를 추가한다.
  - 그런 후 서블릿을 실행하게 한다.
- 인코딩 필터를 DD 파일(web.xml)에 등록한다.
- 서블릿에서 request.setCharacterEncoding() 호출 코드를 제거한다.

## 0.7 - ServletContext 보관소 기능을 사용하여 DAO 공유하기
- DataSource 클래스에서 Singleton 패턴을 제거한다.
- 기존의 DAO 클래스에서 Singleton 패턴을 제거한다.
- 다른 서블릿이 사용할 객체를 준비시키는 서블릿을 만든다.
  - ContextLoaderServlet 클래스 정의
- DAO 클래스의 인터페이스를 정의한다.
- 서블릿에서 DAO 클래스 대신 인터페이스를 사용하도록 변경한다.

## 0.6 - ServletRequest의 보관소 기능을 이용하여 예외 정보 공유
- 서블릿에서 예외가 발생하면 ErrorServlet으로 실행을 위임한다.
  이때 오류 정보를 ServletRequest에 담아서 넘긴다.
- ErrorServlet은 ServletRequest에 보관된 오류 정보를 꺼내서 출력한다.
- 모든 서블릿 코드 변경
- ErrorServlet 코드 변경 

## 0.5 - 포워딩 적용
- 서블릿에서 오류가 발생하면 오류를 처리하는 서블릿으로 포워딩 시킨다.
- ErrorServlet 클래스 정의
- 서블릿에 포워드 적용
- HeaderServlet과 FooterServlet 적용
- 서블릿에 인클루딩 적용

## 0.4 - 강사 관리 기능 구현
- domain 객체 구현
  - Teacher 클래스 구현
- DAO 객체 구현
  - TeacherMysqlDao 클래스 구현
- 서블릿 객체 구현
  - TeacherListServlet, TeacherAddServlet, TeacherDetailServlet, 
    TeacherUpdateServlet, TeacherDeleteServlet 클래스 구현 
        
## 0.3 - 매니저 관리 기능 구현
- domain 객체 구현
  - Manager 클래스 구현
- DAO 객체 구현
  - ManagerMysqlDao 클래스 구현
- 서블릿 객체 구현
  - ManagerListServlet, ManagerAddServlet, ManagerDetailServlet, 
    ManagerUpdateServlet, ManagerDeleteServlet 클래스 구현 
        
## 0.2 - 학생 관리 기능 구현
- 프로젝트 기본 패키지 준비
  - bitcamp.java89.ems2 패키지 생성
  
- domain 객체 구현
  - bitcamp.java89.ems2.domain 패키지 생성
  - Student.java 클래스 정의
- DAO 객체 구현
  - MemberDao, StudentDao, ManagerDao, TeacherDao 인터페이스 생성
  - MemberMysqlDao, StudentMysqlDao, ManagerMysqlDao, TeacherMysqlDao 클래스 정의
- 서블릿 구현
  - StudentListServlet, StudentAddServlet, StudentDetailServlet, 
    StudentUpdateServlet, StudentDeleteServlet 클래스 정의

## 0.1 - Gradle 웹 프로젝트 기본 골격 생성
- 소스 폴더 생성(/src/...)
- MySQL JDBC Driver 파일 준비(/libs/xxx.jar)
- Gradle 설정 파일 준비(/build.gradle)
- GitIgnore 설정 파일 준비(/.gitignore)
- README.md 파일 변경