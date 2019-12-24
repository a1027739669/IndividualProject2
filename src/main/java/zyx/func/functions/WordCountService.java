package zyx.func.functions;

import java.util.*;

public class WordCountService {

    private static CharTreeNode geneCharTree(String text) {
        CharTreeNode root = new CharTreeNode();
        CharTreeNode p = root;
        char c = ' ';
        for (int i = 0; i < text.length(); ++i) {
            c = text.charAt(i);
            if (c >= 'A' && c <= 'Z')
                c = (char) (c + 'a' - 'A');
            if (c >= 'a' && c <= 'z') {
                if (p.children[c - 'a'] == null)
                    p.children[c - 'a'] = new CharTreeNode();
                p = p.children[c - 'a'];
            } else {
                p.cnt++;
                p = root;
            }
        }
        if (c >= 'a' && c <= 'z')
            p.cnt++;
        return root;
    }


    private static void getWordCountFromCharTree(List result, CharTreeNode p, char[] buffer, int length) {
        for (int i = 0; i < 26; ++i) {
            if (p.children[i] != null) {
                buffer[length] = (char) (i + 'a');
                if (p.children[i].cnt > 0) {
                    WordCount wc = new WordCount();
                    wc.setCount(p.children[i].cnt);
                    wc.setWord(String.valueOf(buffer, 0, length + 1));
                    result.add(wc);
                }
                getWordCountFromCharTree(result, p.children[i], buffer, length + 1);
            }
        }
    }

    private static void getWordCountFromCharTree(List result, CharTreeNode p) {
        getWordCountFromCharTree(result, p, new char[500], 0);
    }


    public static List<WordCount> getWordCount(String article) {
        CharTreeNode root = geneCharTree(article);
        List<WordCount> result = new LinkedList<>();
        getWordCountFromCharTree(result, root);
        Collections.sort(result, new Comparator<WordCount>() {
            @Override
            public int compare(WordCount o1, WordCount o2) {
                if (o1.getCount() != o2.getCount()) {
                    return o2.getCount() - o1.getCount();
                } else {

                    return o1.getWord().compareTo(o2.getWord());
                }

            }
        });
        return result;
    }
    public static List<WordCount> getWordCount(String article,int n) {
        CharTreeNode root = geneCharTree(article);
        List<WordCount> result = new LinkedList<>();
        getWordCountFromCharTree(result, root);
        Collections.sort(result, new Comparator<WordCount>() {
            @Override
            public int compare(WordCount o1, WordCount o2) {
                if (o1.getCount() != o2.getCount()) {
                    return o2.getCount() - o1.getCount();
                } else {

                    return o1.getWord().compareTo(o2.getWord());
                }

            }
        });
        return result;
    }
}

