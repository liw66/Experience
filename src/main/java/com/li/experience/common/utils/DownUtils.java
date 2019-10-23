package com.li.experience.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

/**
 * @program: experience
 * @description:多线程断点下载
 * @author: Liwei
 * @create: 2019-09-10 10:05
 **/
public class DownUtils {

    private static int threadCount = 10;

    private static int activeThread;

    public static void main(String[] args) throws Exception {
        String path = "https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2019.exe";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200) {
            int length = conn.getContentLength();
            String fileName = path.substring(path.lastIndexOf('/')+1);
            RandomAccessFile file = new RandomAccessFile(fileName, "rwd");
            file.setLength(length);
            file.close();
            int blockSize = length / threadCount;
            for (int threadId = 1; threadId <= threadCount; threadId++) {
                int startIndex = blockSize * (threadId - 1);
                int endIndex = blockSize * threadId - 1;
                if (threadId == threadCount) {
                    endIndex = length;
                }
                //System.out.println(MessageFormat.format("线程[{0}]开始下载:{1}--->{2}", threadId, startIndex, endIndex));
                new MyThread(path, threadId, startIndex, endIndex).start();
                activeThread++;
                System.out.println(MessageFormat.format("当前活动线程是：{0}", activeThread));
            }
        } else {
            System.out.println("服务器异常，下载失败");
        }
    }

    public static class MyThread extends Thread {
        private String path;
        private int threadId;
        private int startIndex;
        private int endIndex;
        public MyThread(String path, int threadId, int startIndex, int endIndex) {
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        @Override
        public void run() {
            try {
                File tempFile = new File(threadId + ".txt");
                if (tempFile.exists()) {
                    FileInputStream stream = new FileInputStream(tempFile);
                    byte[] temp = new byte[1024*1024];
                    int len = stream.read(temp);
                    int downIndex = Integer.parseInt(new String(temp, 0, len));
                    startIndex = downIndex;
                    stream.close();
                    //System.out.println(MessageFormat.format("线程[{0}]真实开始下载数据区间:{1}--->{2}", threadId, startIndex, endIndex));
                }
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "byte=" + startIndex + "-" + endIndex);
                int code = conn.getResponseCode();
                if (code == 206) {
                    InputStream stream = conn.getInputStream();
                    String fileName = path.substring(path.lastIndexOf('/')+1);
                    RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
                    raf.seek(startIndex);
                    int len = 0;
                    byte[] buffer = new byte[1024*1024];
                    int total = 0;
                    while ((len = stream.read(buffer)) != -1) {
                        RandomAccessFile file = new RandomAccessFile(threadId + ".txt", "rwd");
                        raf.write(buffer, 0, len);
                        total += len;
                        System.out.println(MessageFormat.format("线程[{0}]已下载数据:{1}", threadId, total + startIndex));
                        file.write((startIndex + total + "").getBytes());
                        file.close();
                    }
                    stream.close();
                    raf.close();
                    System.out.println(MessageFormat.format("线程[{0}]下载完毕", threadId));
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(MessageFormat.format("线程[{0}]下载出现异常", threadId));
            } finally {
                activeThread--;
                if (activeThread == 0) {
                    for (int i = 1; i <= threadCount; i++) {
                        File file = new File(i + ".txt");
                        file.delete();
                    }
                    System.out.println("下载完毕，已清除全部临时文件");
                }
            }
        }
    }
}

