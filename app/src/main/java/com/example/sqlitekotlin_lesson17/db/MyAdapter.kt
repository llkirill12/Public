package com.example.sqlitekotlin_lesson17.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitekotlin_lesson17.R
import java.util.zip.Inflater

class MyAdapter (listMain:ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    // изначально мы передаем пустой массив для запуска
    // для заполнения данными rc_item из таблицы базы данных
// ViewHolder будет содержать все элементы view одного элемента данных, у нас это tvTitle в rc_item
    var listArray = listMain    // передаем список из активити

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // каждый элемент будет создавать новый ViewHolder, у нас это tvTitle в rc_item
    // рисуются элементы только видимые на экране, что позволяет экономить память

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun setData (title: String) {   // сюда нужно передать наш TextView
            // дополнительная вспомогательная функция

            tvTitle.text = title    // добавляет данные в элемент

        } // fun setData

    } // class MyHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // эта функция где мы создаем наш шаблон и это шаблон передаем в MyHolder
        // эта функция запускается при создании каждого элемента

        // Inflater это специальный класс который преобразует xml файл в объект который будет нарисован на экране
        val inflater = LayoutInflater.from(parent.context)  // взяли контекст из parent

        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false)) // заполняем(надуваем) rc_item


    } // override fun onCreateViewHolder

    override fun onBindViewHolder(holder: MyHolder, position: Int) { // position - позиция создаваемого элемента
        // функция служит для заполнения данными созданного шаблона
        // эта функция запускается при создании каждого элемента

        holder.setData(listArray.get(position)) // создается один элемент с данными из listArray и позицией position

    } // override fun onBindViewHolder

    override fun getItemCount(): Int {
    // сообщает нашему адаптеру сколько элементов будет необходимо создать
    // т.е. сколько элементов надо будет подключить к RecyclerView - rcView
    // т.е. сколько элементов в нашем списке который мы ему передали

    return  listArray.size  // возвращаем размер массива

    } // override fun getItemCount()

    fun updateAdapter (listItems:List<String>) {  // для обновления адаптера так как изначально создаем его с пустым массивом
    // берет массив listArray очищает его, заполняет новыми данными и собщает адаптеру что данные изменились

        listArray.clear()   // очиащем список
        listArray.addAll(listItems) //добавляем необходимые элементы
        notifyDataSetChanged() // сообщаем адаптеру что данные изменились

    }

} // class MyAdapter