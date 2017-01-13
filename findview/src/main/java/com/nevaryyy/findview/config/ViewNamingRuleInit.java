package com.nevaryyy.findview.config;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author nevaryyy
 */

public class ViewNamingRuleInit {

    private static ViewNamingRuleInit viewNamingRuleInit = null;

    private ViewNamingRuleInit() {

    }

    @NonNull
    public static ViewNamingRuleInit getInstance() {
        if (viewNamingRuleInit == null) {
            synchronized (ViewNamingRuleInit.class) {
                if (viewNamingRuleInit == null) {
                    viewNamingRuleInit = new ViewNamingRuleInit();
                }
            }
        }
        return viewNamingRuleInit;
    }

    public void loadViewNamingRule(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("view_naming_rule.xml");
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            ViewNamingRuleXMLParserHandler parserHandler = new ViewNamingRuleXMLParserHandler();

            parser.parse(inputStream, parserHandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
