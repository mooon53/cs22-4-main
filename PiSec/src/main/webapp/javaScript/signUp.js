function signUp() {
	const username = document.getElementById("username").value;
	const password = preparePassword(document.getElementById("password").value);
	const content = JSON.stringify({username, password});
	let request = makeRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 204) {
			login(email, password);
		}
	}
	request.open("PUT", "rest/accounts", true);
	request.setRequestHeader("Content-Type", "application/json");
	request.send(content);
}