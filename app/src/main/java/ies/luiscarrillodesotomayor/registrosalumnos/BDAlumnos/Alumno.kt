package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumnos")
class Alumno (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String = "",
    var apellidos: String = "",
    var curso: String = ""



)
