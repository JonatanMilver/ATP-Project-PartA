package IO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

//    /**
//     * Main function of MyDecompressorInputStream.
//     * Reads given input(this.in) data which is compressed
//     * decompresses the data and writes the decompressed data as bytes into byte array maze_bytes
//     * @param maze_bytes byte []
//     * @return int
//     * @throws IOException
//     */
//    @Override
//    public int read(byte[] maze_bytes) throws IOException {
//        //initialization
//        int total_length = 24;
//        for(int i=0;i<24;i++){
//            maze_bytes[i] = (byte)in.read();
//        }
//        //after initialization
//        //first entry is 0.
//        byte entry = 0;
//        //read input stream until it reaches the end(end is when next_byte is -1).
//        int next_byte = in.read();
//        while( next_byte != -1){
//            int nums_amount = 1;
//            while(nums_amount <= next_byte) {
//                maze_bytes[total_length] = entry;
//                total_length++;
//                nums_amount++;
//            }
//            //chooses between the entry 0/1
//            if(entry == 1)
//                entry = 0;
//            else entry = 1;
//            next_byte = in.read();
//        }
//        return 0;
//    }

    private int[] getIntArrFromBytes() throws IOException {
        //Array that will hold the sent data in ints(converted)
        int[] sent = new int[in.available()/4];
        int next_byte = in.read();
        int sent_index=0;
        //read input stream until it reaches the end(end is when next_byte is -1).
        while( next_byte != -1 ){
            //enter each 4 elements from the sent data into a byte array(inner for loop)
            byte[] temp = new byte[4];
            for(int i=0;i<temp.length;i++){
                temp[i] = (byte)next_byte;
                next_byte = in.read();
            }
            //Convert the 4 byte array into an integer and set the int into "sent".
            sent[sent_index] = new BigInteger(temp).intValue();
            sent_index++;
        }
        return sent;
    }

    private String convertIntegersToBinaryString(int[] sent){
        String binaryString = "";
        for(int num : sent){
            //for each num in "sent" convert it into a binaryString
            String sub_string="";
            String current_num_binary = Integer.toBinaryString(num);
            sub_string+= current_num_binary;
            //add zeros at the beginning of the string in order to hold 32 elements for the conversion.
            int zero_addition = 32 - current_num_binary.length();
            for(int i=0;i<zero_addition;i++){
                sub_string+= "0"+sub_string;
            }
            //concatenate all sub_strings into one big binary string
            binaryString+= sub_string;
        }
        return binaryString;
    }

    public int read(byte[] maze_bytes) throws IOException {
        //initialization
        int total_length = 24;
        for(int i=0;i<24;i++){
            maze_bytes[i] = (byte)in.read();
        }
        int rows = new BigInteger(Arrays.copyOfRange(maze_bytes, 0 , 4)).intValue();
        int columns = new BigInteger(Arrays.copyOfRange(maze_bytes, 4 , 8)).intValue();


//        byte[] decompressed = new byte[rows*columns];
        //Holds an array of integers converted from bytes.
        int[] sent = getIntArrFromBytes();
        //Converts the array "sent" of integers into one concatenated binary string
        String binaryString = convertIntegersToBinaryString(sent);
        //Writes the entrys into maze_bytes
        for(int i=24; i<rows*columns+24;i++){
            int numeric_val = Character.getNumericValue(binaryString.charAt(i));
            maze_bytes[i] = (byte)numeric_val;
        }
//        System.arraycopy(decompressed, 0 , maze_bytes, 24, decompressed.length);
        return 0;
        }



}


