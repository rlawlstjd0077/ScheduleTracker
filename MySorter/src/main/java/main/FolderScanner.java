package main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderScanner {
    private Requester requester;
    private Parser parser;
    private Pattern pattern = Pattern.compile("^*([a-zA-Z]?[a-zA-Z]?[a-zA-Z]?[a-zA-Z][a-zA-Z])-?([0-9]?[0-9][0-9])");

    public FolderScanner() {
        this.requester = new Requester();
        this.parser = new Parser();
    }

    public void doScan(String path) {
        File folder = new File(path);

        Arrays.stream(folder.listFiles()).filter(e -> e.isFile()).forEach(e -> {
            String pid = matchPatten(FilenameUtils.removeExtension(e.getName()));
            if(pid != null) {
                try {
                    String result = requester.doRequest("",pid);
                    String actor =parser.getActorName(result);
                    if(!actor.equals("")) {
                        File actorFolder = new File(path + "/" + actor);
                        if(!actorFolder.exists()){
                            actorFolder.mkdir();
                        }
                        FileUtils.moveFile(e, new File(actorFolder + "/" + e.getName()));
                    }
                } catch (IOException e1) {
                }
            }
        });
    }

    private String matchPatten(String fileName) {
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.find()) {
            return matcher.group().replace("-", "");
        } else {
            return null;
        }
    }
}
