let commonValues = new Map();

function editableClickHandler(dataId) {
    $('#modalWindow').modal();
    $('#modalTextEdit').val(commonValues.get(dataId));
    $('#modalApply').unbind();
    $('#modalApply').click(() => modalApplyHandler(dataId));
}

function modalApplyHandler(dataId) {
    commonValues.set(dataId, $('#modalTextEdit').val());
    $('#modalWindow').modal('hide');
    updateDataOnServer(dataId);
    updateDoc();
}

function updateDataOnServer(dataId) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == XMLHttpRequest.DONE && xmlHttp.status == 200) {
            var resp = JSON.parse(xmlHttp.response);
            if (resp != null) {
                var updateRequest = new XMLHttpRequest();
                resp.value = commonValues.get(dataId);
                updateRequest.open("POST", '/val/update', true);
                updateRequest.setRequestHeader('Content-type', 'application/json');
                updateRequest.send(JSON.stringify(resp));
            }
        }
    }
    xmlHttp.open("GET", '/val/findByKey/' + dataId, true);
    xmlHttp.send(null);
}

function pickThElements() {
    let result = [];
    let elms = $('[class*="th_"');
    for (let i = 0; i < elms.length; i++) {
        let classes = elms[i].className.toString().split(" ");
        for (let j = 0; j < classes.length; j++) {
            if (classes[j].substring(0, 3) == "th_") {
                result.push({key: classes[j].slice(3), element: elms[i]});
            }
        }
    }
    return result;
}

function prepareDoc() {
    pickThElements().forEach(i => {
        i.element.onclick = () => editableClickHandler(i.key);
        i.element.classList.add('editable');
    });
}

function autoGrow(element) {
    element.style.height = "5px";
    element.style.height = (element.scrollHeight)+"px";
}

function updateDoc() {
    pickThElements().forEach(e => {
        var request = new XMLHttpRequest();
        request.open("POST", '/decorate', true);
        request.setRequestHeader('Content-type', 'application/json');
        request.send(commonValues.get(e.key));
        request.onreadystatechange = function() {
            if (request.response == null) {
                e.element.innerHTML = '(пусто)';
            } else {
                e.element.innerHTML = request.response;
            }
        };
    });
}

prepareDoc();

function showPicUploadDialog() {
    $('#modalPicUploadDialog').modal();
}

function picDialogInit() {
    $('#modalPicUploadDialogApply').unbind();
    $('#modalPicUploadDialogApply').click(doUpload);
}

function doUpload() {
    console.log('doUpload..');
    let pic = document.getElementById('modalPicUploadInput').files[0];  // file from input
    let req = new XMLHttpRequest();
    let formData = new FormData();
    formData.append("image", pic);
    req.open("POST", '/pic/upload');
    req.send(formData);

    $('#modalPicUploadDialog').modal('hide');
}
