<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>학생 정보</h1>
<a href='form.do'>추가</a><br>
<table border='1'>
<thead>
<tr>
<th>회원번호</th>
<th>이름</th>
<th>전화</th>
<th>재직</th>
<th>최종학력</th>
<th>학교명</th>
</tr>
</thead>

<tbody>
<c:forEach var="student" items="${students}">
<tr> 
  <td>${student.memberNo}</td>
  <td><a href='detail.do?memberNo=${student.memberNo}'>${student.name}</a></td>
  <td>${student.tel}</td>
  <td>${student.working}</td>
  <td>${student.grade}</td>
  <td>${student.schoolName}</td>
</tr>
</c:forEach>
</tbody>
</table>

