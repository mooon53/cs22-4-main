fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

makeNotificationsRequest(null);

function loadPage(backEndData){
    let cameras = backEndData["cameras"];
    
    // get the current camera
    const urlParameters = getUrlVars();
    const currentCamera = cameras[urlParameters['id']];

    // set the number of devices in the nav bar.
    document.getElementById('numberOfDevices').innerText = cameras.length;

    // if the page of this camera doesn't exist quit. maybe later show error message
    if (currentCamera === undefined) {
        console.log("this camera doesn't exist");
        return;
    }

    // change all the properties of the page to this current camera
    document.title = currentCamera.name;
    document.getElementById('title').innerText = currentCamera.name;
    document.getElementById('livestreamPlaceholder').setAttribute('src', `images/${currentCamera.showCaseImage}`);
    document.getElementById('livestreamPlaceholder').setAttribute('alt', `${currentCamera.name} livestream`);
    document.getElementById('livestreamPlaceholder').classList.remove('loading');
    
    // fill the notifications:
    let notificationsHTML = "";
    for (const i in currentCamera.notifications){
        const noti = currentCamera.notifications[i]
        notificationsHTML += alertTemplate(noti, i);
    }

    // for security input the data as text:
    document.getElementById('notificationsContainer').innerHTML = notificationsHTML;
    for (const i in currentCamera.notifications){
        const data = currentCamera.notifications[i]

        document.getElementById(`cameraNotificationDate${i}`).innerText = data.date.split("T")[0];
        document.getElementById(`cameraNotificationTime${i}`).innerText = data.date.split("T")[1];
        document.getElementById(`cameraNotifiactionMsg${i}`).innerText = ` ${data.message}`;
    }
}

// set the total notification amount in the nav bar
function loadNotifications(noti){
    const notifications = noti.notifications;
    document.getElementById('numberOfAlerts').innerText = notifications.length;
}

// the html template of the notifications.
const alertTemplate = (data, i) => `
    <div class="cameraNotification">
        <div class="cameraNotificationInfo">
            <div class="cameraNotificationDate" id='cameraNotificationDate${i}'>
            </div>
            <div class="cameraNotificationTime" id='cameraNotificationTime${i}'>
            </div>
        </div>
        <div ${data.type === 'important' ? 'class="importantNoti"' : ''} id='cameraNotifiactionMsg${i}'>
           
        </div>
    </div>`