using UnityEngine;
using UnityEngine.Networking;
using System.Collections.Generic;
using SimpleJSON;
using System.Collections;

public class Escuelas : MonoBehaviour
{
    public string backendURL = "http://localhost:8080/api/escuelas"; // URL de tu endpoint en Spring Boot

    IEnumerator Start()
    {
        UnityWebRequest www = UnityWebRequest.Get(backendURL);
        yield return www.SendWebRequest();

        if (www.result != UnityWebRequest.Result.Success)
        {
            Debug.LogError("Error al obtener datos: " + www.error);
        }
        else
        {
            string jsonString = www.downloadHandler.text;
            List<EscuelaData> Escuelas = DeserializeEscuelas(jsonString);

            // Ahora puedes trabajar con la lista de escuelas recibidas
            foreach (EscuelaData escuela in Escuelas)
            {
                Debug.Log("Nombre de la escuela: " + escuela.Escuela);
            }
        }
    }

    private List<EscuelaData> DeserializeEscuelas(string jsonString)
    {
        List<EscuelaData> escuelas = new List<EscuelaData>();
        JSONNode jsonNode = JSON.Parse(jsonString);

        foreach (JSONNode escuelaJSON in jsonNode.AsArray)
        {
            EscuelaData escuela = new EscuelaData();
            escuela.Escuela = escuelaJSON["Escuela"];
            escuelas.Add(escuela);
        }

        return escuelas;
    }

   [System.Serializable]
public class EscuelaData
{
    public string Escuela; 
}

}
