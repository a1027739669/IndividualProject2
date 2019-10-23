package zyx.func.functions;

import zyx.func.functions.Step1;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

public class Step2 {
    public static void countWordsWithStopWords(Path stopwordspath, Path filepath, PrintStream printStream) throws IOException {
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
        printStream.println("去掉停词表中的单词后，单词分布频率如下:");
//        System.out.println("去掉停词表中的单词后，单词分布频率如下:");
        for (HashMap.Entry<String, Integer> entry : hashMap) {
            printStream.println(String.format("单词:%s 出现次数:%d 频率:%.2f%%", entry.getKey(), entry.getValue(), (float) entry.getValue() * 100 / count));
//            System.out.printf("单词:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(), (float) entry.getValue() * 100 / count);
        }
    }

    public static void countWordsWithStopWordsAndDirec(Path stopwordspath, Path filepath,PrintStream printStream) throws IOException {
        Files.walkFileTree(filepath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println("*****************************");
//                System.out.println("正在访问文件:" + file.getFileName());
                printStream.println("*****************************");
                printStream.println("正在访问文件:" + file.getFileName());
                Step2.countWordsWithStopWords(stopwordspath,file.toAbsolutePath(),printStream);
//                System.out.println("*****************************");
                printStream.println("*****************************");
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
