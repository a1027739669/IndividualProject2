package zyx.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step2 {
   public static void countWordsWithStopWords(Path stopwordspath,Path filepath) throws IOException {
       List<String> list= Files.readAllLines(stopwordspath);
       List<HashMap.Entry<String,Integer>> hashMap=Step1.countWords(filepath);
       for(String word:list){
           for(int i=0;i<hashMap.size();i++){
               if(hashMap.get(i).getKey().equalsIgnoreCase(word)){
                   hashMap.remove(i);
               }
           }
       }
       int count=0;
       for (HashMap.Entry<String, Integer> entry : hashMap) {
           count+=entry.getValue();
       }
       System.out.println("去掉停词表中的单词后，单词分布频率如下:");
       for (HashMap.Entry<String, Integer> entry : hashMap) {
           System.out.printf("单词:%s 出现次数:%d 频率:%.2f%%\n", entry.getKey(), entry.getValue(),(float)entry.getValue()*100/count);
       }
   }
}
