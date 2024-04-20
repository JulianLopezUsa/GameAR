using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using SimpleJSON;
using System.Collections;
using System.Security.Cryptography;
using System.Text;

public class Login : MonoBehaviour
{   private const string llaveS = "UG9ycXVlIG1pIG1lbG9kw61hIGZhdm9yaXRhIGVzIHR1IHZveiBZIHNpbiBkYXJubycgY3VlbnRhLCAndGFtbycgZW4gZWwgbWlzbW8gdG9ubw==";
    public InputField nombreInput;
    public InputField apellidoInput;
    public InputField documentoInput;
    public InputField correoInput;
    public InputField escuelaInput;
    public InputField celularInput;
    public InputField contrasenaInput;
    public GameObject Loginm;
    public GameObject Registre;

    public Text mensajeText;

    public string url = "http://172.26.2.166:8080/api/usuarios";
    public int idRol = 1;

    public void EnviarDatos()
    {
        string nombre = nombreInput.text;
        string apellido = apellidoInput.text;
        string documento = documentoInput.text;
        string correo = correoInput.text;
        string escuela = escuelaInput.text;
        string contrasena = contrasenaInput.text;
        string celular = celularInput.text;
        

        if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(apellido) || string.IsNullOrEmpty(documento) || string.IsNullOrEmpty(correo) ||
            string.IsNullOrEmpty(escuela) || string.IsNullOrEmpty(celular) || string.IsNullOrEmpty(contrasena))
        {
            mensajeText.text = "Todos los campos son obligatorios.";
            return;
        }
 
        string contrasenaHash = HashContrasena(contrasena);
        string contrasenaHash2 = HashContrasena(contrasenaHash+llaveS);
       
        JSONNode json = new JSONObject();
        json["nombre"] = nombre;
        json["apellido"] = apellido;
        json["documento"] = documento;
        json["correo"] = correo;
        json["escuela"] = escuela;
        json["rol"] = idRol;
        json["numeroCelular"] = celular;
        json["contrasena"] = contrasenaHash2;

        StartCoroutine(EnviarInformacion(json));
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

    private IEnumerator EnviarInformacion(JSONNode json)
    {
        byte[] jsonToSend = System.Text.Encoding.UTF8.GetBytes(json.ToString());

        UnityWebRequest request = new UnityWebRequest(url, "POST");
        request.uploadHandler = new UploadHandlerRaw(jsonToSend);
        request.downloadHandler = new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");

        yield return request.SendWebRequest();

        if (request.result == UnityWebRequest.Result.Success)
        {
            Loginm.SetActive(true);
            Registre.SetActive(false);
            mensajeText.text = "Usuario registrado con éxito.";
        }
        else
        {
            if (request.responseCode == 400)
            {
                mensajeText.text = "El correo electrónico ya está registrado.";
            }
            else
            {
                mensajeText.text = "Ocurrió un error al registrar el usuario.";
            }
            Registre.SetActive(true);
        }
    }
}
