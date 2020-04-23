package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int i) throws IOException {

    }

    public void write(byte[] b) throws IOException {
        boolean countingZeros = true;
        int count = 0;

        int numOfRows = byteArrayToBigInt(new byte[]{b[0],b[1],b[2],b[3]});
        int numOfCols = byteArrayToBigInt(new byte[]{b[4],b[5],b[6],b[7]});
        byte[] ret = new byte[24+(numOfRows*numOfCols)];

        //coping the first 24 bytes as is
        System.arraycopy(b, 0, ret, 0, 24);

        int index = 24;

        for (int i = 24; i < b.length; i++) {
            byte x = b[i];
            if (countingZeros) {
                if (x == 0) {
                    if (count == 255){
                        if (b[i+1] == 0){
                            ret[index] = (byte) count;
                            index++;
                            count = 0;
                            ret[index] = (byte) count;
                            count = 1;
                            index++;
                        }
                        else{
                            ret[index] = (byte) count;
                            index++;
                            count = 1;
                        }
                    }
                    else{
                        count++;
                    }
                }
                else {
                    ret[index] = (byte) count;
                    index++;
                    count = 1;
                    countingZeros = false;
                    continue;
                }
            }

            if (!countingZeros) {
                if (x == 1) {
                    if (count == 255){
                        if (b[i+1] == 1){
                            ret[index] = (byte) count;
                            index++;
                            count = 0;
                            ret[index] = (byte) count;
                            count = 1;
                            index++;
                        }
                        else{
                            ret[index] = (byte) count;
                            index++;
                            count = 1;
                        }
                    }
                    else {
                        count++;
                    }
                } else {
                    ret[index] = (byte) count;
                    index++;
                    count = 1;
                    countingZeros = true;
                }
            }
        }
        ret[index] = (byte) count;

        int endingZeros = 0;
        for (int i = ret.length-1 ; i > 0 ; i--){
            if (ret[i] == 0){
                endingZeros++;
                continue;
            }
            break;
        }

        byte[] returningArray = new byte[ret.length-endingZeros];

        //removing the redundant zeros at the end of the array
        System.arraycopy(ret, 0, returningArray, 0, returningArray.length);


        out.write(returningArray);
    }


    public byte[] bigIntToByteArray(int i) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }

    public static int byteArrayToBigInt(byte[] bytes) {
        return new BigInteger(bytes).intValue();
    }


    public static void main(String[] args) throws IOException {

        byte[] arr = {0,0,0,(byte) 139};
        int temp = byteArrayToBigInt(arr);
        System.out.println(temp);
//        MyCompressorOutputStream s = new MyCompressorOutputStream(out);
//        s.write(arr);
    }
}
