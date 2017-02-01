//학생 목록 가져와서 tr 태그를 만들어 붙인다.
get('list.json', function(jsonText) {
	/* @RestController 페이지 컨트롤러의 메서드가 리턴한 값은
	 * AjaxResult의 프로퍼티 값으로 JSON 문자열을 만든다.
	 * 따라서 status나 data 프로퍼티를 바로 꺼내면 된다.
	 */
  var ajaxResult = JSON.parse(jsonText);
  var status = ajaxResult.status;
  
  if (status != "success")
    return;
  
  var list = ajaxResult.data;
  var tbody = document.querySelector('#list-table > tbody');
  
  for (var student of list) {
    var tr = document.createElement("tr");
    tr.innerHTML = "<tr><td>" + 
      student.memberNo + "</td><td><a class='name-link' href='#' data-no='" + 
    	student.memberNo + "'>" + 
      student.name + "</a></td><td>" + 
      student.tel + "</td><td>" + 
      student.working + "</td><td>" +
      student.grade + "</td><td>" +
      student.schoolName + "</td></tr>";
    tbody.appendChild(tr);  // 처음에는 tbody가 빈 상테 -> 반복문을 돌면서 채워진다.
  }
	// 학생 목록에서 이름 링크에 click 이벤트를 처리한다.name-link 라벨이 붙은 모든 링크를 찾는다.
	var al = document.querySelectorAll('.name-link');
	for (var a of al) {
		a.onclick = function(event) {
			event.preventDefault(); // a 동작이 안되게 해야 한다.
			location.href = 'view.html?memberNo=' + this.getAttribute('data-no'); 
			// onclick 객체는 a에 속한다. 같은객체에 속하면 this를 적는다.
		}
	}
});

//추가 버튼에 클릭 이벤트 핸들러(리스너) 등록하기
document.querySelector('#new-btn').onclick = function(event) {
	// a태그를 클릭하면 기본으로 href에 설정된 URL을 요청한다.
	// 이 기본 행동을 막아야 한다.
	event.preventDefault();
	
	// 다음과 같이 자바스크립트 명령으로 화면을 이동하면,
	// 캐시된 파일이 로딩되지 않고 정상적으로 자바스크립트를 실행한다.
	location.href = 'view.html';
};
