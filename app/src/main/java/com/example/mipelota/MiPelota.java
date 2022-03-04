package com.example.mipelota;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MiPelota extends View implements SensorEventListener {
    Paint pincel = new Paint();
    int alto, ancho;
    int tamaño = 40;
    int borde = 12;
    float ejeX= 0, ejeY=0, ejeZ1=0, ejeZ=0;
    String X,Y,Z;
    public MiPelota(Context interfaz){
        super(interfaz);
        SensorManager smAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snRotacion = smAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdministrador.registerListener(this,snRotacion,SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ejeX -= event.values[0];
        X = Float.toString(ejeX);

        if (ejeX < (tamaño + borde)){
            ejeX = (tamaño+borde);
        }
        else if (ejeX > (ancho-(tamaño + borde))){
            ejeX = ancho-(tamaño+borde);
        }
        ejeY += event.values[1];
        Y=Float.toString(ejeY);
        if (ejeY < (tamaño+borde)){
            ejeY = (tamaño+borde);
        }
        else if (ejeY > (alto-tamaño-170)){
            ejeY =  alto-tamaño-170;
        }
        ejeZ=event.values[2];
        Z = String.format("%.2f",ejeZ);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onDraw(Canvas lienzo) {
        pincel.setColor(Color.BLACK);
        lienzo.drawCircle(ejeX,ejeY,(ejeZ+5)+tamaño,pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(50);

        lienzo.drawText("",ejeX-35,ejeY+3, pincel);
    }
}
