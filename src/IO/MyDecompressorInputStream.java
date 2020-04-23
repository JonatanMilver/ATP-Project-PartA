package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * Main function of MyDecompressorInputStream.
     * Reads given input(this.in) data which is compressed
     * decompresses the data and writes the decompressed data as bytes into byte array maze_bytes
     * @param maze_bytes byte []
     * @return int
     * @throws IOException
     */
    @Override
    public int read(byte[] maze_bytes) throws IOException {
        //initialization
        int total_length = 0;
        for(int i=0;i<24;i++){
            maze_bytes[i] = (byte)in.read();
        }
        //after initialization
        //first entry is 0.
        byte entry = 0;
        //read input stream until it reaches the end(end is when next_byte is -1).
        int next_byte = in.read();
        while( next_byte != -1){
            int nums_amount = 1;
            while(nums_amount <= next_byte) {
                maze_bytes[total_length] = entry;
                nums_amount++;
            }
            //chooses between the entry 0/1
            if(entry == 1)
                entry = 0;
            else entry = 1;
            next_byte = in.read();
        }
        return 0;
    }

}


