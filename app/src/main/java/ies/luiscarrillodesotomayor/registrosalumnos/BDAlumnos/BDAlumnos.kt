package ies.luiscarrillodesotomayor.registroalumnosv1.BDAlumnos

import androidx.room.Database
import androidx.room.RoomDatabase
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.Alumno
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.AlumnoDAO
import ies.luiscarrillodesotomayor.registroalumno.BDAlumnos.ListaAlumnosAPP

// Base de datos
@Database(
    entities = [Alumno::class],
    version = 1
)

abstract class BDAlumnos: RoomDatabase() {
    abstract fun alumnoDAO(): AlumnoDAO
}
