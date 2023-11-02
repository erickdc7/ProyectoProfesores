package com.example.proyectoprofesores;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VoleySingleton {


    private static VoleySingleton intanciaV;
    private RequestQueue request;
    private ImageLoader mImageLoader;
    private static Context contexto;

    private VoleySingleton(Context contexto) {
        contexto = contexto;
        request = getRequestQueue();
    }



    public static  synchronized VoleySingleton getIntanciaV(Context context) {
        if(intanciaV==null){
            intanciaV=new VoleySingleton(contexto);
        }
        return intanciaV;
    }

    private RequestQueue getRequestQueue() {
        if(request==null){
            request= Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return request;
    }

    public <T> void addToRequestQueue (Request<T>request){
        getRequestQueue().add(request);
    }


}
