// get the current camera
const urlParameters = getUrlVars();
const id = Number(urlParameters['id']);

fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

makeNotificationsRequest(loadNotifications);
const dayOptions = { weekday: 'long', month: 'short', day: 'numeric'}
const timeoption =  {hour: 'numeric', minute: 'numeric'};  // , second: 'numeric'};

function loadPage(backEndData){
    let cameras = backEndData["cameras"];

    const currentCamera = cameras[id];

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
}

// set the total notification amount in the nav bar
function loadNotifications(notifications){
    // fill the notifications:
    let notificationsHTML = "";
    for (const i in notifications){
        const noti = notifications[i];
        if (noti.fromId !== id) continue;

        notificationsHTML += alertTemplate(noti, i);
    }

    // for security input the data as text:
    document.getElementById('notificationsContainer').innerHTML = notificationsHTML;
    for (const i in notifications){
        const data = notifications[i]
        if (data.fromId !== id) continue;

        const date = new Date(data.dateTime);

        document.getElementById(`cameraNotificationDate${i}`).innerText = new Intl.DateTimeFormat('en-US', dayOptions).format(date);
        document.getElementById(`cameraNotificationTime${i}`).innerText = new Intl.DateTimeFormat('en-US', timeoption).format(date);
        document.getElementById(`cameraNotifiactionMsg${i}`).innerText = `Motion detected`;
    }
}

// the html template of the notifications.
const alertTemplate = (data, i) => `
    <div class="cameraNotification">
        <div ${data.type === 'important' ? 'class="importantNoti"' : ''} id='cameraNotifiactionMsg${i}'>
        </div>
        <div class="cameraNotificationInfo">
            <div class="cameraNotificationDate" id='cameraNotificationDate${i}'>
            </div>
            <div class="cameraNotificationTime" id='cameraNotificationTime${i}'>
            </div>
        </div>
    </div>`