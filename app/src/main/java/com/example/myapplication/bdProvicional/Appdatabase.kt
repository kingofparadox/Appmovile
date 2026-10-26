package com.example.myapplication.bdProvicional

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database (entities = [RolModel::class, UserModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserRepo

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "esucar_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL("INSERT INTO roles (id, nombre) VALUES ('1', 'Estudiante')")
            db.execSQL("INSERT INTO roles (id, nombre) VALUES ('2', 'Docente')")

            // Insertar usuario de prueba
            db.execSQL("INSERT INTO usuarios (rut, nombre, apellido, password, rolId) " +
                    "VALUES ('12.345.678-9', 'Jhonfran', 'Pardo', '1234', '1')")
            db.execSQL("INSERT INTO usuarios (rut, nombre, apellido, password, rolId) " +
                    "VALUES ('12.345.677-9', 'Pato', 'Fernandez', '1234', '2')")
        }

    }
}