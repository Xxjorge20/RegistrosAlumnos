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


        listaAlumnos = ArrayList()
        getTodosAlumnos()


        bindingMain.BanadirAlumno.setOnClickListener {
           var nombreAlumno = bindingMain.TBNombre.text.toString()
           var apellidosAlumno = bindingMain.TBApellidos.text.toString()
           var curso = bindingMain.TBCurso.text.toString()

            var alumno = Alumno(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso)

            anadirAlumno(alumno)
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

    fun getTodosAlumnos(){
        CoroutineScope(Dispatchers.IO).launch {
            listaAlumnos = database.alumnoDAO().getAllAlumnos()
        }
    }



}