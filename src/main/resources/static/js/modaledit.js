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
        if (i.element.tagName == 'IMG') {
            i.element.onclick = () => showPicPickDialog(i.key, i.element);
        } else {
            i.element.onclick = () => editableClickHandler(i.key);
        }
        i.element.classList.add('editable');
    });
}

function autoGrow(element) {
    element.style.height = "5px";
    element.style.height = (element.scrollHeight)+"px";
}

function updateDoc() {
    pickThElements().forEach(e => {
        if (e.element.tagName == 'IMG') {
            e.element.src = '/p/' + commonValues.get(e.key);
        } else {
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
        }
    });
}

prepareDoc();

/////////////////////////////// picture upload dialog ////////////////////////////////////////////

function showPicUploadDialog() {
    $('#modalPicUploadDialog').modal();
}

function picDialogInit() {
    $('#modalPicUploadDialogApply').unbind();
    $('#modalPicUploadDialogApply').click(doUpload);
}

function doUpload() {
    let pic = document.getElementById('modalPicUploadInput').files[0];  // file from input
    let req = new XMLHttpRequest();
    let formData = new FormData();
    formData.append("image", pic);
    req.open("POST", '/pic/upload');
    req.onreadystatechange = () => {
        if (req.readyState == XMLHttpRequest.DONE && req.status == 200) {
            $('#modalPicUploadDialog').modal('hide');
            //picPickDialogRefresh();
            showPicPickDialog();
        }
    };
    req.send(formData);
}

function dismissUpload() {
    $('#modalPicUploadDialog').modal('hide');
    showPicPickDialog();
}

//////////////////////////// picture select dialog ////////////////////////////////////

function  showPicPickDialog(dataId, element) {
    picPickDialogRefresh(dataId, element);
    $('#modalPicPickDialog').modal();
}

function picPickDialogRefresh(dataId, element) {
    var selectedPicture = {id: -1, element: null};
    $('#modalPickDeleteSelected').attr('disabled', true);
    var list = document.getElementById('modalPicPickList');
    list.innerHTML = '';
    var request = new XMLHttpRequest();
    request.open("GET", '/pic/idList', true);
    request.onreadystatechange = function() {
        if (request.readyState == XMLHttpRequest.DONE && request.status == 200 && request.response != null) {
            let data = JSON.parse(request.response);
            data.forEach(i => {
                let div = document.createElement('div');
                div.classList.add('modal-list-item');
                list.appendChild(div);
                let img = document.createElement('img');
                img.src = '/p/' + i;
                img.classList.add('modal-list-picture');
                div.appendChild(img);
                div.onclick = () => {
                    //// click by item
                    if (selectedPicture.element != null) {
                        selectedPicture.element.classList.remove('modal-list-item-selected');
                    }
                    selectedPicture.id = i;
                    selectedPicture.element = div;
                    div.classList.add('modal-list-item-selected');
                    $('#modalPicPickDialogApply').unbind();
                    $('#modalPicPickDialogApply').click(() => {
                        ////// apply selection
                        $('#modalPicPickDialog').modal('hide');
                        //console.log('now selected: ' + i);
                        commonValues.set(dataId, i);
                        element.src = '/p/' + i;
                        updateDataOnServer(dataId);
                    });
                    $('#modalPickDeleteSelected').attr('disabled', false);
                    $('#modalPickDeleteSelected').click(() => {
                        ///// delete picture
                        let deleteRequest = new XMLHttpRequest();
                        deleteRequest.open("GET", '/pic/deleteById/' + i, true);
                        deleteRequest.onreadystatechange = () => {
                            if (deleteRequest.readyState == XMLHttpRequest.DONE && deleteRequest.status == 200) {
                                picPickDialogRefresh();
                            }
                        };
                        deleteRequest.send(null);
                    });
                };
            });
        }
    };
    request.send(null);
}

function doLoadNewPicture() {
    $('#modalPicPickDialog').modal('hide');
    showPicUploadDialog();
}
