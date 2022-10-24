/**
 * Creates a new Http request with the necessary headers set, like session id
 * @returns {XMLHttpRequest} A new http request
 */
function makeRequest(method, url, async) {
	if (!async) async = true;
	let request = new XMLHttpRequest();
	request.open(method, url, async)
	request.setRequestHeader("sessionId", getSessionId());
	return request
}