package zyx.func.functions;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: Individual_2
 * @Package: zyx.func.functions
 * @ClassName: Fun
 * @Description: java类作用描述
 * @Author: 章宇翔
 * @CreateDate: 2019/12/25 7:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/25 7:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Fun {
    private static int count = 0;

    public static List<HashMap.Entry<String, Integer>> countWord(String fileNamePath) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        StringBuilder content = new StringBuilder("");
        try {
            String code = resolveCode(fileNamePath);
            File file = new File(fileNamePath);
            InputStream is = new FileInputStream(fileNamePath);
            InputStreamReader isr = new InputStreamReader(is, code);
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            while (null != (str = br.readLine())) {
                content.append(str).append(" ");
            }
            br.close();
            Pattern p = Pattern.compile("[a-z][0-9a-z]*");
            String words = content.toString();
            Matcher matcher = p.matcher(words);
            int times = 0;
            while (matcher.find()) {
                String word = matcher.group();
                hashMap.merge(word, 1, Integer::sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("读取文件:" + fileNamePath + "失败!");
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
        return result;
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
