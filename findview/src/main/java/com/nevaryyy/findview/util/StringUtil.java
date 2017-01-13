package com.nevaryyy.findview.util;

import com.nevaryyy.findview.config.ViewNamingRuleConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nevaryyy
 */
public class StringUtil {

    private static final String TAG = "StringUtil";

    public static String[] splitCamelCase(String string) {
        int lastPos = 0;
        List<String> words = new ArrayList<>();

        for (int i = 1; i < string.length(); i ++) {
            if (Character.isUpperCase(string.charAt(i))) {
                if (i - lastPos > 1) {
                    words.add(string.substring(lastPos, i).toLowerCase());
                    lastPos = i;
                }
            }
        }
        if (lastPos < string.length()) {
            words.add(string.substring(lastPos, string.length()).toLowerCase());
        }

        return words.toArray(new String[0]);
    }

    public static String concatStringsWithUnderline(String[] strings) {
        return concatStringsWithUnderline(strings, 0, strings.length);
    }

    public static String concatStringsWithUnderline(String[] strings, int end) {
        return concatStringsWithUnderline(strings, 0, end);
    }

    public static String concatStringsWithUnderline(String[] strings, int begin, int end) {
        if (begin < 0 || end > strings.length) {
            throw new IllegalArgumentException();
        }
        if (begin == end) {
            return "";
        }

        String string = "";

        for (int i = begin; i < end; i ++) {
            string = string + strings[i] + "_";
        }
        if (string.charAt(string.length() - 1) == '_') {
            string = string.substring(0, string.length() - 1);
        }

        return string;
    }

    public static String deleteAllPackageName(String string) {
        String[] strings = string.split("[.]");
        return strings[strings.length - 1];
    }

    public static String getInnerClassSimpleName(String string) {
        String[] strings = string.split("[$]");
        return strings[strings.length - 1];
    }

    public static String[] splitViewName(String viewName) {
        String[] strings = new String[2];

        if (isEmpty(viewName)) {
            return strings;
        }

        for (int i = 0; i < viewName.length(); i ++) {
            if (i > 0 && Character.isUpperCase(viewName.charAt(i))) {
                if (ViewNamingRuleConfig.VIEWS_ABBREV.containsKey(viewName.substring(i))) {
                    strings[0] = viewName.substring(0, i);
                    strings[1] = ViewNamingRuleConfig.VIEWS_ABBREV.get(viewName.substring(i));
                    break;
                }
            }
        }
        return strings;
    }

    public static String getViewNameAbbrev(String viewName) {
        if (!Character.isLowerCase(viewName.charAt(0))) {
            return null;
        }

        viewName = Character.toUpperCase(viewName.charAt(0)) + viewName.substring(1);
        if (ViewNamingRuleConfig.VIEWS_ABBREV.containsKey(viewName)) {
            return ViewNamingRuleConfig.VIEWS_ABBREV.get(viewName);
        }
        return null;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }

    public static String camelCaseToUnderline(String string) {
        return concatStringsWithUnderline(splitCamelCase(string));
    }

    public static boolean isActivityName(String string) {
        String activityString = "Activity";
        return string.contains(activityString) && string.substring(string.length() - activityString.length()).equals(activityString);
    }

    public static boolean isFragmentName(String string) {
        String fragmentString = "Fragment";
        return string.contains(fragmentString) && string.substring(string.length() - fragmentString.length()).equals(fragmentString);
    }

    public static String toBigCamelCase(String string) {
        char c = Character.toUpperCase(string.charAt(0));
        return c + string.substring(1);
    }
}
