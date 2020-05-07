package test;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Made for testing
 */
public class Main {
    public static String toBinaryString(int i) {
        return toUnsignedString(i, 1);
    }

    /**
     * Convert the integer to an unsigned number.
     */
    private static String toUnsignedString(int i, int shift) {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[--charPos] = (char)((i & mask)+'0');
//            System.out.println(i&mask);
            i >>>= shift;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }
    public static String intToBinary(int n)
    {
        String s = "";
        while (n > 0)
        {
            s =  ( (n % 2 ) == 0 ? "0" : "1") +s;
            n = n / 2;
        }
        return s;
    }
    public static int binaryToInt (String binary){
        char []cA = binary.toCharArray();
        int result = 0;
        for (int i = cA.length-1;i>=0;i--){
            //111 , length = 3, i = 2, 2^(3-3) + 2^(3-2)
            //                    0           1
            if(cA[i]=='1') result+=Math.pow(2, cA.length-i-1);
        }
        return result;
    }
    public static void main(String[] args) {
        byte savedMazeBytes[] = {0,0,0,5,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,2,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0};
//        int k = binaryToInt("01000000010001000100000100000000");
//        System.out.println(k);
        int l = 68161559;
        String st = Integer.toBinaryString(-21766);
        BigInteger k = BigInteger.valueOf(-216);
        System.out.println(st);
        byte[] arr = k.toByteArray();
        byte[] check = {-1,-1,-86,-6};
        System.out.println(new BigInteger(check).intValue());
        System.out.println(Arrays.toString(arr));
//        byte b = Byte.valueOf(st, 10);
//        byte c = Byte.decode(st);
//        byte c = Byte.parseByte(st);
//        System.out.println(b);
//        System.out.println(c);




//        AMazeGenerator mazeGenerator = new MyMazeGenerator();
//        Maze maze = mazeGenerator.generate(10/*rows*/, 10/*columns*/);

    }
}
