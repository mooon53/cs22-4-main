function onLoad() {
	const url = new URL(location.href);
	let id = 1;
	if (url.searchParams && url.searchParams.get("id")) {
		id = url.searchParams.get("id");
	}
	let request = makeRequest("GET", "rest/alerts/" + id, true)
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			const response = JSON.parse(this.responseText);
			document.getElementById("body").innerHTML = `<video controls src="data:video/mp4;base64, ${response.image}"/>`;
		}
	}
	request.send();
}