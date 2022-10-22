function login2(email, password) {
	if (!email) {const email = document.getElementById("usernameLogIn").value;}
	if (!password) {const password = preparePassword(document.getElementById("passwordLogIn").value);}
	else {const password = preparePassword(password);}
	let request = makeRequest();

}