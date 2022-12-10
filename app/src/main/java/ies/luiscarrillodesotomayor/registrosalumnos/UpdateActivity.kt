package ies.luiscarrillodesotomayor.registrosalumnos

import android.widget.Toast
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.Alumno
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP.Companion.database
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.UpdateActivityBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity :actividadConMenus() {

    private lateinit var binding : UpdateActivityBinding
    private lateinit var listaAlumnos: MutableList<Alumno>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón actualizar alumno
        binding.Bactualizar.setOnClickListener(){

            var nombreAlumno = binding.TBNombreActualizar.text.toString()
            var cursoAlumno = binding.TBCursoActualizar.text.toString()

            // Validaciones
            if (nombreAlumno.isEmpty() || cursoAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumno(nombre = nombreAlumno, curso = cursoAlumno)

                actualizarAlumno(alumno)
                Toast.makeText(this, "Alumno actualizado", Toast.LENGTH_SHORT).show()

                // Limpiamos el campo de texto
                limpiarCampos()
                // Cerramos el teclado
                cerrarTeclado()

            }
        }

    }

    fun actualizarAlumno(nombreAlumno: Alumno){
        CoroutineScope(Dispatchers.IO).launch {
            database.alumnoDAO().updateAlumno(nombreAlumno.nombre, nombreAlumno.curso)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // Funcion para limpiar los campos de texto
    fun limpiarCampos(){
        binding.TBNombreActualizar.text.clear()
        binding.TBCursoActualizar.text.clear()
    }
}
