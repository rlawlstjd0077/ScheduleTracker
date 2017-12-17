package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FileWriter {
    private File file;
    private BufferedWriter out;

    public FileWriter() throws IOException {
        file = new File("text/" + DateTimeFormatter.ofPattern("MM-dd").format(ZonedDateTime.now()) + "_Schedule.txt");
        out = new BufferedWriter(new java.io.FileWriter(file));
    }

    public void writeFile(String time, String content) throws IOException {
        try {
            out.append(time + " " + content);
            out.flush();
            out.newLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
