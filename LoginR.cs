using System.Collections;
using SimpleJSON;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using System.Security.Cryptography;
using System.Text;

public class LoginR : MonoBehaviour
{
    private const string llaveS = "UG9ycXVlIG1pIG1lbG9kw61hIGZhdm9yaXRhIGVzIHR1IHZveiBZIHNpbiBkYXJubycgY3VlbnRhLCAndGFtbycgZW4gZWwgbWlzbW8gdG9ubw==";
    public InputField correoInput;
    public InputField contrasenaInput;
    public GameObject Roles;
    public GameObject RolAdmin;
    public GameObject Jugador;
    public GameObject Loginm;
    public GameObject USA;
    public Text mensajeText;
    public string url = "http://172.26.3.33:8080/api/login";

    public void IniciarSesion()
    {
        string correo = correoInput.text;
        string contrasena = contrasenaInput.text;

        // Hashear la contraseña antes de enviarla
        string contrasenaHash = HashContrasena(contrasena);
        string contrasenaHash2 = HashContrasena(contrasenaHash + llaveS);

        StartCoroutine(EnviarCredenciales(correo, contrasenaHash2));
    }

    private string HashContrasena(string contrasena)
    {
        using (SHA256 sha256Hash = SHA256.Create())
        {
            byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(contrasena));
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bytes.Length; i++)
            {
                builder.Append(bytes[i].ToString("x2"));
            }
            return builder.ToString();
        }
    }

    private IEnumerator EnviarCredenciales(string correo, string contrasena)
    {
        // Crear un objeto JSON con las credenciales
        JSONNode json = new JSONObject();
        json["correo"] = correo;
        json["contrasena"] = contrasena;

        // Crear una solicitud POST
        UnityWebRequest request = new UnityWebRequest(url, "POST");
        byte[] jsonToSend = System.Text.Encoding.UTF8.GetBytes(json.ToString());
        request.uploadHandler = new UploadHandlerRaw(jsonToSend);
        request.downloadHandler = new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");

        // Enviar la solicitud y esperar la respuesta
        yield return request.SendWebRequest();


        // Manejar la respuesta
        if (request.result == UnityWebRequest.Result.Success)
        {  
            JSONNode responseJson = JSON.Parse(request.downloadHandler.text);
           int numeroRol = responseJson["rol"].AsInt;

                string nombreRol = ObtenerNombreRol(numeroRol); // Obtener el nombre del rol legible
                string nombre = responseJson["nombre"];
                Debug.Log("Login exitoso. Rol: " + nombreRol + ", Nombre: " + nombre);
                
        if (nombreRol =="Admin")
        {
            
            Loginm.SetActive(false);
            USA.SetActive(false); 
            Roles.SetActive(true);
            RolAdmin.SetActive(true); 
        }else{

            Loginm.SetActive(false);
            USA.SetActive(false); 
            Roles.SetActive(true);
            Jugador.SetActive(true); 


        }
        }
        else
        { 
            Loginm.SetActive(true); // Ocultar el panel de éxito
            mensajeText.text = "Correo o contraseña incorrectos";
        }
    }
     public string ObtenerNombreRol(int numeroRol)
    {
        switch (numeroRol)
        {
            case 1:
                return "Jugador";
            case 2:
                return "Admin";
            default:
                return "Rol Desconocido";
        }
    }
}
