package zyx.func.functions;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step1 {


    public static List<Map.Entry<String, Integer>> countWords(Path filePath) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> list = Files.readAllLines(filePath, Charset.forName("GBK"));
        Pattern pattern = Pattern.compile("[a-z][0-9a-z]*");
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

    public static List<Map.Entry<String, Integer>> countWords(Path filePath,Path verPath) throws IOException {
        List<List<String>> verbs= Step4.change(verPath);
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> list = Files.readAllLines(filePath,Charset.forName("GBK"));
        Pattern pattern = Pattern.compile("[a-z][0-9a-z]*");
        for (String s : list) {
            Matcher matcher = pattern.matcher(s.toLowerCase());
            while (matcher.find()) {
                String temp = matcher.group();
                for(List<String> verb:verbs){
                    if(verb.contains(temp))
                    {
                        temp=verb.get(0);
                    }
                }
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

    public static void countWordsDirectory(String direcPath,PrintStream printStream) throws IOException {
        Files.walkFileTree(Paths.get(direcPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println("*****************************");
//                System.out.println("正在访问文件:" + file.getFileName());
                printStream.println("*****************************");
                printStream.println("正在访问文件:" + file.getFileName());
                Step1.cout1(Step1.countWords(file.toAbsolutePath()),printStream);
//                System.out.println("*****************************");
                printStream.println("*****************************");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void countWordsDirectory(String direcPath,int n,PrintStream printStream) throws IOException {
        Files.walkFileTree(Paths.get(direcPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println("*****************************");
//                System.out.println("正在访问文件:" + file.getFileName());
                printStream.println("*****************************");
                printStream.println("正在访问文件:" + file.getFileName());
                Step1.cout1(file.toAbsolutePath(),n,printStream);
//                System.out.println("*****************************");
                printStream.println("*****************************");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void countWordsDirectoryAndChild(String direcPath,PrintStream printStream) throws IOException {
        Files.walkFileTree(Paths.get(direcPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.err.println("***********************************************************************");
//                System.out.println("正在访问文件:" + file.getFileName());
                printStream.println("*****************************");
                printStream.println("正在访问文件:" + file.getFileName());
                Step1.cout1(Step1.countWords(file.toAbsolutePath()),printStream);
//                System.err.println("***********************************************************************");
                printStream.println("*****************************");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
    public static void countWordsDirectoryAndChild(String direcPath,int n,PrintStream printStream) throws IOException {
        Files.walkFileTree(Paths.get(direcPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.err.println("***********************************************************************");
//                System.out.println("正在访问文件:" + file.getFileName());
                printStream.println("*****************************");
                printStream.println("正在访问文件:" + file.getFileName());
                Step1.cout1(file.toAbsolutePath(),n,printStream);
//                System.err.println("***********************************************************************");
                printStream.println("*****************************");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }


    public static void cout1(List<HashMap.Entry<String,Integer>> list,PrintStream printStream) throws IOException {
        int count=0;
        for (HashMap.Entry<String, Integer> entry : list) {
            count+=entry.getValue();
        }

        for(int i=0;i<list.size();i++){
            HashMap.Entry<String,Integer> entry=list.get(i);
//       System.out.printf("单词:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count);
         printStream.println(String.format("单词:%s 出现次数:%d 频率:%.2f%%", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count));
        }
//        printStream.close();
    }

    public static void cout1(Path filePath, int n,PrintStream printStream) throws IOException {
        List<HashMap.Entry<String, Integer>> list = Step1.countWords(filePath);
        int count=0;
        for (HashMap.Entry<String, Integer> entry : list) {
            count+=entry.getValue();
        }
        if (n <= 0) {
            System.out.println("n的指应当大于0\n");
        } else {
//            System.out.printf("出现频率前%d的单词为:\n",n);
            printStream.println(String.format("出现频率前%d的单词为:",n));
            for(int i=0;i<n&&i<list.size();i++){
                HashMap.Entry<String,Integer> entry=list.get(i);
//                System.out.printf("单词:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count);
                printStream.println(String.format("单词:%s 出现次数:%d 频率:%.2f%%", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count));
            }
        }
    }


}

