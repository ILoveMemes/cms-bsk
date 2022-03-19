async function findById(controller, id) {
    return fetch('/' + controller + '/findById/' +id).then(response => response.json());
}

function showModalTeammate(teammateId) {
    let picture = document.getElementById('modalTeammatePhoto');
    let _photoId = -1;
    let applyFunction = function(id) {
        _photoId = id;
        picture.src = '/p/' + _photoId;
    };
    picture.onclick = () => {showPicPickDialog(applyFunction)};
    if (teammateId >= 0) {
        fetch('/teammates/findById/' + teammateId)
            .then(response => response.json())
            .then(result => {
                _photoId = result.photoId;
                picture.src = '/p/' + _photoId;
                $('#modalTeammateName').val(result.name);
                $('#modalTeammatePosition').val(result.position);
                $('#modalTeammateApply').unbind();
                $('#modalTeammateApply').click(() => {
                    let _name = $('#modalTeammateName').val();
                    let _position = $('#modalTeammatePosition').val();
                    let requestBody = {id: result.id, name: _name, position: _position, photoId: _photoId};
                    fetch('/teammates/update', {
                        method: 'POST',
                        body: JSON.stringify(requestBody),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => window.location.reload());
                    $('#modalTeammate').modal('hide');
                });
            });
        $('#modalTeammate').modal();
    } else {
        // add a new teammate
        picture.src = 'images/blank_image.png';
        $('#modalTeammateName').val('');
        $('#modalTeammatePosition').val('');
        $('#modalTeammateApply').unbind();
        $('#modalTeammateApply').click(() => {
            let _name = $('#modalTeammateName').val();
            let _position = $('#modalTeammatePosition').val();
            let requestBody = {name: _name, position: _position, photoId: _photoId};
            fetch('/teammates/save', {
                method: 'POST',
                body: JSON.stringify(requestBody),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => window.location.reload());
            $('#modalTeammate').modal('hide');
        });
        $('#modalTeammate').modal();
    }
}

function showModalTeammateSN(tsnId, tId) {
    let picture = document.getElementById('modalTeammateSNPicture');
    let _pictureId = -1;
    let applyFunction = function(id) {
        _pictureId = id;
        picture.src = '/p/' + _pictureId;
    };
    picture.onclick = () => {showPicPickDialog(applyFunction)};
    if (tsnId >= 0) {
        fetch('/teammates_social_network/findById/' + tsnId)
            .then(response => response.json())
            .then(result => {
                _pictureId = result.pictureId;
                picture.src = '/p/' + _pictureId;
                $('#modalTeammateSNLink').val(result.link);
                $('#modalTeammateSNApply').unbind();
                $('#modalTeammateSNApply').click(() => {
                    let _link = $('#modalTeammateSNLink').val();
                    let requestBody = {id: result.id, link: _link, pictureId: _pictureId, teammate: {id: tId}};
                    fetch('/teammates_social_network/update', {
                        method: 'POST',
                        body: JSON.stringify(requestBody),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => window.location.reload());
                    $('#modalTeammateSN').modal('hide');
                });
            });
        $('#modalTeammateSN').modal();
    } else {
        picture.src = 'images/blank_image.png';
        $('#modalTeammateSNLink').val('');
        $('#modalTeammateSNApply').unbind();
        $('#modalTeammateSNApply').click(() => {
            let _link = $('#modalTeammateSNLink').val();
            let requestBody = {link: _link, pictureId: _pictureId, teammate: {id: tId}};
            fetch('/teammates_social_network/save', {
                method: 'POST',
                body: JSON.stringify(requestBody),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => window.location.reload());
            $('#modalTeammateSN').modal('hide');
        });
        $('#modalTeammateSN').modal();
    }
}

function deleteTeammateSN(tsnId) {
    fetch('/teammates_social_network/deleteById/' + tsnId).then(response => window.location.reload());
}

function deleteTeammate(tId) {
    fetch('/teammates/deleteById/' + tId).then(response => window.location.reload());
}