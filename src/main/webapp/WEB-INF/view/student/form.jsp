<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>학생 등록폼</h1>
<form action='add.do' method='POST' enctype="multipart/form-data">
<table border='1'>
<tr><th>이메일</th><td><input name='email' type='text' placeholder='예)hong@test.com'></td></tr>
<tr><th>암호</th><td><input name='password' type='password'></td></tr>
<tr><th>이름</th><td><input name='name' type='text' placeholder='예)홍길동'></td></tr>
<tr><th>전화</th><td><input name='tel' type='text' placeholder='예)010-1111-2222'></td></tr>
<tr><th>재직여부</th><td><input type='radio' name='working' value='true' >재직중 
                         <input type='radio' name='working' value='false' checked>실업/미취업</td></tr>
<tr><th>최종학력</th><td>
  <select name='grade'>
    <option value='고졸'>고졸</option>
    <option value='전문학사'>전문학사</option>
    <option value='학사'>학사</option>
    <option value='석사'>석사</option>
    <option value='박사'>박사</option>
  </select>
</td></tr>
<tr><th>최종학교</th><td><input name='schoolName' type='text' placeholder='예)비트대학'></td></tr>
<tr><th>사진</th><td><input name='photo' type='file'></td></tr>
</table>
<button type='submit'>등록</button>
 <a href='list.do'>목록</a>
</form>