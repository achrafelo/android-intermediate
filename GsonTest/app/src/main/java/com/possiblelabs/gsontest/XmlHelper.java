package com.possiblelabs.gsontest;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.possiblelabs.gsontest.xml.CountryXML;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class XmlHelper {

    public static List<CountryXML> loadFromXML(Context context) {
        XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        GsonXml gsonXml = new GsonXmlBuilder()
                .setXmlParserCreator(parserCreator)
                .create();

        String xml = AssetsHelper.loadFileFromAsset(context, "countries.xml");
        Type listType = new TypeToken<ArrayList<CountryXML>>() {}.getType();

        return gsonXml.fromXml(xml, listType);
    }
}
