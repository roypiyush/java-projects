package com.personal.serialization.avro;

import com.personal.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class AvroMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvroMain.class);

    public static void main(String[] args) throws Exception {
        InputStream resource = ClassLoader.getSystemResourceAsStream("avsc/user.avsc");
        Schema schema = new Schema.Parser().parse(resource);

        GenericRecord genericRecord1 = new GenericData.Record(schema);
        genericRecord1.put("name", "Alyssa");
        genericRecord1.put("favorite_number", 256);
        GenericRecord genericRecord2 = new GenericData.Record(schema);
        genericRecord2.put("name", "Ben");
        genericRecord2.put("favorite_number", 7);
        genericRecord2.put("favorite_color", "red");

        LOGGER.info("************Serialization**************");
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        User user2 = new User("Ben", 7, "red");
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();

        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        String outputFile = "target/newusers.avro";
        dataFileWriter.create(user1.getSchema(), new File(outputFile));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
        LOGGER.info("Done");

        LOGGER.info("************Deserialization************");
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(outputFile), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            LOGGER.info("User Object: {}", user);
        }
        dataFileReader.close();
    }
}
