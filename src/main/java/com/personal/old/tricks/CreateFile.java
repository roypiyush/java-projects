package com.personal.old.tricks;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;

import java.io.*;

public class CreateFile {

    private static final void write(String src, String dst) throws IOException {
        System.out.printf("Written by Thread %s\n", Thread.currentThread().getName());
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        IOUtils.copy(in, out, 1024);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    public static void main(String[] args) {
        final File lockFile = new File(new Path(args[1]).getParent().toUri().toString() + "/newfile");
        int size = 6;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                try {
                    boolean created = lockFile.createNewFile();
                    System.out.printf("Thread %s Created %s\n", Thread.currentThread().getName(), created);
                    if (created) write(args[0], args[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threads[i].setName("Thread " + (i + 1));
        }
        for (int i = 0; i < size; i++) {
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lockFile.delete();
        new File(args[1]).delete();
    }
}
