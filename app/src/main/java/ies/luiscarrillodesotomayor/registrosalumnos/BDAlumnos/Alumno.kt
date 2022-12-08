package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos
import androidx.room.Entity
import androidx.room.PrimaryKey

// Nombre de la tabla
@Entity(tableName = "alumnos")
class Alumno (

    // Clave primaria
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String = "",
    var apellidos: String = "",
    var curso: String = ""



)
