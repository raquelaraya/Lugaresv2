package com.example.lugaresv2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lugaresv2.model.Lugar

@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class LugarDatabase: RoomDatabase() {
    abstract fun lugarDao() : LugarDao

    companion object{
        @Volatile
        private var INSTANCE: LugarDatabase? = null

        //construir la base de datos
        fun getDatabse(context: android.content.Context): LugarDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDatabase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}