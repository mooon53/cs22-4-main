function login2(username, password) {
	if (!username) {username = document.getElementById("usernameLogIn").value;}
	if (!password) {password = preparePassword(document.getElementById("passwordLogIn").value);}
	const content = JSON.stringify({username, password});
	let request = makeRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			const response = JSON.parse(this.responseText);
			if (response.success) {
				setLoginCookies(username);
				location.href = "/";
			}  // TODO: change text on screen to reflect not success with reason
		}
	}
	request.open("POST", "rest/accounts", true);
	request.setRequestHeader("sessionId", getSessionId());
	request.setRequestHeader("Content-Type", "application/json");
	console.log(content)
	request.send(content);
}

function setLoginCookies(username) {
	const expiry = Number.parseInt(getCookies().get("sessionExpires"));
	document.cookie = `loggedIn=${true};expires=${new Date(expiry).toUTCString()};`;
	document.cookie = `account=${username};expires=${new Date(expiry).toUTCString()};`;
}