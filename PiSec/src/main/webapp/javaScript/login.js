function login(username, password) {
	if (!username) {username = document.getElementById("loginName").value;}
	if (!password) {password = document.getElementById("loginPassword").value;}

	document.getElementById("loginName").classList.remove('invalidInput')
	document.getElementById("loginPassword").classList.remove('invalidInput')

	if (username === ""){
		document.getElementById("loginName").classList.add('invalidInput')
		showNotification("Fill in a username", false);
		return;
	}
	if (password === ""){
		document.getElementById("loginPassword").classList.add('invalidInput')
		showNotification("Fill in a password", false);
		return;
	}
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
				location.href = "/";
			} else {
				document.getElementById("loginName").classList.add('invalidInput')
				document.getElementById("loginPassword").classList.add('invalidInput')
				showNotification("Login details are incorrect", false);
			}
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


document.getElementById("loginName").addEventListener("focusout",() => document.getElementById("loginName").classList.remove('invalidInput'));
document.getElementById("loginPassword").addEventListener("focusout",() => document.getElementById("loginPassword").classList.remove('invalidInput'));