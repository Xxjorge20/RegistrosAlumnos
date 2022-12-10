package ies.luiscarrillodesotomayor.registrosalumnos

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.Alumno
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP.Companion.database
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.ActivityMainBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : actividadConMenus()
{

    // Declaramos el binding
    private lateinit var bindingMain : ActivityMainBinding

    // Declaramos la lista de alumnos mutable
    lateinit var listaAlumnos: MutableList<Alumno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflamos el layout
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()


        // Evento click del botón añadir alumno
        bindingMain.BanadirAlumno.setOnClickListener {
            // Obtenemos los datos del alumno
           var nombreAlumno = bindingMain.TBNombre.text.toString()
           var apellidosAlumno = bindingMain.TBApellidos.text.toString()
           var curso = bindingMain.TBCurso.text.toString()

            // Validaciones

            if (nombreAlumno.isEmpty() || apellidosAlumno.isEmpty() || curso.isEmpty()) {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            } else {

                // Creamos el alumno
                var alumno = Alumno(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso)

                // Añadimos el alumno a la lista
                listaAlumnos.add(alumno)
                // Añadimos el alumno a la base de datos
                anadirAlumno(alumno)
                // muestro un mensaje de que se ha añadido el alumno
                Toast.makeText(this, "Alumno añadido", Toast.LENGTH_SHORT).show()

                // Limpiamos los campos
                limpiarCampos()

                // Cerramos el teclado
                cerrarTeclado()

            }

        }

    }

    fun anadirAlumno(alumno: Alumno) {

        CoroutineScope(Dispatchers.IO).launch {
            database.alumnoDAO().addAlumno(alumno)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindingMain.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        bindingMain.TBNombre.text.clear()
        bindingMain.TBApellidos.text.clear()
        bindingMain.TBCurso.text.clear()
    }


}