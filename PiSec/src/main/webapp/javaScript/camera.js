fetch('./javaScript/fakeBackEndData.json')
    .then((response) => response.json())
    .then((json) => loadPage(json));

function loadPage(backEndData){
    let cameras = backEndData["cameras"];
    const urlParameters = getUrlVars();

    const currentCamera = cameras[urlParameters['id']];
    console.log(currentCamera);
    console.log(cameras);
    console.log(urlParameters['id']);
    console.log(urlParameters);

    document.title = currentCamera.name;
}


function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
