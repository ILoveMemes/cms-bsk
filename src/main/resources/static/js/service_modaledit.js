
function showModalServiceUnit(unitId, categoryId) {
    if (unitId >= 0) {
        fetch('/service_units/findById/' + unitId)
        .then(response => response.json())
        .then(result => {
            $('#modalServiceUnit').modal();
            $('#modalServiceUnitName').val(result.name);
            $('#modalServiceUnitPrice').val(result.price);
            $('#modalServiceUnitShowOnMain').prop('checked', result.showOnMain);
            var myOpts = document.getElementById('modalServiceUnitCategory').options;
            for (let i = 0; i < myOpts.length; i++) {
                if (myOpts[i].id == categoryId) {
                    myOpts.selectedIndex = i;
                    break;
                }
            }
            $('#modalServiceUnitApply').unbind();
            $('#modalServiceUnitApply').click(() => {
                let _name = $('#modalServiceUnitName').val();
                let _price = $('#modalServiceUnitPrice').val();
                let _showOnMain = $('#modalServiceUnitShowOnMain').prop('checked');
                let _categoryId = myOpts[myOpts.selectedIndex].id;
                let requestBody = {id: result.id, name: _name, price: _price, showOnMain: _showOnMain,
                                    category: {id: _categoryId}};
                fetch('/service_units/update', {
                    method: 'POST',
                    body: JSON.stringify(requestBody),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => window.location.reload());
                $('#modalServiceUnit').modal('hide');
            });
        });
    } else {
        // add a new service unit
        $('#modalServiceUnit').modal();
        $('#modalServiceUnitName').val('');
        $('#modalServiceUnitPrice').val('');
        $('#modalServiceUnitShowOnMain').prop('checked', false);
        var myOpts = document.getElementById('modalServiceUnitCategory').options;
        for (let i = 0; i < myOpts.length; i++) {
            if (myOpts[i].id == categoryId) {
                myOpts.selectedIndex = i;
                break;
            }
        }
        $('#modalServiceUnitApply').unbind();
        $('#modalServiceUnitApply').click(() => {
            let _name = $('#modalServiceUnitName').val();
            let _price = $('#modalServiceUnitPrice').val();
            let _showOnMain = $('#modalServiceUnitShowOnMain').prop('checked');
            let _categoryId = myOpts[myOpts.selectedIndex].id;
            let requestBody = {name: _name, price: _price, showOnMain: _showOnMain,
                               category: {id: _categoryId}};
            fetch('/service_units/save', {
                method: 'POST',
                body: JSON.stringify(requestBody),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => window.location.reload());
                $('#modalServiceUnit').modal('hide');
            });
    }
}

function showModalServiceCategory(categoryId) {
    let picture = document.getElementById('modalServiceCategoryPicture');
    let _categoryPictureId = -1;
    let applyFunction = function(id) {
        _categoryPictureId = id;
        picture.src = '/p/' + _categoryPictureId;
    };
    picture.onclick = () => {showPicPickDialog(applyFunction)};
    if (categoryId >= 0) {
        fetch('/service_categories/findById/' + categoryId)
            .then(response => response.json())
            .then(result => {
                _categoryPictureId = result.pictureId;
                picture.src = '/p/' + _categoryPictureId;
                $('#modalServiceCategoryTitle').val(result.title);
                $('#modalServiceCategoryApply').click(() => {
                    let _title = $('#modalServiceCategoryTitle').val();
                    let requestBody = {id: result.id, title: _title, pictureId: _categoryPictureId};
                    fetch('/service_categories/update', {
                        method: 'POST',
                        body: JSON.stringify(requestBody),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => window.location.reload());
                    $('#modalServiceCategory').modal('hide');
                });
            });
        $('#modalServiceCategory').modal();
    } else {
        // add a new category
        picture.src = 'images/blank_image.png';
        $('#modalServiceCategoryTitle').val('');
        $('#modalServiceCategoryApply').click(() => {
            let _title = $('#modalServiceCategoryTitle').val();
            let requestBody = {title: _title, pictureId: _categoryPictureId};
            fetch('/service_categories/save', {
                method: 'POST',
                body: JSON.stringify(requestBody),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => window.location.reload());
            $('#modalServiceCategory').modal('hide');
        });
        $('#modalServiceCategory').modal();
    }
}

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
    //showPicPickDialog(null);
}

//////////////////////////// picture select dialog ////////////////////////////////////

function  showPicPickDialog(applyFunction) {
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