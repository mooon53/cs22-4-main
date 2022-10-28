function login2(username, password) {
	if (!username) {username = document.getElementById("usernameLogIn").value;}
	if (!password) {password = document.getElementById("passwordLogIn").value;}
	const hashObj = new jsSHA("SHA-512", "TEXT", {numRounds: 1});
	hashObj.update(password);
	password = hashObj.getHash("HEX");
	const content = JSON.stringify({username, password});
	let request = makeRequest("POST", "rest/accounts");
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			const response = JSON.parse(this.responseText);
			if (response.success) {
				setLoginCookies(username);
				// location.href = "/";
			}  // TODO: change text on screen to reflect not success with reason
		}
	}
	request.setRequestHeader("Content-Type", "application/json");
	request.send(content);
}

function setLoginCookies(username) {
	const expiry = Number.parseInt(getCookies().get("sessionExpires"));
	document.cookie = `loggedIn=${true};expires=${new Date(expiry).toUTCString()};`;
	document.cookie = `account=${username};expires=${new Date(expiry).toUTCString()};`;
}