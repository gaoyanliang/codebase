package io.yanliang.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * NIO 快速入门
 *
 * @author yanliang
 */
public class NioDemo {

    public static void main(String[] args) throws Exception {
        transferFromDemo();
        transferToDemo();
        demo();
    }

    /**
     * transferTo()：把源通道的数据传输到目的通道中。
     *
     * @throws Exception
     */
    public static void transferToDemo() throws Exception {
        //获取文件输入流
        File file = new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();
        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/2.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();
        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //把输入流通道的数据读取到输出流的通道
        inputStreamChannel.transferTo(0, byteBuffer.limit(), outputStreamChannel);
        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }

    /**
     * transferFrom()：把来自源通道的数据传输到目的通道。
     *
     * @throws Exception
     */
    public static void transferFromDemo() throws Exception {
        //获取文件输入流
        File file = new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();
        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/2.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();
        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //把输入流通道的数据读取到输出流的通道
        outputStreamChannel.transferFrom(inputStreamChannel,0,byteBuffer.limit());
        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }


    /**
     * 分散读取和聚合写入
     *
     * 循环了两次。第一次循环时，三个缓冲区都读取了5个字节，总共读取了15，也就是读满了。还剩下11个字节，于是第二次循环时，前两个缓冲区分配了5个字节，最后一个缓冲区给他分配了1个字节，刚好读完。总共就是26个字节。
     *
     * 使用场景: 可以使用一个缓冲区数组，自动地根据需要去分配缓冲区的大小。可以减少内存消耗。网络IO也可以使用.
     *
     * @throws Exception
     */
    public static void demo() throws Exception {
        //获取文件输入流
        File file = new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/3.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/gaoyanliang/github/codebase/netty/src/main/java/io/yanliang/nio/4.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建三个缓冲区，分别都是5
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(5);
        //创建一个缓冲区数组
        ByteBuffer[] buffers = new ByteBuffer[]{byteBuffer1, byteBuffer2, byteBuffer3};

        //循环写入到buffers缓冲区数组中，分散读取
        long read;
        long sumLength = 0;
        while ((read = inputStreamChannel.read(buffers)) != -1) {
            sumLength += read;
            Arrays.stream(buffers)
                    .map(buffer -> "posstion=" + buffer.position() + ",limit=" + buffer.limit())
                    .forEach(System.out::println);
            //切换模式
            Arrays.stream(buffers).forEach(Buffer::flip);
            //聚合写入到文件输出通道
            outputStreamChannel.write(buffers);
            //清空缓冲区
            Arrays.stream(buffers).forEach(Buffer::clear);
        }
        System.out.println("总长度:" + sumLength);
        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();

    }


}
