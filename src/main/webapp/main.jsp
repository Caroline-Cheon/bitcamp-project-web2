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
<title>${title}</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include>

<jsp:include page="${contentPage}"></jsp:include>
 
<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>
