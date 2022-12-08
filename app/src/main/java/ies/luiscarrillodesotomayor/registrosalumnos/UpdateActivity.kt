package ies.luiscarrillodesotomayor.registrosalumnos

import ies.luiscarrillodesotomayor.registrosalumnos.databinding.UpdateActivityBinding
import ies.luiscarrillodesotomayor.registrosalumnos.menuPrincipal.actividadConMenus

class UpdateActivity :actividadConMenus() {

    private lateinit var binding : UpdateActivityBinding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
