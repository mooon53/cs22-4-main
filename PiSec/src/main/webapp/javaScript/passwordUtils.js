function preparePassword(password) {
	return hash(salt(password));  // peppering happens server-side, since the key is supposed to be secret, so doing this browser side makes no sense
}

function hash(password) {
	return password;  // TODO: add hashing algorithm
}

function salt(password) {
	return password;  // TODO: add salting algorithm
}