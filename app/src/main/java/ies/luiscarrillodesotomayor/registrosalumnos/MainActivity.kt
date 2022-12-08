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
        // obtenemos la lista de alumnos de la base de datos

        //              //
        //      ERROR   //
        //              //

        // getTodosAlumnos() Si descomento esto peta el programa

        // Evento click del botón añadir alumno
        bindingMain.BanadirAlumno.setOnClickListener {
            // Obtenemos los datos del alumno
           var nombreAlumno = bindingMain.TBNombre.text.toString()
           var apellidosAlumno = bindingMain.TBApellidos.text.toString()
           var curso = bindingMain.TBCurso.text.toString()

            // Supuestas validaciones de que no estén vacíos ?????


            // cargo los datos del alumno en un objeto de la clase Alumno
            var alumno = Alumno(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso)

            // añado el alumno a la base de datos
            anadirAlumno(alumno)
            // muestro un mensaje de que se ha añadido el alumno
            Toast.makeText(this, "Alumno añadido", Toast.LENGTH_SHORT).show()
        }


    }

    fun anadirAlumno(alumno: Alumno) {

        CoroutineScope(Dispatchers.IO).launch {

            val id = ListaAlumnosAPP.database.alumnoDAO().addAlumno(alumno)
            val recuperarAlumno = ListaAlumnosAPP.database.alumnoDAO().getAlumno(id)

            runOnUiThread{

                listaAlumnos.add(recuperarAlumno)

                    if (listaAlumnos.size >= 1) {
                        Toast.makeText(this@MainActivity, "Alumno añadido", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Alumnos añadidos", Toast.LENGTH_SHORT).show()
                    }
                    // Se oculta el teclado
                    hideKeyboard()
                    // Se limpia el foco
                    clearFocus()
            }
        }
    }


    //Al pulsar sobre el boton añadir, se limpia
    fun clearFocus(){
        bindingMain.TBNombre.setText("")
        bindingMain.TBApellidos.setText("")
        bindingMain.TBCurso.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(bindingMain.TBCurso.windowToken, 0)
    }

    // Obtenemos todos los alumnos de la base de datos
    fun getTodosAlumnos(){
        CoroutineScope(Dispatchers.IO).launch {
            listaAlumnos = database.alumnoDAO().getAllAlumnos()
        }
    }



}