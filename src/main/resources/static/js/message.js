function showMessages(condition) {
    if (!condition) {
        condition = msg => true;
    }
    let createMarks = function(r) {
        let m = document.createElement('img');
        //m.src = 'images/email.png';
        r.appendChild(m);
        return m;
    }
    let root = document.querySelector('#msgContainer');
    root.innerHTML = '';
    let rowCounter = 0;
    for (let i = 0; i < MESSAGES.length; i++) {
        let msg = MESSAGES[i];
        if (condition(msg)) {
            let row = createRowAndAttach(root);
            if (rowCounter % 2 == 0) {
                row.root.classList.add('even-row');
            } else {
                row.root.classList.add('odd-row');
            }
            rowCounter++;
            row.root.onclick = () => {location.href = '/admin/single_message?id=' + msg.id;};
            row.time.innerHTML = new Date(msg.sendingTime).format('dd.mm.yyyy HH:MM');
            row.name.innerHTML = msg.senderName;
            row.email.innerHTML = msg.senderEmail;
            let readMark = createMarks(row.marks);
            if (msg.unread) {
                readMark.src = './../images/mail-orange.png';
            } else {
                readMark.src = './../images/mail-gray.png';
            }
            let flagMark = createMarks(row.marks);
            if (msg.marked) {
                flagMark.src = './../images/flag-orange.png';
            } else {
                flagMark.src = './../images/flag-gray.png';
            }
        }
    }
}

function getUnreadMessageCount() {
    return MESSAGES.filter(msg => msg.unread).length;
}

function createRowAndAttach(root) {
    let createElement = function (subroot) {
        let element = document.createElement('div');
        //element.className = 'col-sm-12 col-md-6 col-lg-3';
        subroot.appendChild(element);
        return element;
    }
    let sroot = document.createElement('div');
    sroot.className = 'row msg-row';
    root.appendChild(sroot);
    let row = {
        root: sroot,
        marks: createElement(sroot),
        time: createElement(sroot),
        name: createElement(sroot),
        email: createElement(sroot)
    };
    row.marks.className = 'col-6 col-sm-6 col-md-2 col-lg-2';
    row.time.className = 'col-6 col-sm-6 col-md-2 col-lg-2';
    row.name.className = 'col-12 col-sm-12 col-md-4 col-lg-4';
    row.email.className = 'col-12 col-sm-12 col-md-4 col-lg-4';

    return row;
}

function actionMarkAsUnread(msgId) {
    switchVisibility(document.querySelector('#markAsUnread'), false);
    switchVisibility(document.querySelector('#markAsRead'), true);
    fetch('/asm/markAsUnread/' + msgId);
}

function actionMarkAsRead(msgId) {
    switchVisibility(document.querySelector('#markAsUnread'), true);
    switchVisibility(document.querySelector('#markAsRead'), false);
    fetch('/asm/markAsRead/' + msgId);
}

function actionMarkAsSpecial(msgId) {
    switchVisibility(document.querySelector('#markAsSpecial'), false);
    switchVisibility(document.querySelector('#markAsRegular'), true);
    fetch('/asm/markAsSpecial/' + msgId);
}

function actionMarkAsRegular(msgId) {
    switchVisibility(document.querySelector('#markAsSpecial'), true);
    switchVisibility(document.querySelector('#markAsRegular'), false);
    fetch('/asm/markAsRegular/' + msgId);
}

function actionMarkAsArchive(msgId) {
    switchVisibility(document.querySelector('#markAsArchive'), false);
    switchVisibility(document.querySelector('#markAsNotArchive'), true);
    fetch('/asm/markAsArchive/' + msgId);
}

function actionMarkAsNotArchive(msgId) {
    switchVisibility(document.querySelector('#markAsArchive'), true);
    switchVisibility(document.querySelector('#markAsNotArchive'), false);
    fetch('/asm/markAsNotArchive/' + msgId);
}

function deleteMessage(msgId) {
    fetch('/messages/deleteById/' + msgId);
    location.href = '/admin_messages';
}

function switchVisibility(element, visibility) {
    if (visibility) {
        element.classList.remove('invisible');
        element.classList.add('visible');
    } else {
        element.classList.remove('visible');
        element.classList.add('invisible');
    }
}