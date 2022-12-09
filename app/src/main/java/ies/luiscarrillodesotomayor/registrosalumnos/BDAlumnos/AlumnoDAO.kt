package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlumnoDAO {

    // Inserta un alumno en la base de datos
    @Insert
   suspend fun addAlumno(alumno: Alumno): Long

    // Devuelve un alumno de la base de datos
    @Query
        ("SELECT * FROM alumnos where nombre like :nombre")
   suspend fun getAlumno(nombre: Long): Alumno

    // Devuelve todos los alumnos de la base de datos
    @Query
        ("SELECT * FROM alumnos")
   suspend fun getAllAlumnos(): MutableList<Alumno>
}