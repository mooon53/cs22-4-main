fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

fetch('./javaScript/notificationFakeBackEnd.json')
    .then((response) => response.json())
    .then((json) => loadNotifications(json));

function loadPage(backEndData){
    let cameras = backEndData["cameras"];
    
    // get the current camera
    const urlParameters = getUrlVars();
    const currentCamera = cameras[urlParameters['id']];

    // set the number of devices in the nav bar.
    document.getElementById('numberOfDevices').innerText = cameras.length;

    // if the the page of this camera doesn't exist quit. maybe later show error message
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
        notificationsHTML += notificationTemplate(noti);
    }
    document.getElementById('notificationsContainer').innerHTML = notificationsHTML;
}

// set the total notification amount in the nav bar
function loadNotifications(noti){
    const notifications = noti.notifications;
    document.getElementById('numberOfAlerts').innerText = notifications.length;
}

// used to get the parameters from the url, for instance henkie.com/test.html?parameter=value.
function getUrlVars() {
    const vars = {};
    const parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

// the html template of the notifications.
const notificationTemplate = (data) => `
    <div class="notification">
        <div class="notificationInfo">
            <div class="notificationDate">
                ${data.date.split("T")[0]}
            </div>
            <div class="notificationTime">
                ${data.date.split("T")[1]}
            </div>
        </div>
        <div ${data.type === 'important' ? 'class="importantNoti"' : ''}>
            ${data.message}
        </div>
    </div>`