package com.example.sqlitekotlin_lesson17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sqlitekotlin_lesson17.db.MyDbManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditActivity : AppCompatActivity() {

    val imageRequestCode = 10 // переменная для запуска приложения отображающего картинки
    var tempImageUri = "empty"  // переменная для временного сохранения ссылки на картинку
    val myDbManager = MyDbManager(this)  // создаем экземпляр MyDbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)

        // изчезает поле с картинкой и кнопками
        val mainImageLayout = findViewById<ConstraintLayout>(R.id.mainImageLayout)
        mainImageLayout.visibility = View.GONE
    }

    // функция для проверки успешности получения картинки
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // resultCode выдает успешность запроса а requestCode сравнивается с нашим imageRequestCode
        if (resultCode == RESULT_OK && requestCode == imageRequestCode) {
            // если true то значит пришел ответ на наш запрос на ссылку картинки

            val imMainImage = findViewById<ImageView>(R.id.imMainImage)
            imMainImage.setImageURI(data?.data) // показываем картинку в imMainImage если ссылка не null
            tempImageUri = data?.data.toString() // сохраняем ссылку на картинку в переменную

        }
    }

    fun onClickAddImage(view: View) {   // действия при нажатии на кнопку fbAddImage

        // появляется поле с картинкой и кнопками
        val mainImageLayout = findViewById<ConstraintLayout>(R.id.mainImageLayout)
        mainImageLayout.visibility = View.VISIBLE

        // кнопка fbAddImage при этом изчезает
        val fbAddImage = findViewById<FloatingActionButton>(R.id.fbAddImage)
        fbAddImage.visibility = View.GONE

    } // fun onClickAddImage

    fun onClickDeleteImage(view: View) { // действия при нажатии на кнопку imButtonDeleteImage

        // изчезает поле с картинкой и кнопками
        val mainImageLayout = findViewById<ConstraintLayout>(R.id.mainImageLayout)
        mainImageLayout.visibility = View.GONE

        // кнопка fbAddImage при этом появляется
        val fbAddImage = findViewById<FloatingActionButton>(R.id.fbAddImage)
        fbAddImage.visibility = View.VISIBLE

    } // fun onClickDeleteImage

    // функция для выбора картинки при нажатии на кнопку добавить картинку
    fun onClickChooseImage(view: View) { // действия при нажатии на кнопку imButtonDeleteImage

        // для выбора картинки будем использовать вызов приложения - галерея
        // для вызова приложения галерея необходимо создать Intent
        val intent = Intent(Intent.ACTION_PICK) // этот интент вызывает приложение которое может открыть картинки
        intent.type = "image/*" // тип интента - все картинки
        startActivityForResult(intent, imageRequestCode) // получаем ссылку на картинку

    } // fun onClickChooseImage

    // функция для сохранения
    fun onClickSave(view: View) { // действия при нажатии на кнопку imButtonDeleteImage

        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edDesc = findViewById<EditText>(R.id.edDesc)

        val myTitle = edTitle.text.toString() // сохраняем текст из edTitle в переменную myTitle
        val myDesc = edDesc.text.toString()   // сохраняем текст из edDesc в переменную myDesc

        if (myTitle != "" && myDesc != "") {    // проверяем условие что оба поля не пусты

            myDbManager.insertToDb(myTitle, myDesc, tempImageUri)   // сохраняем данные в базу данных

            // SELECT * FROM my_table
            // запрос в базу данных в Database Inspector
            // для отображения всех записей из нашей таблицы

            // SELECT uri FROM my_table
            // запрос в базу данных в Database Inspector
            // для отображения всех ссылок на картинки из нашей таблицы

        }

    } // fun onClickSave

    override fun onResume() {
        super.onResume()
        // открываем базу данных для редактирования
        myDbManager.openDb()
        // возвращает считанные данные из таблицы базы данных в виде массива
        // val dataList = myDbManager.readDbData()
        // выводим считанные из таблицы данные в TextView = tvTest
    } // onResume()

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    } // onDestroy()

} // class EditActivity