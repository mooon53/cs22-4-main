function signUp() {
	const username = document.getElementById("signupName").value;
	const password = preparePassword(document.getElementById("signupPassword").value);
	const content = JSON.stringify({username, password});
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 204) {
			setLoginCookies(username);
		} else if (this.readyState === 4 && this.status === 500) {
			// TODO: account with that username already exists, print this to the screen somwhere
		}
	}
	request.open("PUT", "rest/accounts", true);
	request.setRequestHeader("sessionId", getSessionId());
	request.setRequestHeader("Content-Type", "application/json");
	request.send(content);
}