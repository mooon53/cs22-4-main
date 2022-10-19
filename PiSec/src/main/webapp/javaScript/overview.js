fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

fetch('./javaScript/notificationFakeBackEnd.json')
    .then((response) => response.json())
    .then((json) => loadNotifications(json));

function loadPage(backEndData){
    let cameras = backEndData["cameras"];
    
    // set the number of devices in the nav bar.
    document.getElementById('numberOfDevices').innerText = cameras.length;

    // loads the camera menu items to the document
    const camerasContainer = document.getElementById('cameraContainer');
    let camerasHtml = "";
    for (const cameraID in cameras){
        camerasHtml += cameraItemTemplate(cameras[cameraID]);
    }
    camerasContainer.innerHTML = camerasHtml;
}

// set the total notification amount in the nav bar
function loadNotifications(noti){
    const notifications = noti.notifications;
    document.getElementById('numberOfAlerts').innerText = notifications.length;
}

// the html template of the camera menu item
const cameraItemTemplate = (data) => `
  <div class="camera unselectable" style="background-image: url('./images/${data.showCaseImage}')" onclick="location.href='camera.html?id=${data.id}'">
    <div class="cameraOverlay">
        <div class="cameraInfo">
            <div class="cameraTextContainer">
                <div class="cameraTitle">${data.name}</div>
                <div class="status">
                    <div class="liveStatusIcon">
                        <svg>
                            <circle cx="8" cy="8" r="8" fill="${data.isLive ? '#5BB318' : '#FF0000'}"/>
                        </svg>
                    </div>
                    <div class="cameraLiveStatus">${data.isLive ? 'LIVE' : 'Off'}</div>
                </div>
            </div>
            <div class="notificationIcon">
                <svg>
                    <circle cx="27" cy="27" r="27" fill="#FF7F3F"></circle>
                    <text x="28" y="41" fill="#E8ECF1">${data.notifications.length}</text>
                </svg>
            </div>
        </div>
        <div class="cameraButtonsContainer">
            <div class="cameraButtons">
                <div class="cameraButton">
                    <img src="icons/${data.notifications.length > 0 ? 'bellWithNotification.svg' : 'bell.svg'}" alt="bell">
                </div>
                <div class="cameraButton">
                    <img src="icons/play.svg" alt="full screen">
                </div>
            </div>
        </div>
    </div>
</div>
`;
