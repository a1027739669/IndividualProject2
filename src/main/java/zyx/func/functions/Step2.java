package zyx.func.functions;

import zyx.func.functions.Step1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

public class Step2 {
    public static void countWordsWithStopWords(Path stopwordspath, Path filepath) throws IOException {
        List<String> list = Files.readAllLines(stopwordspath, Charset.forName("GBK"));
        List<HashMap.Entry<String, Integer>> hashMap = Step1.countWords(filepath);
        for (String word : list) {
            for (int i = 0; i < hashMap.size(); i++) {
                if (hashMap.get(i).getKey().equalsIgnoreCase(word)) {
                    hashMap.remove(i);
//                    System.out.println(hashMap.get(i).getKey());
                }
            }
        }
        int count = 0;
        for (HashMap.Entry<String, Integer> entry : hashMap) {
            count += entry.getValue();
        }
        System.out.println("去掉停词表中的单词后，单词分布频率如下:");
        for (HashMap.Entry<String, Integer> entry : hashMap) {
            System.out.printf("单词:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(), (float) entry.getValue() * 100 / count);
        }
    }

    public static void countWordsWithStopWordsAndDirec(Path stopwordspath, Path filepath) throws IOException {
        Files.walkFileTree(filepath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("*****************************");
                System.out.println("正在访问文件:" + file.getFileName());
                Step2.countWordsWithStopWords(stopwordspath,file.toAbsolutePath());
                System.out.println("*****************************");
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
