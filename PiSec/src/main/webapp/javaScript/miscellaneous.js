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

const notificationTemplate = (msg, isSucess, id) => `
  <div class="notification${isSucess ? '' : ' notifiError'}" id="${id}">
    <div class="notificationContent" id="notificationContent">
      ${msg}
    </div>
    <span class="material-symbols-outlined unselectable" onclick="document.getElementById(${id}).remove()">
      X
    </span>
  </div>`