<%@page import="java.io.PrintWriter"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>애플리케이션 오류!</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<h1>오류 내용</h1>
<pre>
<jsp:useBean id="error" class="java.lang.Throwable" scope="request"/>
<%
error.printStackTrace(new PrintWriter(out));
%>
</pre>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>

    