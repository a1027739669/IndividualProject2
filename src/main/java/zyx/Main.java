package zyx;

import zyx.func.Step0;
import zyx.func.Step1;

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
      Step1 step1=new Step1();
      step1.cout1("GoneWithTheWind.txt");
      long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
