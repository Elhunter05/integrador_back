window.addEventListener('load', function () {
    const form = document.querySelector('#update_paciente_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            email: document.querySelector('#email').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {calle: document.querySelector('#calle').value,
                                    numero: document.querySelector('#numero').value,
                                    localidad: document.querySelector('#localidad').value,
                                    provincia: document.querySelector('#provincia').value},
        };
        const url = '/pacientes';
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
            window.location.replace("/get_paciente.html");
        })
    })
 });

function findBy(id) {
    const url = '/pacientes'+"/buscar/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let paciente = data;
        document.querySelector('#paciente_id').value=id;
        document.querySelector('#nombre').value = paciente.nombre;
        document.querySelector('#apellido').value = paciente.apellido;
        document.querySelector('#dni').value = paciente.dni;
        document.querySelector('#email').value=paciente.email;
        document.querySelector('#fechaIngreso').value=paciente.fechaIngreso;
        document.querySelector('#calle').value = paciente.domicilio.calle;
        document.querySelector('#numero').value = paciente.domicilio.numero;
        document.querySelector('#localidad').value = paciente.domicilio.localidad;
        document.querySelector('#provincia').value = paciente.domicilio.provincia;
        document.querySelector('#div_paciente_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}