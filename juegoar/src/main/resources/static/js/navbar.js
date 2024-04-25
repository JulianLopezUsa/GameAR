

        fetch('navbar.html')
            .then(response => response.text())
            .then(data => {
                // Insertamos el contenido en el contenedor navbarContainer
                document.getElementById('navbarContainer').innerHTML = data;
            })
            .catch(error => console.error('Error al cargar el archivo', error));
