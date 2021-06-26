package com.example.sqlitekotlin_lesson17.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {   // класс менеджер для помощи в работе с базой данных

    val myDbHelper = MyDbHelper(context) // создаем переменную для работы с базой данных
    var db: SQLiteDatabase? = null // класс который будет открывать/закрывать базу данных

    fun openDb () { // функция открытия базы данных
        db = myDbHelper.writableDatabase    // открытие базы данных для записи
    }

    // функция для записи в базу данных
    fun insertToDb (title: String, content: String, uri: String) {

    // переменная для передачи готовой строки данных в таблицу содержащая
    // ключ(key) + значения, класс ContentValues типа массива где сохраняются данные ключ-значение
        val values = ContentValues().apply {

            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URI, uri)   // добавили еще один элемент - ссылку на картинку
        }

        db?.insert(MyDbNameClass.TABLE_NAME, null, values)  // вставляем данные в таблицу
    }

    // функция считывания данных из нашей таблицы в базе данных
    fun readDbData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        // переменная для считывания данных из таблицы в базе данных
        // можно указать параметры поиска данных в таблице, если нет параметров то указывается null
            val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null,
                null, null, null, null)

            // работа с переменной cursor
            with(cursor){
                while (this?.moveToNext()!!)    // перебирание элементов? если все элементы заканчиваются
                // то while останавливается и мы выходим из этой функции
                {
                    // достаем данные String из столбца title нашей таблицы
                    val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))

                    // помещаем полученные данные в список
                    dataList.add(dataText.toString())
                }
            }

        // тоже самое
        /* while (cursor?.moveToNext()!!) {
             val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
             dataList.add(dataText.toString()) } */

        // закрытие курсора
        cursor?.close()

        return dataList
    }

    // закрытие базы данных
    fun closeDb () {
        myDbHelper.close()
    }

}