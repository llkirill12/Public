package com.example.sqlitekotlin_lesson17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sqlitekotlin_lesson17.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)  // создаем экземпляр MyDbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        // открываем базу данных для редактирования
        myDbManager.openDb()
        // возвращает считанные данные из таблицы базы данных в виде массива
        val dataList = myDbManager.readDbData()
        // выводим считанные из таблицы данные в TextView = tvTest
    } // onResume()

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    } // onDestroy()

    fun onClickSave(view: View) {   // слушатель нажатия на кнопку SAVE

        // сохраняем данные в таблицу базы данных
        //myDbManager.insertToDb(edTitle.text.toString(), edContent.text.toString())

        // возвращает считанные данные из таблицы базы данных в виде массива
        val dataList = myDbManager.readDbData()

        // выводим считанные из таблицы данные в TextView = tvTest

    }

    fun onClickNew(view: View) {    // нажатие на кнопку New

        // запускаем EditActivity при нажатии на кнопку New
        val i = Intent(this,EditActivity::class.java)
        startActivity(i)

    }
}