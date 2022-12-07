package ies.luiscarrillodesotomayor.registrosalumnos

import ies.luiscarrillodesotomayor.registrosalumnos.databinding.UpdateActivityBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus

class UpdateActivity :actividadConMenus() {

    private val binding = UpdateActivityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
