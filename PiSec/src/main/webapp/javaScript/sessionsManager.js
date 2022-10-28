"use strict";

function checkSession() {
	let cookieMap = getCookies();
	if (!cookieMap.has("sessionId") || cookieMap.get("sessionExpires") < new Date().getTime()) newSession();
	if (!cookieMap.get("loggedIn") && !location.href.endsWith("login2.html")) /*location.href = "login.html"*/ console.log("you are not logged in, in the future this will redirect");
}

function checkSessionServer() {
	let request = makeRequest("GET", "/rest/sessions");
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			const response = JSON.parse(this.responseText);
			document.cookie = `sessionId=${response.sessionId};expires=${new Date(response.expiration).toUTCString()};`;
			document.cookie = `sessionExpires=${response.expiration};expires=${new Date(response.expiration).toUTCString()};`;
			document.cookie = `loggedIn=${response.loggedIn};expires=${new Date(response.expiration).toUTCString()};`;
			if (response.loggedIn) document.cookie = `account=${response.account};expires=${new Date(response.expiration).toUTCString()};`;
		} else newSession();
	};
	request.setRequestHeader("Accept", "application/json");
	request.send();
}

function newSession() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			document.cookie = `sessionId=${response.sessionId};expires=${new Date(response.expiration).toUTCString()};`;
			document.cookie = `sessionExpires=${response.expiration};expires=${new Date(response.expiration).toUTCString()};`;
			document.cookie = `loggedIn=false;expires=${new Date(response.expiration).toUTCString()};`;
		}
	};
	request.open("PUT", "/rest/sessions", false);
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