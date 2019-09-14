package zyx;

import zyx.func.Step0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main ( String[] args ) throws Exception
    {
        long start=System.currentTimeMillis();
      Step0 step0=new Step0();
      step0.cout("GameOfThron.txt");
      long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
