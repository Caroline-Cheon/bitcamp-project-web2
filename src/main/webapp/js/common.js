window.addEventListener("load", function() {
// header.html을 가져와서 붙인다.
	get('../header.html', function(result) {
	  //서버에서 로그인 사용자 정보를 가져온다.
	  get('../auth/loginUser.json', function(jsonText) {
		  var ajaxResult = JSON.parse(jsonText); // 파싱
		  
		  document.querySelector('#header').innerHTML = result;
		  // 화면에 뿌려지는 퍼포먼스를 고려하기위해서는 (로그인, 로그아웃이 동시에 나타나는 상태의 시간을 줄이기 위해서)
		  // 최대한 화면에 늦게 출력한다. 그리고 바로 불필요한 창을 감춘다.
		  
		  if (ajaxResult.status == "fail") { // 로그인 되지 않았으면,
			  // 로그온 상태 출력 창을 감춘다.
			  document.querySelector('#logon-div').style.display = 'none';
			  return;
		  }
		  
		  // 로그인 되었으면, 로그오프 상태 출력 창을 감춘다.
		  document.querySelector('#logoff-div').style.display = 'none';
		  document.querySelector('#logon-div img').src =
			  '../upload/' + ajaxResult.data.photoPath;
		  document.querySelector('#logon-div span').textContent = 
			  ajaxResult.data.name;
	  })
	});
	
// sidebar.html을 가져와서 붙인다.
	get('../sidebar.html', function(result) {
		document.querySelector('#sidebar').innerHTML = result;
	});
	
// footer.html을 가져와서 붙인다.
	get('../footer.html', function(result) {
		document.querySelector('#footer').innerHTML = result;
	});
});