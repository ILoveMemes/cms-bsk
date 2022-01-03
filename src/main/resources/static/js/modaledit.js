let commonValues = new Map();

function editableClickHandler(dataId) {
    $('#modalWindow').modal();
    $('#modalTextEdit').val(commonValues.get(dataId));
    $('#modalApply').click(() => modalApplyHandler(dataId));
}

function modalApplyHandler(dataId) {
    commonValues.set(dataId, $('#modalTextEdit').val());
    $('#modalWindow').modal('hide');
    updateDoc();
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
    pickThElements().forEach(e => e.element.innerHTML = commonValues.get(e.key));
}

prepareDoc();
