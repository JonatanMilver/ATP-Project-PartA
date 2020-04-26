package IO;


import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int i) throws IOException {

    }

//    public void write(byte[] b) throws IOException {
//        boolean countingZeros = true;
//        int count = 0;
//
//        int numOfRows = byteArrayToBigInt(new byte[]{b[0],b[1],b[2],b[3]});
//        int numOfCols = byteArrayToBigInt(new byte[]{b[4],b[5],b[6],b[7]});
//        byte[] ret = new byte[24+(numOfRows*numOfCols)];
//
//        //coping the first 24 bytes as is
//        System.arraycopy(b, 0, ret, 0, 24);
//
//        int index = 24;
//
//        for (int i = 24; i < b.length; i++) {
//            byte x = b[i];
//            if (countingZeros) {
//                if (x == 0) {
//                    if (count == 255){
//                        if (b[i+1] == 0){
//                            ret[index] = (byte) count;
//                            index++;
//                            count = 0;
//                            ret[index] = (byte) count;
//                            count = 1;
//                            index++;
//                        }
//                        else{
//                            ret[index] = (byte) count;
//                            index++;
//                            count = 1;
//                            countingZeros = false;
//                            continue;
//                        }
//                    }
//                    else{
//                        count++;
//                    }
//                }
//                else {
//                    ret[index] = (byte) count;
//                    index++;
//                    count = 1;
//                    countingZeros = false;
//                    continue;
//                }
//            }
//
//            if (!countingZeros) {
//                if (x == 1) {
//                    if (count == 255){
//                        if (b[i+1] == 1){
//                            ret[index] = (byte) count;
//                            index++;
//                            count = 0;
//                            ret[index] = (byte) count;
//                            count = 1;
//                            index++;
//                        }
//                        else{
//                            ret[index] = (byte) count;
//                            index++;
//                            count = 1;
//                            countingZeros = true;
//                        }
//                    }
//                    else {
//                        count++;
//                    }
//                } else {
//                    ret[index] = (byte) count;
//                    index++;
//                    count = 1;
//                    countingZeros = true;
//                }
//            }
//        }
//        ret[index] = (byte) count;
//
//        int endingZeros = 0;
//        for (int i = ret.length-1 ; i > 0 ; i--){
//            if (ret[i] == 0){
//                endingZeros++;
//                continue;
//            }
//            break;
//        }
//
//        byte[] returningArray = new byte[ret.length-endingZeros];
//
//        //removing the redundant zeros at the end of the array
//        System.arraycopy(ret, 0, returningArray, 0, returningArray.length);
////        System.out.println(Arrays.toString(returningArray));
//
//
//        out.write(returningArray);
//    }

    public void write(byte[] b) throws IOException {
        //Will hold all 0/1
        byte[] tmp = new byte[b.length-24];
        System.arraycopy(b,24,tmp,0,tmp.length);

        int amount_of_ints = 0; //Amount of ints would be held in the int array(after conversion from binary)
        byte[] copy_of_bytes;

        if(tmp.length%32 != 0) {
            amount_of_ints = tmp.length / 32 + 1; //we add one in case we'll have to add another number
            copy_of_bytes= new byte[tmp.length + (32 - tmp.length%32)];
        }
        else{
            amount_of_ints = tmp.length/32;
            copy_of_bytes = new byte[tmp.length];
        }

        //coping the entire array to another array of suitable size
        System.arraycopy(tmp, 0, copy_of_bytes, 0, tmp.length);


        //The array that will hold the integers
        int[] arr_of_converted_ints = buildConvertedInts(copy_of_bytes , amount_of_ints);

        //Convert the ints to byte arrays and get the bytes ready to write.
        byte[] to_send = buildSendingArray(arr_of_converted_ints);
        byte[] ret = new byte[24+to_send.length];
        //Copy the first 24 elements showing maze's details.
        System.arraycopy(b, 0, ret, 0, 24);
        int to_send_index = 0 ;
        //Copy the rest of maze's content to "ret"
        System.arraycopy(to_send, 0, ret, 24, to_send.length);
        out.write(ret);
    }

    private byte[] buildSendingArray(int[] arr_of_converted_ints) {

        //An array of byte arrays that each entry will hold an expression of integer in bytes after the compression.
        byte[][] comp = buildComp(arr_of_converted_ints);

        //The byte array that will holds the data.
        byte[] to_send = new byte[4*arr_of_converted_ints.length];

        int current_index = 0; //holds the current place at to_send array.
        int i=0;
        while(current_index<to_send.length){

            for(int j = 0; j < comp[i].length ; j++){
                to_send[current_index] = comp[i][j];

                current_index++;
            }
            i++;
        }

        return to_send;
    }
    private byte[][] buildComp(int[] arr_of_converted_ints) {
        byte[][] comp = new byte[arr_of_converted_ints.length][4];


        for(int i=0;i<arr_of_converted_ints.length;i++){
            BigInteger n = BigInteger.valueOf(arr_of_converted_ints[i]);
            int j=0;
            byte[] tmp = n.toByteArray(); //convert each int to byte array
            int cell_size = 4 - tmp.length;

            //add zeros if necessary(if the int don't need 4 bytes)
            if(cell_size > 0){
                byte entry = 0;
                if(arr_of_converted_ints[i] < 0){
                    entry = -1;
                }
                for(int r = 0 ; r<cell_size ; r++){
                    comp[i][r] = entry;
                    j++;
                }
            }

            //add the int to the array holding the compressed data
            for(byte k: tmp){
                comp[i][j] = k;
                j++;
            }
        }
        return comp;
    }
    private int[] buildConvertedInts(byte[] copy_of_bytes, int amount_of_ints) {
        int[] arr_of_converted_ints = new int[amount_of_ints];

        //The index at the integers array that we are going to add an integer.
        int current_index = 0;

        //Convert each 32 elements into an integer.
        for(int i=0; i <= copy_of_bytes.length-32; i+=32){

            //First we convert it to string.
            StringBuilder s = buildStringBuilder(copy_of_bytes , i, i);

            //then we convert the string into an integer
            arr_of_converted_ints[current_index] = new BigInteger(s.toString(), 2).intValue();
            current_index++;
        }
        return arr_of_converted_ints;
    }
    private StringBuilder buildStringBuilder(byte[] copy_of_bytes, int i, int j) {
        StringBuilder s = new StringBuilder();
        while(j<i+32){
            s.append(copy_of_bytes[j]);
            j++;
        }
        return s;
    }


    public byte[] bigIntToByteArray(int i) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }

    public int byteArrayToBigInt(byte[] bytes) {
        return new BigInteger(bytes).intValue();
    }

}
