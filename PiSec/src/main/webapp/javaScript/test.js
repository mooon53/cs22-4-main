function onLoad() {
	let request = makeRequest("GET", "rest/alerts/1", true)
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			const response = JSON.parse(this.responseText);
			document.getElementById("body").innerHTML = `<img src="data:image/jpg;base64, ${response.image}">`
		}
	}
	request.send();
}

	Math.max([0, 3 ,1 , 4])