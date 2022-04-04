function confirmAction(text, yesBtnText, noBtnText, action) {
    $('#modalConfirmDialogText').html(text);
    $('#modalConfirmDialogNo').html(noBtnText);
    $('#modalConfirmDialogYes').html(yesBtnText);
    $('#modalConfirmDialogYes').unbind();
    $('#modalConfirmDialogYes').click(action);
    $('#modalConfirmDialog').modal();
}

function confirm(text, action) {
    confirmAction(text, 'Да', 'Нет', action);
}