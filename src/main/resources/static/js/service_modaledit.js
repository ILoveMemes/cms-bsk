
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