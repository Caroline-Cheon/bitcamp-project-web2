$('#submit-btn').click(function() {
	// 이메일 저장이 체크되어 있으면, 쿠키에 저장하고
	// 체크되어 있지 않으면 쿠키에서 제거한다.
	if ($('#save-email').is(':checked')) {
	    setCookie('email', $('#email').val(), 30);
	} else {
	  setCookie('email', '', 0);
	}
	
	var param = {
		email: $('#email').val(),
		password: $('#password').val(),
		userType: $('input[name=user-type]:checked').val()
	};
	
	$.post('login.json', param, function(ajaxResult) {
		if (ajaxResult.status == "success") { // 암호가 맞으면 화면이동
			location.href = "../student/main.html";
			return;
		}
		// 암호가 맞지 않으면 서버가 보낸 결과 팝업을 띄워준다.(네트워크 오버헤드를 줄일 수 있다.---> 굳이 네트워크와 연결하지 않고 정보를 주는 것)
		alert(ajaxResult.data);
	}, 'json');
	
}); // click()

// email 쿠키가 있다면 값을 넣는다. email에 서버에서 설정한 쿠키값을 넣어준다.
$('#email').val(getCookie('email').replace(/"/g,''));