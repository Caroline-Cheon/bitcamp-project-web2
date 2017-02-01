
try {
	var memberNo = location.href.split('?')[1].split('=')[1];
} catch (error) {
	var memberNo = -1;
}

if (memberNo > 0) {
	prepareViewForm();
} else {
	prepareNewForm();
}

function prepareViewForm() {
	// 등록 버튼은 감춘다.
	var tags = document.querySelectorAll('.new-form');
	for (var i = 0; i < tags.length; i++) {
		tags[i].style.display = 'none';
	}
	
	
	//학생 목록 가져와서 tr 태그를 만들어 붙인다.
	get('detail.json?memberNo=' + memberNo, function(jsonText) {
		// result JSON 문자열을 자바스크립트 객체로 만든다.
	  var ajaxResult = JSON.parse(jsonText); 
		//ajaxResult는 우리가 정한 것이 아님 StudentJsonControl.java 에서 메소드 리턴 확인
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  alert(ajaxResult.data);
		  return;
	  }
	  
	  var student = ajaxResult.data; // data를 가져온 뒤에 화면에 뿌릴 것이다.
	  console.log(student);
	  
	  document.querySelector('#email').value = student.email;
	  document.querySelector('#name').value = student.name;
	  document.querySelector('#tel').value = student.tel;
	  if (student.working) {
		  document.querySelector('#working').checked = 'checked';
	  } else {
		  document.querySelector('#not-working').checked = 'checked';
	  }
	  document.querySelector('#grade').value = student.grade;
	  document.querySelector('#school-name').value = student.schoolName;
	  document.querySelector('#photo-img').src = "../upload/" + student.photoPath;
	});
	
	// 삭제, 변경 버튼을 클릭했을 때 호출될 함수(클릭 이벤트 핸들러)등록
	document.querySelector('#delete-btn').onclick = function() {
		get('delete.json?memberNo=' + memberNo, function(jsonText) {
			var ajaxResult = JSON.parse(jsonText);
			if (ajaxResult.status != "success") {
				alert(ajaxResult.data);
				return;
			}
			location.href = 'main.html';
		});
	}
	
	document.querySelector('#update-btn').onclick = function() {
		
	  var param = {
			"memberNo": memberNo,
			"name": document.querySelector('#name').value,
			"tel": document.querySelector('#tel').value,
			"email": document.querySelector('#email').value,
			"password": document.querySelector('#password').value,
			"working": document.querySelector('#working').checked,
			"grade": document.querySelector('#grade').value,
			"schoolName": document.querySelector('#school-name').value,
	  };
		
	  post('update.json', param, function(jsonText) {
		  var ajaxResult = JSON.parse(jsonText);
		  if (ajaxResult.status != "success") {
			  alert(ajaxResult.data);
			  return;
		  }
		  location.href = 'main.html';
	  });
	};
	
} // prepareViewForm() 

function prepareNewForm() {
	// 변경, 삭제 버튼은 감춘다.
  var tags = document.querySelectorAll('.view-form');
  for (var i = 0; i < tags.length; i++) {
    tags[i].style.display = 'none';
  }
  
  document.querySelector('#add-btn').onclick = function() {
	    
    var param = {
      "name": document.querySelector('#name').value,
      "tel": document.querySelector('#tel').value,
      "email": document.querySelector('#email').value,
      "password": document.querySelector('#password').value,
      "working": document.querySelector('#working').checked,
      "grade": document.querySelector('#grade').value,
      "schoolName": document.querySelector('#school-name').value,
    };
    
    post('add.json', param, function(jsonText) {
      var ajaxResult = JSON.parse(jsonText);
      if (ajaxResult.status != "success") {
        alert(ajaxResult.data);
        return;
      }
      location.href = 'main.html';
    });
  };
}

// 목록 버튼을 클릭했을 때 호출될 함수(이벤트핸들러) 등록
document.querySelector('#list-btn').onclick = function() {
	location.href = 'main.html';
}