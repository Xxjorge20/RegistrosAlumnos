package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos

import android.app.Application
import androidx.room.Room
import ies.luiscarrillodesotomayor.registroalumnosv1.BDAlumnos.BDAlumnos

class ListaAlumnosAPP: Application() {

    companion object {
         lateinit var database: BDAlumnos
    }

    override fun onCreate() {
        super.onCreate()
        ListaAlumnosAPP.database = Room.databaseBuilder(
            this,
            BDAlumnos::class.java,
            "DBAlumnos"
        ).build()
    }

}
