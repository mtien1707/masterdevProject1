package com.cuminhtien;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.util.Utf8;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        user u1 = new user();
        mailing_address ma1 = new mailing_address();
        mailing_address ma2 = new mailing_address();
        user u2 = new user();
        u1.setUsername("cuminhtien");
        u1.setAge(18);
        u1.setPhone("0989898989");
        u1.setHousenum("123");
        ma1.setCity("HCM");
        ma1.setStreet("PVD");
        u1.setAddress(ma1);


        u2.setUsername("minhtien");
        u2.setAge(18);
        u2.setPhone("0989898989");
        u2.setHousenum("123");
        ma2.setStreet("PVD") ;
        ma2.setCity("HN");
        u2.setAddress(ma2);
        //Serialize
        File avroOutput = new File("user-test.avro");
        try {
            DatumWriter<user> DatumWriter = new SpecificDatumWriter<user>(user.class);
            DataFileWriter<user> dataFileWriter = new DataFileWriter<user>(DatumWriter);
            dataFileWriter.create(u1.getSchema(), avroOutput);

            dataFileWriter.append(u1);
            dataFileWriter.append(u2);
            dataFileWriter.close();
        } catch (IOException e) {System.out.println("Error writing Avro");}
        //Deserialize sample avro file
        try {
            DatumReader<user> bdPersonDatumReader = new SpecificDatumReader(user.class);
            DataFileReader<user> dataFileReader = new DataFileReader<user>(avroOutput, bdPersonDatumReader);
            user u = null;
            while(dataFileReader.hasNext()){
                u = dataFileReader.next(u);
                System.out.println(u);
            }
        } catch(IOException e) {System.out.println("Error reading Avro");}

    }
}