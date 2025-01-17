// update the alerts amount in the nav bar.
makeNotificationsRequest(null);

// get the current camera
const urlParameters = getUrlVars();
makeSingleNotificationRequest(Number(urlParameters['id']), loadSingleNotification);

const dayOptions = { weekday: 'long', month: 'short', day: 'numeric' , hour: 'numeric', minute: 'numeric'};  // , second: 'numeric'};

function loadSingleNotification(notification){
    const formatedDate =  new Intl.DateTimeFormat('en-US', dayOptions).format(new Date(notification.dateTime));

    if (notification.image !== '')
        document.getElementById('alertVideo').setAttribute('src', `data:video/mp4;base64, ${notification.image}`);
    else {
        fetch('./startVideo.txt')
            .then((response) => response.text())
            .then((txt) => document.getElementById('alertVideo').setAttribute('src', `data:video/mp4;base64, ${txt}`))
    }

    // document.getElementById('alertTitle').innerText = notification
    document.getElementById('alertMsg').innerText = `Intruder has been detected at ${formatedDate}, The detection has been triggered by ${notification.type}.`;
    document.getElementById('alertTime').innerText = formatedDate;
    document.getElementById('alertType').innerText = notification.type;
}