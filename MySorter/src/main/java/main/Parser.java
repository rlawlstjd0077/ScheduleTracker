package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parser {
    public String getActorName(String html) {
        Document doc = Jsoup.parse(html);
        return doc.select(".star_info_b").text().split(" / ")[0];
    }
}
