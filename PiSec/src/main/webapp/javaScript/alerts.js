// dateTime: 1666793754726
// fromId: 0
// id: 0
// image: null
// message: ""
// type: "motion"
let otherFinished = false;
let notifications;
let cameras = {};

makeCameraRequest(loadCameras);
makeNotificationsRequest(loadNotifications);

const headers = ['dateTime', 'fromId', 'message', 'type'];
const dayOptions = { weekday: 'long', month: 'short', day: 'numeric' , hour: 'numeric', minute: 'numeric'};  // , second: 'numeric'};

// called when the notifications are loaded
function loadNotifications(notificationsIn){
    notifications = notificationsIn;
    // sorts the notifications by time.
    notifications.sort((a, b) => {
        const timeA = new Date(a.dateTime);
        const timeB = new Date(b.dateTime);
        return timeA.getTime() < timeB.getTime();
    });

    if (!otherFinished) {
        otherFinished = true;
    } else{
        loadTable(notifications, cameras);
    }
}

// called when the camera is loaded
function loadCameras(camerasIn){
    // sort cameras by id
    for (let i in camerasIn){
        const camera = camerasIn[i];
        cameras[camera.id] = camera;
    }

    if (!otherFinished) {
        otherFinished = true;
    } else{
        loadTable(notifications, cameras);
    }
}

// once both cameras and notifications are loaded this function is called. And it populates the table.
function loadTable(notifications, cameras){
    const tableBody = document.getElementById('alertsTbody');
    for (const noti in notifications){
        const notification = notifications[noti];
        const tableRow = document.createElement('tr');
        for (i in headers){
            const tableCell = document.createElement('td');
            const header = headers[i];

            switch (header){
                case 'dateTime':
                    const date = new Intl.DateTimeFormat('en-US', dayOptions).format(new Date(notification[header]));
                    tableCell.innerText = date.toString();
                    break;
                case 'fromId':
                    const cameraName = cameras[Number(notification[header])].name;
                    tableCell.innerText = cameraName;
                    break;
                default:
                    tableCell.innerText = notification[header];
                    break;
            }
            tableRow.appendChild(tableCell);
            tableRow.onclick = () => location.href=`alert.html?id=${notification.id}`;
        }
        tableBody.appendChild(tableRow);
    }
}