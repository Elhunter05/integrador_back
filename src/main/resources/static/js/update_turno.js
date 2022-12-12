window.addEventListener('load', function () {
    const form = document.querySelector('#update_turno_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#turno_id').value,
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,
            fecha: document.querySelector('#fecha').value,
        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            window.location.replace("/get_turno.html");
        })
    })
 });

function findBy(id) {
    const url = '/turnos/buscar'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let turno = data;
        document.querySelector('#turno_id').value = id;
        document.querySelector('#pacienteId').value = turno.pacienteId;
        document.querySelector('#odontologoId').value = turno.odontologoId;
        document.querySelector('#fecha').value = turno.fecha;
        document.querySelector('#div_turno_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}