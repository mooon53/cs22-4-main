fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

function loadPage(backEndData){
    let cameras = backEndData["cameras"];
    const urlParameters = getUrlVars();

    const currentCamera = cameras[urlParameters['id']];

    if (currentCamera === undefined) {
        console.log("this camera doesn't exist");
        return;
    }

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


function getUrlVars() {
    const vars = {};
    const parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}


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