function deleteBy(id){
    const url='/turnos/eliminar/'+id;
    const settings = {
        method:'DELETE'
    }
    fetch(url,settings)
    .then(response => {
        let row_id = "#tr_" + id;
        document.querySelector(row_id).remove();
    })
}