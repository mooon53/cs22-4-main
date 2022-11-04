"use strict";

function signUp() {
	const passwordIn = document.getElementById("signupPassword").value
	const username = document.getElementById("signupName").value;
	if (username === ""){
		showNotification("Enter a username", false);
		document.getElementById("signupName").classList.add('invalidInput');
		return;
	}	
	if (passwordIn === ""){
		showNotification("Enter a password", false);
		document.getElementById("signupPassword").classList.add('invalidInput');
		return;
	}
	if (passwordIn.length < 5){
		showNotification("Password has to have at least 5 characters", false);
		document.getElementById("signupPassword").classList.add('invalidInput');
		return;
	}
	const hashObj = new jsSHA("SHA-512", "TEXT", {numRounds: 1});
	hashObj.update(passwordIn);

	const password = hashObj.getHash("HEX");
	const content = JSON.stringify({username, password});

	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 204) {
			setLoginCookies(username);
			window.href = '/';  // redirect to main page.
		} else if (this.readyState === 4 && this.status === 500) {
			showNotification("Username already exits", false);
			document.getElementById("signupName").classList.add('invalidInput');
			document.getElementById("signupPassword").classList.add('invalidInput');
		}
	}
	request.open("PUT", "rest/accounts", true);
	request.setRequestHeader("sessionId", getSessionId());
	request.setRequestHeader("Content-Type", "application/json");
	request.send(content);
}

document.getElementById("signupName").addEventListener("focusout",() => document.getElementById("signupName").classList.remove('invalidInput'));
document.getElementById("signupPassword").addEventListener("focusout",() => document.getElementById("signupPassword").classList.remove('invalidInput'));