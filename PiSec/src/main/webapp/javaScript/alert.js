// update the alerts amount in the nav bar.
makeNotificationsRequest(null);

// get the current camera
const urlParameters = getUrlVars();
makeSingleNotificationRequest(Number(urlParameters['id']), loadSingleNotification);

const dayOptions = { weekday: 'long', month: 'short', day: 'numeric' , hour: 'numeric', minute: 'numeric'};  // , second: 'numeric'};

function loadSingleNotification(notification){
    // console.log(notification);

    // document.getElementById('alertTitle').innerText = notification
    document.getElementById('alertMsg').innerText = notification.message;
    document.getElementById('alertTime').innerText = new Intl.DateTimeFormat('en-US', dayOptions).format(new Date(notification.dateTime));
    document.getElementById('alertType').innerText = notification.type;
}