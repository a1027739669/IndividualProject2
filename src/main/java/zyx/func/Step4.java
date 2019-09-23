package zyx.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step4 {
    public static List<List<String>> change(Path vebPath) throws IOException {
        List<List<String>> vebs = new ArrayList<>();
        List<String> list = Files.readAllLines(vebPath);
        Pattern pattern = Pattern.compile("[a-z][0-9a-z]*");
        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            List<String> temp = new ArrayList<>();
            while (matcher.find()) {
                temp.add(matcher.group());
            }
            vebs.add(temp);
        }
        return vebs;
    }
}
