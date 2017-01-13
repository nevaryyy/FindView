package com.nevaryyy.findview.helper;

import android.content.Context;

/**
 * @author nevaryyy
 */
public class IDHelper {

    public static int getLayout(Context context, String layoutName) {
        return ResourceHelper.getInstance(context).getLayoutId(layoutName);
    }

    public static int getViewId(Context context, String idName) {
        return ResourceHelper.getInstance(context).getViewId(idName);
    }

    public static int getDrawable(Context context, String drawableName) {
        return ResourceHelper.getInstance(context).getDrawableId(drawableName);
    }

    public static int getMipmap(Context context, String mipmapName) {
        return ResourceHelper.getInstance(context).getMipmapId(mipmapName);
    }

    public static int getString(Context context, String stringName) {
        return ResourceHelper.getInstance(context).getStringId(stringName);
    }

    public static int getAttr(Context context, String attrName) {
        return ResourceHelper.getInstance(context).getAttrId(attrName);
    }

    public static int getColor(Context context, String colorName) {
        return ResourceHelper.getInstance(context).getColorId(colorName);
    }
}
