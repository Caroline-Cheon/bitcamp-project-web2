window.addEventListener("load", function() {
// header.html을 가져와서 붙인다.
	get('../header.html', function(result) {
		document.querySelector('#header').innerHTML = result;
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