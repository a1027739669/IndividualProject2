package zyx;


import zyx.func.Step0;
import zyx.func.Step1;
import zyx.func.Step2;
import zyx.func.Step3;

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
    public static void main ( String[] args ) throws Exception
    { long start=System.currentTimeMillis();
      Step3.cout1(Paths.get("GoneWithTheWind.txt"),2);
      long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
