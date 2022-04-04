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
    fetch('/val/findByKey/' + dataId)
        .then(response => {
            if (response.status === 404) {
                // there is no such data on db, so make a new one
                let req = {
                    description: '',
                    key: dataId,
                    value: commonValues.get(dataId)
                };
                fetch('/val/save', {
                    method: 'POST',
                    body: JSON.stringify(req),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            }
            if (response.status === 200) {
                // if this key is exist - just update data on db
                response.json().then(r => {
                    r.value = commonValues.get(dataId);
                    fetch('/val/update', {
                        method: 'POST',
                        body: JSON.stringify(r),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                });
            }
        });
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
        let applyFunction = function(id) {
            commonValues.set(i.key, id);
            i.element.src = '/p/' + id;
            updateDataOnServer(i.key);
        };
        if (i.element.tagName == 'IMG') {
            i.element.onclick = () => showPicPickDialog(applyFunction);
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
