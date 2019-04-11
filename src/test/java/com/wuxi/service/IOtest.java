package com.wuxi.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class IOtest {

    @Test
    public void read() {
        File file = new File("/Users/qingtong/Desktop/sync.txt");
        try {
            FileReader reader = new FileReader(file);
            StringBuilder tmp = new StringBuilder();
            char[] tmps = new char[1024];
            int len = -1;
            while ((len = reader.read(tmps)) != -1) {
                System.out.println(len);
                tmp.append(new String(tmps, 0, len));
            }

            System.out.println(tmp.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Test
    public void write() {
        File file = new File("/Users/qingtong/Desktop/syncc.txt");

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < 10; i++) {
                writer.write("中");
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//	@Test
//	public void asyncIO(){
//		Path file = Paths.get("/Users/dasouche/Desktop/sync_58.txt");
//		try {
//			AsynchronousFileChannel channel = AsynchronousFileChannel.open(file,StandardOpenOption.READ);
//			ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
//			Future<Integer> result = channel.read(buffer, 0);
//			//do something
//			while(!result.isDone()){
//				System.out.println("read...");
//			}
//			Thread.sleep(1000);
//			Integer bytesRead = result.get();
//			byte[] data = new byte[bytesRead];
//			buffer.get(data);
// 			System.out.println("byte read " + bytesRead + ", " + new String(data));
//		} catch (IOException | InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//	}

}
