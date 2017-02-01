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

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setCookie(cname, cvalue, exdays, path) {
	if (path == undefined) {
		path = "/";
	}
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=" + path;
}