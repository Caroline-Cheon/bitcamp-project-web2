# java89-project-web2

## 1.2 - 학생 등록시 사진 업로드하기
- 의존 라이브러리 설정하기
  - build.gradle 파일에 apache-fileupload 라이브러리 추가
  - "gradle eclipse" 명령을 수행하여 이클립스 설정 파일을 갱신
- student/form.html 파일 변경
  - form 태그에 enctype 속성 추가
- StudentAddServlet 클래스 변경
  - fileupload 라이브러리의 클래스를 사용하여 멀티파트 데이터 처리

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