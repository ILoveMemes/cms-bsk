async function findById(controller, id) {
    return fetch('/' + controller + '/findById/' +id).then(response => response.json());
}

function showModalServiceUnit(unitId, categoryId) {
    if (unitId >= 0) {
        //fetch('/service_units/findById/' + unitId)
        //.then(response => response.json())
        findById('service_units', unitId)
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
        picture.src = './../images/blank_image.png';
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

function deleteServiceUnit(suId) {
    fetch('/service_units/deleteById/' + suId).then(response => window.location.reload());
}

function deleteServiceCategory(scId) {
    fetch('/service_categories/deleteById/' + scId).then(response => window.location.reload());
}