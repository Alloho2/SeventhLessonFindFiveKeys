package com.msaggik.seventhlessonfindfivekeys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // поля
    private TextView screen, coordinates;
    private float x; // координата касания по оси X
    private float y; // координата касания по оси Y
    private int[] coordinatesKeys; // массив координат 5 ключей
    private int interval = 40; // погрешность поиска
    Key[] keys = new Key[5]; //Массив из объектов Key
    private FrameLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout=findViewById(R.id.mainLayout);

        // привязка разметки к полям
        screen = findViewById(R.id.screen);
        coordinates = findViewById(R.id.coordinates);
        //заполнение массива объктами Key
           for (int i = 0; i <keys.length ; i++) {
            keys[i] = new Key(i+"") ;
        }

        // обработка касания TextView
        screen.setOnTouchListener(listener);
    }

    // создание слушателя
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            // определение координат касания
            x = motionEvent.getX();
            y = motionEvent.getY();

            // определение типа касания
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: // нажатие
                    coordinates.setText("Нажатие " + x + ", " + y);
                    //проверка что ключи создались, вывод в консоль
                    Arrays.stream(keys).forEach(key -> System.out.println(key.getName()+key.getX()+key.getY()));
                    break;
                case MotionEvent.ACTION_MOVE: // движение
                    coordinates.setText("Движение " + x + ", " + y);
                    for (Key key:keys) {
                        if (x >= (key.getX() - interval) && x <= (key.getX() + interval) &&
                                y >= (key.getY() - interval) && y <= (key.getY() + interval)) {
                            Toast.makeText(MainActivity.this, "Найден " + (Integer.parseInt(key.getName()) + 1) + " ключ", Toast.LENGTH_SHORT).show();
                            addKeyIcon(key.getX(), key.getY());
                        }
                    }
                    break;

                case MotionEvent.ACTION_UP: // отпускание
                    coordinates.setText("Отпускание " + x + ", " + y);
                    break;
            }

            return true;
        }
    };

   //метод добавление иконки
    private void addKeyIcon(float x, float y) {
        ImageView keyIcon = new ImageView(this);
        keyIcon.setImageResource(R.drawable.key_icon);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (int) x;
        params.topMargin = (int) y;
        mainLayout.addView(keyIcon, params);
    }
}