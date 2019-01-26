package com.personal.bigdata.hadoop.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class SaveFileMain {
    public static void main(String[] args) throws Exception {

        String localSrc = System.getProperty("user.home") + File.separator +  args[2];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", String.format("hdfs://%s:%s", args[0], args[1]));
        System.out.println("Connecting to -- " + conf.get("fs.defaultFS"));

        Path filePath = new Path(localSrc);
        FileSystem fs = FileSystem.get(URI.create(localSrc), conf);
        fs.mkdirs(filePath.getParent());
        OutputStream out = fs.create(filePath);

        IOUtils.copyBytes(in, out, 4096, true);

        in.close();
        out.close();
        System.out.println(localSrc + " copied to HDFS");
    }
}
