using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class AsEquipos : MonoBehaviour
{
    public Text textoEquipo;
    public Image imagenEquipo;
    public bool esEquipoRojo;

    void Start()
    {
        // Verificar la asignación de equipo
        if (esEquipoRojo)
        {
            textoEquipo.text = "Eres del equipo rojo";
            // Asignar imagen del equipo rojo
            // imagenEquipo.sprite = /* Aquí asigna la imagen del equipo rojo */;
        }
        else
        {
            textoEquipo.text = "Eres del equipo azul";
            // Asignar imagen del equipo azul
            // imagenEquipo.sprite = /* Aquí asigna la imagen del equipo azul */;
        }
    }

    void Update()
    {
        // Puedes agregar lógica de actualización aquí si es necesario
    }
}
