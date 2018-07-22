package devdem.time;
/*
Нагло скопированный с форума код.
Устаревший.
Нет комментариев.*/
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class HandleXml {
    private String version;
    private String urlString;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete=true;
    HandleXml(String url) {
        this.urlString=url;
    }
    public String getVersion() {
        return version;
    }
    private void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        try {
            event=myParser.getEventType();
            while (event!=XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                    break;
                    case XmlPullParser.TEXT:
                        myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("version")) {
                            version=myParser.getAttributeValue(null,"value");
                        }
                        break;
                }
                event=myParser.next();
            }
            parsingComplete=false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchXML(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(urlString);
                    HttpURLConnection connect=(HttpURLConnection)url.openConnection();
                    connect.setReadTimeout(10000);
                    connect.setConnectTimeout(15000);
                    connect.setRequestMethod("GET");
                    connect.setDoInput(true);
                    connect.connect();

                    InputStream stream=connect.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser=xmlFactoryObject.newPullParser();
                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream,null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
