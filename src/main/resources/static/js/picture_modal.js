/////////////////////////////// picture upload dialog ////////////////////////////////////////////

function showPicUploadDialog() {
    $('#modalPicUploadDialog').modal();
}

function picDialogInit(applyFunction) {
    $('#modalPicUploadDialogApply').unbind();
    $('#modalPicUploadDialogApply').click(() => doUpload(applyFunction));
}

function doUpload(applyFunction) {
    let pic = document.getElementById('modalPicUploadInput').files[0];  // file from input
    let req = new XMLHttpRequest();
    let formData = new FormData();
    formData.append("image", pic);
    req.open("POST", '/pic/upload');
    req.onreadystatechange = () => {
        if (req.readyState == XMLHttpRequest.DONE && req.status == 200) {
            $('#modalPicUploadDialog').modal('hide');
            //picPickDialogRefresh();
            showPicPickDialog(applyFunction);
        }
    };
    req.send(formData);
}

function dismissUpload() {
    $('#modalPicUploadDialog').modal('hide');
    //showPicPickDialog(null);
}

//////////////////////////// picture select dialog ////////////////////////////////////

function  showPicPickDialog(applyFunction) {
    picDialogInit(applyFunction);
    picPickDialogRefresh(applyFunction);
    $('#modalPicPickDialog').modal();
}

function picPickDialogRefresh(applyFunction) {
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
                img.src = '/p/sm/' + i;
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
                        //element.src = '/p/' + i;
                        if (applyFunction != null) {
                            applyFunction(i);
                        }
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
    //$('#modalPicPickDialog').modal('hide');
    showPicUploadDialog();
}