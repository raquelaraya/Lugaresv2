package com.example.lugaresv2.repository

import androidx.lifecycle.LiveData
import com.example.lugaresv2.data.LugarDao
import com.example.lugaresv2.model.Lugar

class LugarRepository (private val  lugarDao: LugarDao) {
    val getAllData: LiveData<List<Lugar>> = lugarDao.getAllData()

    //llamada asincrona
    suspend fun addLugar(lugar: Lugar){
        lugarDao.addLugar(lugar)
    }

    suspend fun updateLugar(lugar: Lugar){
        lugarDao.updateLugar(lugar)
    }

    suspend fun deleteLugar(lugar: Lugar){
        lugarDao.deleteLugar(lugar)
    }


}