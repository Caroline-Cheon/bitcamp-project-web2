document.querySelector('#login-btn').onclick = function() {
	// 이메일 저장이 체크되어 있으면, 쿠키에 저장하고
	// 체크되어 있지 않으면 쿠키에서 제거한다.
	if (document.querySelector('#save-email').checked) {
	    setCookie('email', document.querySelector('#email').value, 30);
	  } else {
	    setCookie('email', '', 0);
	    //setCookie('email', '', 0, 'bitcamp-project-web2/auth');
	  }
	
	var param = {
		email: document.querySelector('#email').value,
		password: document.querySelector('#password').value
	};
	
	var userTypeList = document.querySelectorAll('input[name=user-type]'); // 배열이 아니라 컬렉션 타입이 넘어온다.
	for (var i = 0; i < userTypeList.length; i++) {
		if (userTypeList[i].checked) {
			param.userType = userTypeList[i].value; 
			break;
		}
	}

	post('login.json', param, function(jsonText) {
		var ajaxResult = JSON.parse(jsonText);
		//console.log(ajaxResult);
		
		if (ajaxResult.status == "success") { // 암호가 맞으면 화면이동
			location.href = "../student/main.html";
			return;
		}
		// 암호가 맞지 않으면 서버가 보낸 결과 팝업을 띄워준다.(네트워크 오버헤드를 줄일 수 있다.---> 굳이 네트워크와 연결하지 않고 정보를 주는 것)
		alert(ajaxResult.data);
	});
}

// email 쿠키가 있다면 값을 넣는다. email에 서버에서 설정한 쿠키값을 넣어준다.
document.querySelector('#email').value = getCookie('email').replace(/"/g,'');