package com.example.demo;

import com.example.demo.collection.Sight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class KeelungSightsCrawler {
    public String[] getAllZone(){
        ArrayList<String> zones =new ArrayList<>();
        try{
            Document document = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            Elements zone = document.select("div.box").select("h4");
            for(Element e:zone){
                zones.add(e.text());
                System.out.println(e.text());
                System.out.println(zones);
            }
        }catch(Exception e){
            System.out.println("fail");
        }
        String[] tmp=new String[zones.size()];
        zones.toArray(tmp);
        for(int i=0;i<tmp.length;i++){
            System.out.println(tmp[i]);
        }
        return tmp;
    }
    public Sight[] getItems( String zone) {
        //zone = zone + "區";
        ArrayList<String> url = getSightURL(zone);
        ArrayList<Sight> detail = getSightdetail(url);
        Sight[] res = new Sight[detail.size()];
        detail.toArray(res);
        return res;
    }

    public ArrayList<String> getSightURL(String targetzone) {
        ArrayList<String> url = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            Elements zones = document.select("div.box").select("h4");
            for (Element ele : zones) {
                String zone = ele.text();
                if (zone.equals(targetzone)) {
                    Elements targetlink = ele.nextElementSibling().select("a");
                    for (Element t : targetlink) {
                        url.add(t.attr("href"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("fail");
        }
        return url;
    }

    public ArrayList<Sight> getSightdetail(@org.jetbrains.annotations.NotNull ArrayList<String> url) {
        ArrayList<Sight> SightDetail = new ArrayList<>();
        for (String ele : url) {
            Sight cursight = new Sight();
            ele = "https://www.travelking.com.tw" + ele;
            try {
                Document SightURL = Jsoup.connect(ele).get();
                String zone = SightURL.select("ol.breadcrumb").select("li.bc_last").text();
                //System.out.println("zone"+zone);
                cursight.setZone(zone);
                String sightname = SightURL.select("h1.h1").select("span").text();
                //System.out.println("sightname "+sightname);
                cursight.setSightName(sightname);
                String category = SightURL.select("[property=rdfs:label]").text();
                //System.out.println("category:" +category);
                cursight.setCategory(category);
                Elements imagelink = SightURL.select("div.gpic").select("[data-src]");
                ArrayList<String> image = new ArrayList<>();
                for (Element img : imagelink) {
                    if (img != null) {
                        String srcStr = img.attr("abs:data-src");
                        image.add(srcStr);
                    }
                }
                //System.out.println("photourl");
                cursight.setPhotoURL(image);
                Elements description = SightURL.select("div.text[property=dc:description]");
                description.select("div.author").remove();
                description.select("div.othermsg2").remove();
                Element thisOne;
                Iterator it = description.iterator();
                thisOne = (Element) it.next();
                cursight.setDescription(thisOne.text());
                Elements address = SightURL.select("div.address").select("p").select("span");
                if (address != null) {
                    cursight.setAddress(address.text().toString());
                }
                Elements Link = SightURL.select("div.address").select("p").select("a");
                String addressLink = Link.attr("href");
                cursight.setAddressLink(addressLink);
                SightDetail.add(cursight);
            } catch (Exception e) {
                System.out.println("error " + e);
            }
        }
        return SightDetail;
    }
}
