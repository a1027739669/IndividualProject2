package zyx.func;


import zyx.func.functions.Step0;
import zyx.func.functions.Step1;
import zyx.func.functions.Step2;
import zyx.func.functions.Step3;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> params = new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String temp = args[i];
            if (temp.length() == 2 && temp.startsWith("-"))
                params.add(temp);
            else
                filePaths.add(temp);
        }
        if (params.size() == 0) {
            System.out.println("必须有参数");
        }
        if (filePaths.size() == 0) {
            System.out.println("必须输入文件路径");
        }
        if (params.size() == 1) {
            if (params.get(0).equals("-c")) {
                long start = System.currentTimeMillis();
                Step0 step0 = new Step0();
                step0.cout(filePaths.get(0));
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-f")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.cout1(Step1.countWords(Paths.get(filePaths.get(0))), printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-d")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.countWordsDirectory(filePaths.get(0), printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-p")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step3.cout1(Step3.countPhrase(Paths.get(filePaths.get(1)), Integer.parseInt(filePaths.get(0))), Integer.parseInt(filePaths.get(0)),printStream);
//                Step3.countPhraseWithDirec(filePaths.get(1),Integer.parseInt(filePaths.get(0)));
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
        }
        if (params.size() == 2) {
            if (params.get(0).equals("-d") && params.get(1).equals("-s")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.countWordsDirectoryAndChild(filePaths.get(0),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-p") && params.get(1).equals("-d")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step3.countPhraseWithDirec(filePaths.get(1), Integer.parseInt(filePaths.get(0)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-f") && params.get(1).equals("-n")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.cout1(Paths.get(filePaths.get(1)), Integer.parseInt(filePaths.get(0)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-f") && params.get(1).equals("-v")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.cout1(Step1.countWords(Paths.get(filePaths.get(1)), Paths.get(filePaths.get(0))),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-d") && params.get(1).equals("-n")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.countWordsDirectory(filePaths.get(1), Integer.parseInt(filePaths.get(0)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-x") && params.get(1).equals("-f")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step2.countWordsWithStopWords(Paths.get(filePaths.get(0)), Paths.get(filePaths.get(1)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-x") && params.get(1).equals("-d")) {
                long start = System.currentTimeMillis();
//                Step2.countWordsWithStopWords(Paths.get(filePaths.get(0)), Paths.get(filePaths.get(1)));
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step2.countWordsWithStopWordsAndDirec(Paths.get(filePaths.get(0)), Paths.get(filePaths.get(1)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
            if (params.get(0).equals("-p") && params.get(1).equals("-v")) {
                long start = System.currentTimeMillis();
                Step3.countPhrase(Paths.get(filePaths.get(2)), Integer.parseInt(filePaths.get(0)), Paths.get(filePaths.get(1)));
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
        }
        if (params.size() == 3) {
            if (params.get(0).equals("-d") && params.get(1).equals("-s") && params.get(2).equals("-n")) {
                long start = System.currentTimeMillis();
                PrintStream printStream = new PrintStream(new File("result.txt"));
                Step1.countWordsDirectoryAndChild(filePaths.get(1), Integer.parseInt(filePaths.get(0)),printStream);
                printStream.close();
                long end = System.currentTimeMillis();
                System.out.println((end - start) / (float) 1000 + " " + "s");
            }
        }
    }
}
