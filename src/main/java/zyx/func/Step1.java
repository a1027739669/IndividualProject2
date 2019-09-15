package zyx.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step1 {
    private int count = 0;

    public List<HashMap.Entry<String, Integer>> countWords(String filePath) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> list = Files.readAllLines(Paths.get(filePath));
        Pattern pattern = Pattern.compile("[a-z][0-9a-z]*");
        for (String s : list) {
            Matcher matcher = pattern.matcher(s.toLowerCase());
            while (matcher.find()) {
                String temp = matcher.group();
                if (hashMap.get(temp) != null) {
                    hashMap.put(temp, hashMap.get(temp) + 1);
                    count++;
                } else {
                    hashMap.put(temp, 1);
                    count++;
                }
            }
        }
        List<HashMap.Entry<String, Integer>> result = new ArrayList<>(hashMap.entrySet());
        Collections.sort(result, new Comparator<HashMap.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return -o1.getValue().compareTo(o2.getValue());
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });
        return result;
    }

    public void cout1(String filePath) throws IOException {
        List<HashMap.Entry<String, Integer>> list = this.countWords(filePath);
        for (HashMap.Entry<String, Integer> entry : list) {
            System.out.printf("单词:%s 出现次数:%d \n", entry.getKey(), entry.getValue());
        }
    }


}

