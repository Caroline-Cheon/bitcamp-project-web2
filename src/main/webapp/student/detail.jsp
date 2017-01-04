<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>학생관리-상세정보</title>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<h1>학생 정보</h1>
<form action='update.do' method='POST' enctype='multipart/form-data'>
<table border='1'>
<tr><th>이메일</th><td><input name='email' type='text' value='${student.email}'></td></tr>
<tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th>이름</th><td><input name='name' type='text' value='${student.name}'></td></tr>
<tr><th>전화</th><td><input name='tel' type='text' value='${student.tel}'></td></tr>
<tr><th>재직여부</th><td><input type='radio' name='working' value='true'
  <c:if test="${student.working}">checked</c:if> >재직중
  <input type='radio' name='working' value='false' 
  <c:if test="${!student.working}">checked</c:if> >실업/미취업</td></tr>
<tr><th>최종학력</th><td>
<select name='grade'>
  <option value='고졸' <c:if test="${student.grade == '고졸'}">selected</c:if>>고졸</option>
  <option value='전문학사' <c:if test="${student.grade == '전문학사'}">selected</c:if>>전문학사</option>
  <option value='학사' <c:if test="${student.grade == '학사'}">selected</c:if>>학사</option>
  <option value='석사' <c:if test="${student.grade == '석사'}">selected</c:if>>석사</option>
  <option value='박사' <c:if test="${student.grade == '박사'}">selected</c:if>>박사</option>
</select>
<tr><th>최종학교</th><td><input name='schoolName' type='text' value='${student.schoolName}'></td></tr>
<tr><th>사진</th><td><img src='../upload/${student.photoPath}' height='80'><input name='photoPath' type='file'></td></tr></table>
<button type='submit'>변경</button>
 <a href='delete.do?memberNo=${student.memberNo}'>삭제</a>
<input type='hidden' name='memberNo' value='${student.memberNo}'>
 <a href='list.do'>목록</a>
</form>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
