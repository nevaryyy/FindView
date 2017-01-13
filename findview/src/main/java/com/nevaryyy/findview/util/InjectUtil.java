package com.nevaryyy.findview.util;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nevaryyy.findview.Injectable;
import com.nevaryyy.findview.annotation.FindView;
import com.nevaryyy.findview.annotation.SetLayout;
import com.nevaryyy.findview.helper.IDHelper;

import java.lang.reflect.Field;

/**
 * @author nevaryyy
 */
public class InjectUtil {

    private static final String TAG = "InjectUtil";

    public static void inject(Object object) {
        if (object instanceof AppCompatActivity) {
            injectActivity((AppCompatActivity) object);
        }
        else if (object instanceof Fragment) {
            injectFragment((Fragment) object);
        }
    }

    private static void injectActivity(AppCompatActivity baseActivity) {
        String activityName = StringUtil.deleteAllPackageName(baseActivity.getClass().getName());

        if (!StringUtil.isActivityName(activityName)) {
            LogUtil.e(TAG, "Make sure the injected view is an activity.");
            return;
        }

        if (baseActivity.getClass().isAnnotationPresent(SetLayout.class)) {
            if (!baseActivity.getClass().getAnnotation(SetLayout.class).value()) {
                LogUtil.d(TAG, activityName + " doesn't need to set layout automatically.");
                return;
            }

            String[] activityWords = StringUtil.splitCamelCase(activityName);
            String activityNameWithUnderline = StringUtil.concatStringsWithUnderline(activityWords, activityWords.length - 1);
            String layoutName = "activity_" + activityNameWithUnderline;

            baseActivity.setContentView(IDHelper.getLayout(baseActivity.getApplicationContext(), layoutName));
            LogUtil.d(TAG, "Set " + activityName + "'s layout success.");

            Field[] fields = baseActivity.getClass().getDeclaredFields();

            try {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(FindView.class)) {
                        String fieldName = field.getName();
                        String viewNameAbbrev = StringUtil.getViewNameAbbrev(fieldName);
                        String[] viewNameSplit = StringUtil.splitViewName(fieldName);
                        String idName;

                        if (!StringUtil.isEmpty(viewNameAbbrev)) {
                            idName = viewNameAbbrev + "_" + activityNameWithUnderline;
                        }
                        else {
                            if (StringUtil.isEmpty(viewNameSplit[0]) || StringUtil.isEmpty(viewNameSplit[1])) {
                                LogUtil.e(TAG, "Failed to parse the view name. " + fieldName + " doesn't comply with the ViewNamingRule.");
                                continue;
                            }
                            else {
                                idName = viewNameSplit[1] + "_" + activityNameWithUnderline + "_" + StringUtil.camelCaseToUnderline(viewNameSplit[0]);
                            }
                        }

                        View view = baseActivity.findViewById(IDHelper.getViewId(baseActivity.getApplicationContext(), idName));

                        field.setAccessible(true);
                        field.set(baseActivity, view);

                        LogUtil.d(TAG, "Success to initial view: " + fieldName + ".");
                    }
                }
            }
            catch (IllegalAccessException e) {
                LogUtil.e(TAG, "Failed to inject " + activityName + ". Illegal access.");
                e.printStackTrace();
            }
        }
    }

    private static void injectFragment(Fragment baseFragment) {
        String fragmentName = StringUtil.deleteAllPackageName(baseFragment.getClass().getName());

        if (!StringUtil.isFragmentName(fragmentName)) {
            LogUtil.e(TAG, "Make sure the injected view is a fragment.");
            return;
        }

        if (!(baseFragment instanceof Injectable)) {
            LogUtil.e(TAG, "Make sure the injected fragment implements Injectable interface.");
            return;
        }

        if (baseFragment.getClass().isAnnotationPresent(SetLayout.class)) {
            if (!baseFragment.getClass().getAnnotation(SetLayout.class).value()) {
                LogUtil.d(TAG, fragmentName + " doesn't need to set layout automatically.");
                return;
            }

            String[] fragmentWords = StringUtil.splitCamelCase(fragmentName);
            String fragmentNameWithUnderline = StringUtil.concatStringsWithUnderline(fragmentWords, fragmentWords.length - 1);
            String layoutName = "fragment_" + fragmentNameWithUnderline;

            ((Injectable) baseFragment).setRootView(IDHelper.getLayout(baseFragment.getContext().getApplicationContext(), layoutName));
            LogUtil.d(TAG, "Set " + fragmentName + "'s layout success.");

            Field[] fields = baseFragment.getClass().getDeclaredFields();

            try {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(FindView.class)) {
                        String fieldName = field.getName();
                        String viewNameAbbrev = StringUtil.getViewNameAbbrev(fieldName);
                        String[] viewNameSplit = StringUtil.splitViewName(fieldName);
                        String idName;

                        if (!StringUtil.isEmpty(viewNameAbbrev)) {
                            idName = viewNameAbbrev + "_" + fragmentNameWithUnderline;
                        }
                        else {
                            if (StringUtil.isEmpty(viewNameSplit[0]) || StringUtil.isEmpty(viewNameSplit[1])) {
                                LogUtil.e(TAG, "Failed to parse the view name. " + fieldName + " doesn't comply with the ViewNamingRule.");
                                continue;
                            }
                            else {
                                idName = viewNameSplit[1] + "_" + fragmentNameWithUnderline + "_" + StringUtil.camelCaseToUnderline(viewNameSplit[0]);
                            }
                        }

                        View view = ((Injectable) baseFragment).getRootView().
                                findViewById(IDHelper.getViewId(baseFragment.getContext().getApplicationContext(), idName));

                        field.setAccessible(true);
                        field.set(baseFragment, view);

                        LogUtil.d(TAG, "Success to initial view: " + fieldName + ".");
                    }
                }
            }
            catch (IllegalAccessException e) {
                LogUtil.e(TAG, "Failed to inject " + fragmentName + ". Illegal access.");
                e.printStackTrace();
            }
        }
    }
}
