package zyx.func;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import zyx.func.functions.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@ShellComponent
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @ShellMethod(value = "countWord", key = "wf.exe -f")
    public void countWord(String path1, String path2) throws Exception {
        long start = System.currentTimeMillis();
        FileChannel print = new FileOutputStream(new File(path2)).getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        List<WordCount> list = WordCountService.getWordCount(readTxt(path1));
        String data;
        for (int i = 0; i < list.size(); i++) {
            data = list.get(i).getWord() + " " + list.get(i).getCount() + "\n";
            buf.put(data.getBytes());
            buf.flip();
            print.write(buf);
            buf.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / (float) 1000);
        data = (end - start) / (float) 1000 + " " + "s";
        buf.put(data.getBytes());
        buf.flip();
        print.write(buf);
        buf.clear();
    }

    @ShellMethod(value = "countDirectory", key = "wf.exe -d")
    public void countDirectory(String path1, String path2, @ShellOption(defaultValue = "-1") int n) throws Exception {
        long start = System.currentTimeMillis();
        FileChannel print = new FileOutputStream(new File(path2)).getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String ans = "";
        if (n == -1) {
            Files.walkFileTree(Paths.get(path1), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String temp = "*****************************\n正在访问文件:" + file.getFileName() + "\n";
                    buf.put(temp.getBytes());
                    buf.flip();
                    print.write(buf);
                    buf.clear();
                    List<WordCount> list = WordCountService.getWordCount(readTxt(file.toAbsolutePath().toString()));
                    for (int i = 0; i < list.size(); i++) {
                        temp = list.get(i).getWord() + " " + list.get(i).getCount() + "\n";
                        buf.put(temp.getBytes());
                        buf.flip();
                        print.write(buf);
                        buf.clear();
                    }
                    temp = "*****************************";
                    buf.put(temp.getBytes());
                    buf.flip();
                    print.write(buf);
                    buf.clear();
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            Files.walkFileTree(Paths.get(path1), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String temp = "*****************************\n正在访问文件:" + file.getFileName() + "\n";
                    buf.put(temp.getBytes());
                    buf.flip();
                    print.write(buf);
                    buf.clear();
                    List<WordCount> list = WordCountService.getWordCount(readTxt(file.toAbsolutePath().toString()));
                    for (int i = 0; i < Math.min(list.size(), n); i++) {
                        temp = list.get(i).getWord() + " " + list.get(i).getCount() + "\n";
                        buf.put(temp.getBytes());
                        buf.flip();
                        print.write(buf);
                        buf.clear();
                    }
                    temp = "*****************************";
                    buf.put(temp.getBytes());
                    buf.flip();
                    print.write(buf);
                    buf.clear();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / (float) 1000);
        ans = "\n" + (end - start) / (float) 1000 + " " + "s" + "\n";
        buf.put(ans.getBytes());
        buf.flip();
        print.write(buf);
        buf.clear();


    }

    @ShellMethod(value = "stopWord", key = "wf.exe")
    public void countWithStop(String x, String path1, String f, String path2, String path3) throws IOException {
        long start = System.currentTimeMillis();
        FileChannel print = new FileOutputStream(new File(path3)).getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        List<String> stopList = Arrays.asList(readTxt(path1).split(" "));
        List<WordCount> list = WordCountService.getWordCount(readTxt(path2));
        String data;
        for (int i = 0; i < list.size(); i++) {
            data = list.get(i).getWord();
            int flag = 1;
            for (int j = 0; j < stopList.size(); j++) {
                if (stopList.get(j).equals(data)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                data = list.get(i).getWord() + " " + list.get(i).getCount() + "\n";
                buf.put(data.getBytes());
                buf.flip();
                print.write(buf);
                buf.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / (float) 1000);
        data = (end - start) / (float) 1000 + " " + "s";
        buf.put(data.getBytes());
        buf.flip();
        print.write(buf);
        buf.clear();
    }

    @ShellMethod(value = "phrase", key = "wf.exe -p")
    public void countPhrase(int n,String path1, String path2) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String article = readTxt(path1);
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            regex.append("[a-z][0-9a-z]*\\s");
        }
        regex.append("[a-z][0-9a-z]*");
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(article);
        while (matcher.find()) {
            String temp = matcher.group();
            hashMap.merge(temp, 1, Integer::sum);
        }

        List<HashMap.Entry<String, Integer>> result = new ArrayList<>(hashMap.entrySet());
        result.sort(new Comparator<HashMap.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (!o1.getValue().equals(o2.getValue())) {
                    return -o1.getValue().compareTo(o2.getValue());
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });


        long start = System.currentTimeMillis();
        FileChannel print = new FileOutputStream(new File(path2)).getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String data;
        for (int i = 0; i < result.size(); i++) {
            Map.Entry<String,Integer> temp=result.get(i);
                data = temp.getKey() + " " + temp.getValue() + "\n";
                buf.put(data.getBytes());
                buf.flip();
                print.write(buf);
                buf.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / (float) 1000);
        data = (end - start) / (float) 1000 + " " + "s";
        buf.put(data.getBytes());
        buf.flip();
        print.write(buf);
        buf.clear();
    }

    public static String readTxt(String path) {
        StringBuilder content = new StringBuilder("");
        try {
            String code = resolveCode(path);
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, code);
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            while (null != (str = br.readLine())) {
                content.append(str + " ");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("读取文件:" + path + "失败!");
        }
        return content.toString();
    }

    public static String resolveCode(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "gb2312";  //或GBK
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        else if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";

        inputStream.close();

        return code;
    }
}
