package zyx.func.functions;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Step0 {
    private int[] letters = new int[26];
    private float[] rate = new float[26];

    public int[] countLetter(String filepath) throws IOException {
        List<String> lists = Files.readAllLines(Paths.get(filepath));
        int[] letters = new int[26];
        int count = 0;
        for (String list : lists) {
            for (int i = 0; i < list.length(); i++) {
                char c = Character.toUpperCase(list.charAt(i));
                if (Character.isLetter(c)) {
                    letters[c - 'A']++;
                }
            }
        }
        return letters;
    }

    public float[] countRate(int[] le) {
        float[] ra = new float[26];
        int count = 0;
        for (int i = 0; i < le.length; i++) {
            count += le[i];
        }
        for (int i = 0; i < ra.length; i++) {
            ra[i] = (float) le[i] / count;
        }
        return ra;
    }

    public void cout(String filepath) throws IOException {
        this.letters = this.countLetter(filepath);
        this.rate = this.countRate(this.letters);
        boolean[] flag = new boolean[26];
        for (int i = 0; i < 26; i++) {
            int position = 0;
            int max=-1;
            for (int j = 0; j < 26; j++) {
                if ((this.letters[j] > max) && !flag[j]) {
                    max=this.letters[j];
                    position = j;
                }
            }
            if (!flag[position]) {
                System.out.printf("%c:频率:%f 百分比:%.2f%% 频数:%d\n", (char) (position + 'A'), this.rate[position], this.rate[position] * 100,this.letters[position]);
                flag[position] = true;
            }
        }
    }
}