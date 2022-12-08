package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos

import android.app.Application
import androidx.room.Room
import ies.luiscarrillodesotomayor.registroalumnosv1.BDAlumnos.BDAlumnos

class ListaAlumnosAPP: Application() {

    // Creamos la base de datos
    companion object {
         lateinit var database: BDAlumnos
    }

    // Inicializamos la base de datos
    override fun onCreate() {
        super.onCreate()
        ListaAlumnosAPP.database = Room.databaseBuilder(
            this,
            BDAlumnos::class.java,
            "DBAlumnos"
        ).build()
    }

}
