function loadPage() {
    // sessionId();
    // changeLogInButton();
}

function login(email, password) {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            if (response.success) location.href = "accountDetails.html";
            else alert("Wrong username or password :(");
        }
    };
    if (!email) email = document.getElementById("email").value;
    if (!password) password = stringToHashConversion(document.getElementById("password").value);
    let sessionId = getSessionId();
    let responseString = JSON.stringify({sessionId, email, password});
    request.open("POST", "rest/account", true);
    request.setRequestHeader("Accept", "application/json");
    request.setRequestHeader("Content-Type", "application/json");
    request.send(responseString);
}

function signUp() {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 204) {
            login(email, password);
        }
    };
    let email = document.getElementById('signupEmail').value;
    let password = stringToHashConversion(document.getElementById('signupPassword').value);
    let tel = stringToHashConversion(document.getElementById('telephone').value);
    let response;
    response = {email, password};
    if (!!tel) response["code"] = tel;
    let responseString = JSON.stringify(response);
    request.open("POST", "rest/signup", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(responseString);
    console.log(responseString);
}

function stringToHashConversion(string) {
    let hashVal = 0;
    if (string.length === 0) return null;
    for (let i = 0; i < string.length; i++) {
        const char = string.charCodeAt(i);
        hashVal = ((hashVal << 5) - hashVal) + char;
        hashVal = hashVal & hashVal;
    }
    return String(hashVal);
}

loadPage();