<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>시스템 정보</title>
</head>
<body>
<h1>스프링 컨테이너에 생성된 객체 목록</h1>
<c:forEach items="${beanClassNames}" var="className">
${className}<br>
</c:forEach>
</body>
</html>
