package zyx.func;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step3 {
    public static List<Map.Entry<String, Integer>> countPhrase(Path filePath,int n) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> list = Files.readAllLines(filePath);
        String regex="";
        for(int i=0;i<n-1;i++){
            regex+="[a-z][0-9a-z]*\\s";
        }
        regex+="[a-z][0-9a-z]*";
        Pattern pattern = Pattern.compile(regex);
        for (String s : list) {
            Matcher matcher = pattern.matcher(s.toLowerCase());
            while (matcher.find()) {
                String temp = matcher.group();
                if (hashMap.get(temp) != null) {
                    hashMap.put(temp, hashMap.get(temp) + 1);

                } else {
                    hashMap.put(temp, 1);

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
    public static void cout1(Path filePath,int n) throws IOException {
        List<HashMap.Entry<String, Integer>> list = Step3.countPhrase(filePath,n);
        int count=0;
        for (HashMap.Entry<String, Integer> entry : list) {
            count+=entry.getValue();
        }
        System.out.println("只输出前100位:");
        for (int i = 0; i < 100; i++) {
            HashMap.Entry<String,Integer> entry=list.get(i);
            System.out.printf("短语:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count);
        }
//        for (HashMap.Entry<String, Integer> entry : list) {
//            System.out.printf("短语:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count);
//        }
    }
}
