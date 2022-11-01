"use strict";

function signUp() {
	const hashObj = new jsSHA("SHA-512", "TEXT", {numRounds: 1});
	hashObj.update(document.getElementById("signupPassword").value);

	const username = document.getElementById("signupName").value;
	const password = hashObj.getHash("HEX");
	const content = JSON.stringify({username, password});

	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 204) {
			setLoginCookies(username);
			window.href('/');  // redirect to main page.
		} else if (this.readyState === 4 && this.status === 500) {
			showNotification("Username already exits", false);
			// TODO: account with that username already exists, print this to the screen somwhere
		}
	}
	request.open("PUT", "rest/accounts", true);
	request.setRequestHeader("sessionId", getSessionId());
	request.setRequestHeader("Content-Type", "application/json");
	request.send(content);
}