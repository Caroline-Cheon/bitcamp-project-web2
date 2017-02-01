//학생 목록 가져와서 tr 태그를 만들어 붙인다.
$.getJSON('list.json', function(ajaxResult) {
	/* @RestController 페이지 컨트롤러의 메서드가 리턴한 값은
	 * AjaxResult의 프로퍼티 값으로 JSON 문자열을 만든다.
	 * 따라서 status나 data 프로퍼티를 바로 꺼내면 된다.
	 */
  var status = ajaxResult.status;
  
  if (status != "success")
    return;
  
  var list = ajaxResult.data;
  var tbody = $('#list-table > tbody');
  
  for (var student of list) {
    $("<tr>")
      .html("<td>" + 
	      student.memberNo + "</td><td><a class='name-link' href='#' data-no='" + 
	      student.memberNo + "'>" + 
	   	  student.name + "</a></td><td>" + 
	      student.tel + "</td><td>" + 
	      student.working + "</td><td>" +
	      student.grade + "</td><td>" +
	      student.schoolName + "</td>")
	  .appendTo(tbody);
  }
	// 학생 목록에서 이름 링크에 click 이벤트를 처리한다.
	$('.name-link').click(function(event) {
		event.preventDefault(); // a 동작이 안되게 해야 한다.
		location.href = 'view.html?memberNo=' + this.getAttribute('data-no'); 
		// onclick 객체는 a에 속한다. 같은객체에 속하면 this를 적는다.
	});
});

$('#new-btn').click(function(event) {
	event.preventDefault();
	location.href = 'view.html';
});
