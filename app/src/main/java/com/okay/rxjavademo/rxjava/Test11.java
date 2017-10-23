package com.okay.rxjavademo.rxjava;

import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.internal.Util;

/**
 * Created by xmq on 2017/8/21.
 */

public class Test11 {
    public static void main(String args[]) {
        List<Message> list =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message m =new Message();
            m.obj = "瞎写的";
            m.what = i;
            list.add(m);
        }
        String array = listTojsonStr(list);
        System.out.print(array);
//        try {
//            JSONArray jarray = new JSONArray(array);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        List result = jsonStrToList(array);
        System.out.print(result);

    }

    private static Gson gson =new Gson();

    private static <T> List<T> jsonStrToList(String array) {
        return gson.fromJson(array,new TypeToken<List<T>>(){}.getType());
    }


    private static  String listTojsonStr(List list) {
        return gson.toJson(list);
    }
}
