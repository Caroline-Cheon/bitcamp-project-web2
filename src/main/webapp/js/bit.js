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
