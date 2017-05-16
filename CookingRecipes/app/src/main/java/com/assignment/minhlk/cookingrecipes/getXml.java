package com.assignment.minhlk.cookingrecipes;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by minhlk on 3/13/17.
 */

public class getXml extends AsyncTask<String, Void, String> {
    Context mcontext;
    RecyclerView rc_lowcarb;
    public  ArrayList<Items> items = new ArrayList<>();
    public getXml(Context mcontext,RecyclerView rc_lowcarb) {
        this.mcontext = mcontext;
        this.rc_lowcarb = rc_lowcarb;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String site = params[0];
            URL url = new URL(site);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
//                conn.setDoOutput(true);
            InputStream inputStream = conn.getInputStream();

//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
//                StringBuilder builder = new StringBuilder();
//                String line;
//
//                while ( (line =reader.readLine()) != null){
//
//                    builder.append(line).append("\n");
//
//                }
//                inputStream.close();
//                return builder.toString();

            return parseXML(new InputStreamReader(inputStream));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(mcontext, "success", Toast.LENGTH_SHORT).show();
//            t.setText(s);
//        adapter.notifyDataSetChanged();

        RcAdapter adapter = new RcAdapter(items,mcontext);
        rc_lowcarb.setAdapter(adapter);
        rc_lowcarb.setLayoutManager(new LinearLayoutManager(mcontext));
    }
    private String parseXML(InputStreamReader in){

        try {
            String date = "";
//            String  names = "";
//            items = new ArrayList<>();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in);
            int event = parser.getEventType();
            String text = "";
            String title = "",link = "",img = "",caption = "";
            while (event != XmlPullParser.END_DOCUMENT){
                String name = parser.getName();
//                String text = "";

                //get title, link description  src="   jpg"

                switch (event){
                    case XmlPullParser.START_TAG: break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        Log.i("TAG", "" + text);
//                        Log.i("TAG", "parseXML: a");
                        break;
                    case XmlPullParser.END_TAG:
//                        Log.i("TAG", "parseXML: a");

                        if(name.equals("title")){
                            title = text;
                        }
                        else if(name.equals("link")){
                            link = text;
//                            Log.i("TAG", "parseXML: "+link);
                        }
                        else if(name.equals("description") && text.contains("src")){
//                            Log.i("TAG", " " + name);
                            int start = text.indexOf("src=\"") +5;
                            int end = text.indexOf("jpg\"")+3;
//                            Log.i("TAG", " " + text.substring(start,end));
                            img = text.substring(start,end);
                            caption = text.substring(text.indexOf("<p>"));
                        }
                        break;
                }
                if(!title.isEmpty() && !img.isEmpty()) {
                    items.add(new Items(img, title, link, caption));
                    //restore first state
                    title = "";
                    link = "";
                    img = "";
                }
                event = parser.next();


            }

            return date;


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
