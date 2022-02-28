
async function findById(controller, id) {
    return fetch('/' + controller + '/findById/' +id).then(response => response.json());
}