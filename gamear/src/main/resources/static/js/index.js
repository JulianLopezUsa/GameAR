document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const correo = document.getElementById('correo').value;
    const contrasena = document.getElementById('contrasena').value;

    const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ correo, contrasena })
    });

    if (response.ok) {
        const token = await response.text();
        // Guardar el token en el almacenamiento local o en cookies si es necesario
        localStorage.setItem('jwt', token);

        // Redirigir a la nueva página
        window.location.href = '/menu';
    } else {
        // Manejar el error de autenticación
        alert('Correo o contraseña incorrectos');
    }
});