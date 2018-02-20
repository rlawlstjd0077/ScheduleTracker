package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDriver {
    public static void main(String args[]) throws IOException {
        FolderScanner folderScanner = new FolderScanner();
        folderScanner.doScan("");
//        Requester requester = new Requester();
//        System.out.println(requester.doRequest("https://hentaku.net/poombun.php", "ABP533"));
    }
}
