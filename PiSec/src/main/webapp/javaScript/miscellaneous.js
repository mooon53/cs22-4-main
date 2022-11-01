function showNotification(msg, isSucess, delay){
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

function requestNotifications() {
    if (this.readyState === 4 && this.status === 200) {
        const response = JSON.parse(this.responseText);

        const numOfAlerts = document.getElementById('numberOfAlerts')
        if (numOfAlerts) numOfAlerts.innerText = response.length;

        loadNotifications(response);
    } else if (this.status === 500 || this.status === 505){
        showNotification("Couldn't retrieve notifications")
    }
}
function requestCameras() {
    if (this.readyState === 4 && this.status === 200) {
        const response = JSON.parse(this.responseText);
        loadCameras(response);
    } else if (this.status === 500 || this.status === 505){
        showNotification("Couldn't retrieve cameras")
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