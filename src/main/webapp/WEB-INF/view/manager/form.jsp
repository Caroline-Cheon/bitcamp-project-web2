<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>매니저 등록폼</h1>
<form action='add.do' method='POST' enctype="multipart/form-data">
<table border='1'>
<tr><th>이메일</th><td><input name='email' type='text' placeholder='예)hong@test.com'></td></tr>
<tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th>이름</th><td><input name='name' type='text' placeholder='예)홍길동'></td></tr>
<tr><th>전화</th><td><input name='tel' type='text' placeholder='예)010-1111-2222'></td></tr>
<tr><th>직책</th><td><input name='position' type='text' placeholder='예)대리'></td></tr>
<tr><th>팩스</th><td><input name='fax' type='text' placeholder='예)02-1234-5678'></td></tr>
<tr><th>사진</th><td><input name='photo' type='file'></td></tr>
</table>
<button type='submit'>등록</button>
 <a href='list.do'>목록</a>
</form>