package ies.luiscarrillodesotomayor.registrosalumnos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.Alumno
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.ActivityMainBinding
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.ActivityMainBinding.inflate
import ies.luiscarrillodesotomayor.registrosalumnos.databinding.DeleteActivityBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity: actividadConMenus() {

    private lateinit var binding : DeleteActivityBinding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeleteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
