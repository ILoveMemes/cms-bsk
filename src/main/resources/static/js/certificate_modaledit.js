async function findById(controller, id) {
    return fetch('/' + controller + '/findById/' +id).then(response => response.json());
}
function showModalCertificate(certId) {
    let picture = document.getElementById('modalCertificatePicture');
    let _pictureId = -1;
    let pictureApplyFunction = function(id) {
        _pictureId = id;
        picture.src = '/p/' + _pictureId;
    };
    picture.onclick = () => {showPicPickDialog(pictureApplyFunction)};
    let dataApplyFunction = function(url, dataId) {
        // get data from modal window
        let _text = $('#modalCertificateText').val();

        let requestBody = {
            pictureId: _pictureId,
            text: _text
        };

        if (dataId) {
            requestBody.id = dataId;
        }

        fetch(url, {
            method: 'POST',
            body: JSON.stringify(requestBody),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => window.location.reload());
        $('#modalCertificate').modal('hide');
    };
    if (certId >= 0) {
        // edit exist certificate
        findById('certificates', certId)
        .then(result => {
            $('#modalCertificate').modal();
            // put data on modal window
            $('#modalCertificateText').val(result.text);
            _pictureId = result.pictureId;
            picture.src = '/p/' + result.pictureId;

            $('#modalCertificateApply').unbind();
            $('#modalCertificateApply').click(() => dataApplyFunction('/certificates/update', result.id));
        });
    } else {
        // add a new certificate
        $('#modalCertificate').modal();

        $('#modalCertificateText').val('');
        picture.src = './../images/blank_image.png';

        $('#modalCertificateApply').unbind();
        $('#modalCertificateApply').click(() => dataApplyFunction('/certificates/save'));
    }
}

function deleteCertificate(certId) {
    confirm('Вы действительно хотите удалить данный сертификат?',
        () => fetch('/certificates/deleteById/' + certId).then(response => window.location.reload()));
}
