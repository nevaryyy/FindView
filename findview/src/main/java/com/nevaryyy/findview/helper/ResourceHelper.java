package com.nevaryyy.findview.helper;

import android.content.Context;

import com.nevaryyy.findview.util.LogUtil;

import java.lang.reflect.Field;

/**
 * @author nevaryyy
 */
public class ResourceHelper {

    private static final String TAG = "ResourceHelper";

    private static ResourceHelper resourceHelper;
    private static String packageName;
    private static Class layoutClass;
    private static Class drawableClass;
    private static Class idClass;
    private static Class stringClass;
    private static Class attrClass;
    private static Class colorClass;
    private static Class mipmapClass;

    public static ResourceHelper getInstance(Context context) {
        if (resourceHelper == null) {
            synchronized (ResourceHelper.class) {
                packageName = context.getPackageName();
                resourceHelper = new ResourceHelper();
            }
        }
        return resourceHelper;
    }

    private ResourceHelper() {
        try {
            layoutClass = Class.forName(packageName + ".R$layout");
            drawableClass = Class.forName(packageName + ".R$drawable");
            idClass = Class.forName(packageName + ".R$id");
            stringClass = Class.forName(packageName + ".R$string");
            attrClass = Class.forName(packageName + ".R$attr");
            colorClass = Class.forName(packageName + ".R$color");
            mipmapClass = Class.forName(packageName + ".R$mipmap");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getResId(Class cls, String resName) {
        if (cls == null) {
            LogUtil.e(TAG, "Resource Class is not initialized.");
        }
        else {
            try {
                Field field = cls.getField(resName);
                return field.getInt(resName);
            }
            catch (Exception e) {
                LogUtil.e(TAG, " Resource not found: " + resName + ".");
            }
        }
        return -1;
    }

    public int getLayoutId(String resName) {
        return getResId(layoutClass, resName);
    }

    public int getDrawableId(String resName) {
        return getResId(drawableClass, resName);
    }

    public int getMipmapId(String resName) {
        return getResId(mipmapClass, resName);
    }

    public int getViewId(String resName) {
        return getResId(idClass, resName);
    }

    public int getStringId(String resName) {
        return getResId(stringClass, resName);
    }

    public int getAttrId(String resName) {
        return getResId(attrClass, resName);
    }

    public int getColorId(String resName) {
        return getResId(colorClass, resName);
    }
}
