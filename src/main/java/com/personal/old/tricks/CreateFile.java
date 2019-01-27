package com.personal.old.tricks;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CreateFile {

    private static final void write(String src, String dst) throws IOException {
        System.out.printf("Written by Thread %s\n",
                Thread.currentThread().getName());
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        IOUtils.copy(in, out, 1024);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    public static void main(String[] args) {
        String src = args[0];
        String dst = args[1];
        final File lockFile =
                new File(new Path(dst).getParent().toUri().toString() +
                        "/newfile");
        int size = 6;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                try {
                    boolean created = lockFile.createNewFile();
                    System.out.printf("Thread %s Created %s\n", Thread.currentThread().getName(), created);
                    if (created) write(src, dst);
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
        new File(dst).delete();
    }
}
