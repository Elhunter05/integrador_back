window.addEventListener('load', function () {
    const form = document.querySelector('#update_odontologo_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value
            };
        const url = '/odontologos';
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
            window.location.replace("/get_odontologo.html");
        })
    })
 });

function findBy(id) {
    const url = '/odontologos'+"/buscar?id="+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        document.querySelector('#odontologo_id').value = id;
        document.querySelector('#matricula').value = odontologo.matricula;
        document.querySelector('#nombre').value = odontologo.nombre;
        document.querySelector('#apellido').value = odontologo.apellido;
        document.querySelector('#div_odontologo_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}