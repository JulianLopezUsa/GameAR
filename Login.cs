using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using SimpleJSON;
using System.Collections;

public class Login : MonoBehaviour
{
    public InputField nombreInput;
    public InputField apellidoInput;
    public InputField correoInput;
    public InputField escuelaInput;
    public InputField celularInput;
    public InputField contraseñaInput;

    public GameObject RegExi;
    public GameObject RegFallo;
    public GameObject Registre;

    public Text mensajeText;

    public string url = "http://localhost:8080/api/usuarios"; // URL del endpoint de tu backend Spring Boot

    public void EnviarDatos()
    {
        string nombre = nombreInput.text;
        string apellido = apellidoInput.text;
        string correo = correoInput.text;
        string escuela = escuelaInput.text;

        // Verificar si la entrada de celular es un número entero válido
        int celular;
        if (int.TryParse(celularInput.text, out celular))
        {
            // La conversión fue exitosa, proceder con el envío de datos
            StartCoroutine(EnviarInformacion(nombre, apellido, correo, escuela, celular, contraseñaInput.text));
        }
    }
    private IEnumerator EnviarInformacion(string nombre, string apellido, string correo, string escuela, int celular, string contraseña)
    {
        // Crear un objeto JSON con los datos a enviar usando SimpleJSON
        JSONNode json = new JSONObject();
        json["nombre"] = nombre;
        json["apellido"] = apellido;
        json["correo"] = correo;
        json["escuela"] = escuela;
        json["rol"] = 1; // Asignar rol por defecto
        json["numeroCelular"] = celular;
        json["contraseña"] = contraseña;

        // Crear una solicitud POST
        UnityWebRequest request = UnityWebRequest.PostWwwForm(url, json.ToString());
        request.SetRequestHeader("Content-Type", "application/json");

        // Enviar la solicitud y esperar la respuesta
        yield return request.SendWebRequest();

        // Manejar la respuesta
        if (request.result == UnityWebRequest.Result.Success)
        {
            mensajeText.text = "Datos enviados correctamente";
            RegExi.SetActive(true); // Mostrar el panel de éxito
            Registre.SetActive(false); // Ocultar el panel de fallo
        }
        else
        {
            mensajeText.text = "Error al enviar datos: " + request.error;
            Registre.SetActive(false); // Ocultar el panel de éxito
            RegFallo.SetActive(true); // Mostrar el panel de fallo
        }
    }
}
