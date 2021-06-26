package com.example.sqlitekotlin_lesson17.db

import android.provider.BaseColumns // имеет идентификатор который добавляется автоматически

object MyDbNameClass : BaseColumns {    // создаем объект для хранения всех названий элементов таблицы

    // определяем вид нашей таблицы в базе данных
    const val TABLE_NAME = "my_table"       // 0    title   content - вид нашей базы данных
    const val COLUMN_NAME_TITLE = "title" // название столбца
    const val COLUMN_NAME_CONTENT = "content" // название столбца
    const val COLUMN_NAME_IMAGE_URI = "uri" // название столбца

    // определяем свойства нашей базы данных
    const val DATABASE_VERSION = 2  // версия базы данных, т.к. добавили еще один столбец то 2 версия
    const val DATABASE_NAME = "MyLessonDB.db"   // название нашей базы данных

    // значения для создания таблицы с нашим названием если такой таблицы еще не существует
    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," + // идентификатор который будет автоматически добавляться при добавлении каждого элемента
            "${COLUMN_NAME_TITLE} TEXT," +
            "${COLUMN_NAME_CONTENT} TEXT," +
            "${COLUMN_NAME_IMAGE_URI} TEXT" + ")"  // тип данных TEXT
    // или так
    //"${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_CONTENT TEXT, $COLUMN_NAME_IMAGE_URI TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}
// BaseColumns дает идентификатор в первом столбце
//  0   title   content uri
//  1   title   content uri
//  2   title   content uri
//  3   title   content uri
//  4   title   content uri
//  5   title   content uri