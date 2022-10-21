/**
 * Creates a new Http request with the necessary headers set, like session id
 * @returns {XMLHttpRequest} A new http request
 */
function makeRequest() {
	let request = new XMLHttpRequest();
	request.setRequestHeader("sessionId", getSessionId());
	return request
}