var siteSectionChanges = new Map();
var visibleButton = false;

function addChanges(key, propKey, propValue) {
    let obj = {};
    if (siteSectionChanges.has(key)) {
        obj = siteSectionChanges.get(key);
    }
    obj[propKey] = propValue;
    siteSectionChanges.set(key, obj);
    if (!visibleButton) {
        btn = document.querySelector('#saveChanges');
        if (btn) {
            btn.classList.remove('invisible');
            btn.classList.add('visible');
            visible = true;
        }
    }
}

function saveChanges(btn) {
    siteSectionChanges.forEach((value, key) => {
        fetch('/site_sections/findByKey/' + key)
            .then(response => response.json())
            .then(response => {
                console.log('from server: ');
                console.log(response);
                console.log('change value: ');
                console.log(value);
                if (value.visible != undefined) {
                    response.visible = value.visible;
                }
                if (value.caption != undefined) {
                    response.caption = value.caption;
                }
                console.log('to server: ');
                console.log(response);
                fetch('/site_sections/update', {
                    method: 'POST',
                    body: JSON.stringify(response),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            });
    });
    btn = document.querySelector('#saveChanges');
    if (btn) {
        btn.classList.remove('visible');
        btn.classList.add('invisible');
        visible = false;
    }
}

/*function switchVisible(sectionId, element) {
    updateDataOnServer('site_section_' + sectionId + '_visible', element.checked);
}

function updateDataOnServer(dataId, value) {
    fetch('/val/findByKey/' + dataId)
        .then(response => {
            if (response.status === 404) {
                // there is no such data on db, so make a new one
                let req = {
                    description: '',
                    key: dataId,
                    value: value
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
                    r.value = value;
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
}*/