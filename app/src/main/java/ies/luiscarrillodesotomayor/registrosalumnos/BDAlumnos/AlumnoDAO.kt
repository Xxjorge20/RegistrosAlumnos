package ies.luiscarrillodesotomayor.registroalumno.BDAlumnos
import androidx.room.Dao
import androidx.room.Delete
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
   suspend fun getAlumno(nombre: String): Alumno

    // Devuelve todos los alumnos de la base de datos
    @Query
        ("SELECT * FROM alumnos")
   suspend fun getAllAlumnos(): MutableList<Alumno>


    // Elimina un alumno de la base de datos
    @Query
        ("DELETE FROM alumnos where nombre like :nombre")
    suspend fun deleteAlumno(nombre: String): Int

    // Actualiza el curso de un alumno de la base de datos
    @Query
        ("UPDATE alumnos SET curso = :curso WHERE nombre like :nombre")
    suspend fun updateAlumno(nombre: String, curso: String): Int


}