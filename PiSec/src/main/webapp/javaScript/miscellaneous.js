
// NOTIFICATION ----------
function showNotification(msg, isSucess, delay){
    if (!document.getElementById('notifications')){
        // <div id="notifications"></div>
        const newNotificationContainer = document.createElement('div');
        newNotificationContainer.id = 'notifications';
        document.body.appendChild(newNotificationContainer);
    }
    if (delay == null) delay = isSucess ? 1000 : 2000;

    const date = new Date();
    const notificationElement = notificationTemplate(msg, isSucess, date.getTime());
    document.getElementById('notifications').insertAdjacentHTML('afterbegin', notificationElement);

    setTimeout(() => {
        closeNotification(date.getTime());
    }, delay);
}

function closeNotification(id){
    if (document.getElementById(id)){
        document.getElementById(id).style.opacity = 0;
        setTimeout(() => {
            document.getElementById(id).remove();
        }, 200);
    }
}

const notificationTemplate = (msg, isSucess, id) => `
  <div class="notification${isSucess ? '' : ' notifiError'}" id="${id}">
    <div class="notificationContent" id="notificationContent">
      ${msg}
    </div>
    <span class="material-symbols-outlined unselectable" onclick="document.getElementById(${id}).remove()">
      X
    </span>
  </div>`

// REQUESTS -----------
function makeCameraRequest(loadedFunc){
    const camerasRequest = makeRequest("GET", "rest/cameras", true);
    camerasRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (loadedFunc !== null)
                loadedFunc(response);
        } else if (this.status === 500 || this.status === 505){
            showNotification("Couldn't retrieve cameras")
        }
    }
    camerasRequest.setRequestHeader("Content-Type", "application/json");
    camerasRequest.send();
}
function makeNotificationsRequest(loadedFunc){
    const notificationRequest = makeRequest("GET", "rest/alerts", true);
    notificationRequest.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            // set the total notification amount in the nav bar
            const numOfAlerts = document.getElementById('numberOfAlerts')
            if (numOfAlerts) numOfAlerts.innerText = response.length;

            if (loadedFunc !== null)
                loadedFunc(response);
        } else if (this.status === 500 || this.status === 505){
            showNotification("Couldn't retrieve notifications")
        }
    };
    notificationRequest.setRequestHeader("Content-Type", "application/json");
    notificationRequest.send();
}

function makeSingleNotificationRequest(id, loadedFunc){
    const notificationRequest = makeRequest("GET", `rest/alerts/${id}`, true);
    notificationRequest.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.responseText);

            if (loadedFunc !== null)
                loadedFunc(response);
        } else if (this.status === 500 || this.status === 505){
            showNotification("Couldn't retrieve notifications")
        }
    };
    notificationRequest.setRequestHeader("Content-Type", "application/json");
    notificationRequest.send();
}



// used to get the parameters from the url, for instance henkie.com/test.html?parameter=value.
function getUrlVars() {
    const vars = {};
    const parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}