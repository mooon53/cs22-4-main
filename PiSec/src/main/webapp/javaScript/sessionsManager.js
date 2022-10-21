// const requestUrl = new URL(location.href).origin + "/rest/";

function checkSession() {
	let cookieMap = getCookies();
	if (!cookieMap.has("sessionId") || cookieMap.get("sessionExpires") < new Date().getTime()) newSession();
	if (!cookieMap.loggedIn) /*location.href = "login.html"*/ console.log("you are not logged in, in the future this will redirect");
}

function newSession() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			document.cookie = `sessionId=${response.sessionId};expires=${new Date(response.expiration).toUTCString()};`;
			document.cookie = `sessionExpires=${response.expiration};`;
			document.cookie = `loggedIn=${response.loggedIn};`;
			if (response.loggedIn) document.cookie = `account=${response.account};`;
		}
	}
	request.open("GET", "/rest/sessions", false);
	request.setRequestHeader("Accept", "application/json");
	request.send();
}

function getCookies() {
	let cookies = document.cookie.split(";");
	let cookieMap = new Map();
	for (let i in cookies) {
		cookies[i] = cookies[i].replaceAll(" ", "");
		let split = cookies[i].split("=");
		cookieMap.set(split[0], split[1]);
	}
	return cookieMap;
}

function getSessionId() {
	let cookies = getCookies();
	return cookies.get("sessionId");
}