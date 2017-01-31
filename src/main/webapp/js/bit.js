function get(url, success) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	  if (xhr.readyState < 4)
	    return;
	  success(xhr.responseText);
	}
	xhr.open('get', url, true);
	xhr.send();
}

function post(url, data, success) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	  if (xhr.readyState < 4)
	    return;
	  success(xhr.responseText);
	}
	xhr.open('post', url, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	xhr.send(toQueryString(data));
}

function toQueryString(obj) {
	var qs = "";     //빈 문자열 준비
	for (var propName in obj) {
		if (qs.length > 0) {
			qs += "&";
		}
		qs += propName + "=" + obj[propName];
	}
	return qs;
}
