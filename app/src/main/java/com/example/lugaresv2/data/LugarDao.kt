package com.example.lugaresv2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lugaresv2.model.Lugar

@Dao
interface LugarDao {

    @Query("SELECT * FROM LUGAR")
    fun getAllData() : LiveData<List<Lugar>>
    //LiveData hace que se refresquen automaticamente los datos

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar(lugar: Lugar)
    //Si hay un conflico que no se haga el insert

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLugar(lugar: Lugar)

    @Delete
    suspend fun deleteLugar(lugar: Lugar)

}