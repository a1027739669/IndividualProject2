package zyx;


import zyx.func.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main ( String[] args ) throws Exception{
    long start=System.currentTimeMillis();
     Step3.cout1(Step3.countPhrase(Paths.get("GameOfThron.txt"),2,Paths.get("verbs.txt")),2);
      long end=System.currentTimeMillis();
        System.out.println(end-start);

    }
}
