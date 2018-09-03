package com.test.baseandroid.utils.binding;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class ViewBinder {

    public static void bind(Activity activity) {
        //activity.getWindow().getDecorView():获取顶级视图
        bind(activity, activity.getWindow().getDecorView());
    }

    public static void bind(Object target, View source) {
//        得到activity所有字段
        Field[] fields = target.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    //取消java的权限控制检查
                    field.setAccessible(true);
                    if (field.get(target) != null) {
                        continue;
                    }
                    //获取这个类中所有注解
                    Bind bind = field.getAnnotation(Bind.class);
                    if (bind != null) {
                        int viewId = bind.value();
                        field.set(target, source.findViewById(viewId));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
