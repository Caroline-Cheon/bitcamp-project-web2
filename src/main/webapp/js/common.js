$(function() {
	// header.html을 가져와서 붙인다.
	$.get('../header.html', function(result) {
	  //서버에서 로그인 사용자 정보를 가져온다.
		$.getJSON('../auth/loginUser.json', function(ajaxResult) {
		  $('#header').html(result); 
		  
		  if (ajaxResult.status == "fail") { // 로그인 되지 않았으면,
			  // 로그온 상태 출력 창을 감춘다.
			  $('#logon-div').css('display', 'none');
			  
			  // 로그인버튼의 클릭 이벤트 핸들러 등록하기
			  $('#login-btn').click(function(event) {
				  event.preventDefault()
				  location.href = '../auth/main.html'
			  });
			  return;
		  }
		  
		  // 로그인 되었으면, 로그오프 상태 출력 창을 감춘다.
		  $('#logoff-div').css('display', 'none');
		  $('#logon-div img').attr('src','../upload/' + ajaxResult.data.photoPath);
		  $('#logon-div span').text(ajaxResult.data.name);
		  
		  // 로그아웃 버튼의 클릭 이벤트 핸들러 등록하기
		  $('#logout-btn').click(function(event) {
			  event.preventDefault();
			  $.getJSON('../auth/logout.json', function(jsonText) {
				  location.href = '../auth/main.html'
			  });
		  });
	  });
	});
	
	// sidebar.html을 가져와서 붙인다.
	$.get('../sidebar.html', function(result) {
		$('#sidebar').html(result);
	});
	
	// footer.html을 가져와서 붙인다.
	$.get('../footer.html', function(result) {
		$('#footer').html(result);
	});
});