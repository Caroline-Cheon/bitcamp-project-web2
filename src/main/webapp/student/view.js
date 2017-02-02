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
	$('.new-form').css('display', 'none');
	
	//학생 목록 가져와서 tr 태그를 만들어 붙인다.
	$.getJSON('detail.json?memberNo=' + memberNo, function(ajaxResult) {
		//ajaxResult는 우리가 정한 것이 아님 StudentJsonControl.java 에서 메소드 리턴 확인
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  alert(ajaxResult.data);
		  return;
	  }
	  
	  var student = ajaxResult.data; // data를 가져온 뒤에 화면에 뿌릴 것이다.
	  console.log(student);
	  
	  $('#email').val(student.email);
	  $('#name').val(student.name);
	  $('#tel').val(student.tel);
	  if (student.working) {
		  $('#working').attr('checked', 'checked');
	  } else {
		  $('#not-working').attr('checked', 'checked');
	  }
	  $('#grade').val(student.grade);
	  $('#school-name').val(student.schoolName);
	  $('#photo-img').attr('src', '../upload/' + student.photoPath);
	});
	
	// 삭제, 변경 버튼을 클릭했을 때 호출될 함수(클릭 이벤트 핸들러)등록
	$('#delete-btn').click(function() {
		$.getJSON('delete.json?memberNo=' + memberNo, function(ajaxResult) {
			if (ajaxResult.status != "success") {
				alert(ajaxResult.data);
				return;
			}
			location.href = 'main.html';
		});// getJSON()
	});// click()
	
	$('#update-btn').click(function() {
	    var param = {
			"memberNo": memberNo,
			"name": $('#name').val(),
			"tel": $('#tel').val(),
			"email": $('#email').val(),
			"password": $('#password').val(),
			"working": $('#working').is(':checked'),
			"grade": $('#grade').val(),
			"schoolName": $('#school-name').val()
	  };
	  
	  $.post('update.json', param, function(ajaxResult) {
		  if (ajaxResult.status != "success") {
			  alert(ajaxResult.data);
			  return;
		  }
		  location.href = 'main.html';
	  }, 'json');
	  
	});// click()
	
} // prepareViewForm() 

function prepareNewForm() {
	// 변경, 삭제 버튼은 감춘다.
  $('.view-form').css('display', 'none');
  
  $('#add-btn').click(function() {
	  var param = {
			"name": $('#name').val(),
			"tel": $('#tel').val(),
			"email": $('#email').val(),
			"password": $('#password').val(),
			"working": $('#working').is(':checked'),
			"grade": $('#grade').val(),
			"schoolName": $('#school-name').val()
	  };
    
    $.post('add.json', param, function(ajaxResult) {
      if (ajaxResult.status != "success") {
        alert(ajaxResult.data);
        return;
      }
      location.href = 'main.html';
    }, 'json');// post()
    
  }); //click()
}

// 목록 버튼을 클릭했을 때 호출될 함수(이벤트핸들러) 등록
$('#list-btn').click(function() {
	location.href = 'main.html';
});