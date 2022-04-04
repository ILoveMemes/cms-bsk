async function findById(controller, id) {
    return fetch('/' + controller + '/findById/' +id).then(response => response.json());
}

function showModalGoods(goodsId) {
    let picture = document.getElementById('modalGoodsPicture');
    let _pictureId = -1;
    let pictureApplyFunction = function(id) {
        _pictureId = id;
        picture.src = '/p/' + _pictureId;
    };
    picture.onclick = () => {showPicPickDialog(pictureApplyFunction)};
    let dataApplyFunction = function(url, dataId) {
        // get data from modal window
        let _title = $('#modalGoodsTitle').val();
        let _price = $('#modalGoodsPrice').val();
        let _showOnMain = $('#modalGoodsShowOnMain').prop('checked');
        let _shortDescription = $('#modalGoodsShortDescription').val();
        let _fullDescription = $('#modalGoodsFullDescription').val();

        let requestBody = {
            imageId: _pictureId,
            title: _title,
            price: _price,
            shortDescription: _shortDescription,
            fullDescription: _fullDescription,
            showOnMain: _showOnMain
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
        $('#modalGoods').modal('hide');
    };
    if (goodsId >= 0) {
        // edit exist goods
        findById('goods', goodsId)
        .then(result => {
            $('#modalGoods').modal();
            // put data on modal window
            $('#modalGoodsTitle').val(result.title);
            $('#modalGoodsPrice').val(result.price);
            _pictureId = result.imageId;
            picture.src = '/p/' + result.imageId;
            $('#modalGoodsShowOnMain').prop('checked', result.showOnMain);
            $('#modalGoodsShortDescription').val(result.shortDescription);
            $('#modalGoodsFullDescription').val(result.fullDescription);

            $('#modalGoodsApply').unbind();
            $('#modalGoodsApply').click(() => dataApplyFunction('/goods/update', result.id));
        });
    } else {
        // add a new goods
        $('#modalGoods').modal();

        $('#modalGoodsTitle').val('');
        $('#modalGoodsPrice').val('');
        picture.src = './../images/blank_image.png';
        $('#modalGoodsShowOnMain').prop('checked', true);
        $('#modalGoodsShortDescription').val('');
        $('#modalGoodsFullDescription').val('');

        $('#modalGoodsApply').unbind();
        $('#modalGoodsApply').click(() => dataApplyFunction('/goods/save'));
    }
}

function deleteGoods(goodsId) {
    confirm('Вы действительно хотите удалить данный товар?',
        () => fetch('/goods/deleteById/' + goodsId).then(response => window.location.reload()));
}