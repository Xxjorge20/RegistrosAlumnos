package ies.luiscarrillodesotomayor.registrosalumnos

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.Alumno
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP.Companion.database
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.DeleteActivityBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity: actividadConMenus() {

    // Declaramos las variables
    private lateinit var binding : DeleteActivityBinding
    private lateinit var listaAlumnos: MutableList<Alumno>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeleteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón eliminar alumno
        binding.BEliminar.setOnClickListener(){

            var nombreAlumno = binding.TBNombreEliminar.text.toString()


            // Validaciones
            if (nombreAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumno(nombre = nombreAlumno)

                    eliminarAlumno(alumno)
                    Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show()

                    // Limpiamos el campo de texto
                    limpiarCampos()
                    // Cerramos el teclado
                    cerrarTeclado()

            }
        }


    }
    fun eliminarAlumno(nombreAlumno: Alumno){
        CoroutineScope(Dispatchers.IO).launch {
            database.alumnoDAO().deleteAlumno(nombreAlumno.nombre)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        binding.TBNombreEliminar.text.clear()
    }

}
