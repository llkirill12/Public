package com.example.sqlitekotlin_lesson17.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper // интерфейс который дает доступ к базе данных

// при запуске этого класса создастся база данных с указанными параметрами
class MyDbHelper(context: Context) : SQLiteOpenHelper
    (context, MyDbNameClass.DATABASE_NAME, null, MyDbNameClass.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        // создание таблицы в нашей базе данных
        db?.execSQL(MyDbNameClass.CREATE_TABLE) // указываем наши ранее созданные команды
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // обновление базы данных (удаление и создание снова таблицы)
        db?.execSQL(MyDbNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}