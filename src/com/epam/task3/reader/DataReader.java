package com.epam.task3.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    private static Logger logger = LogManager.getLogger(DataReader.class);

    public List<String> readData(String fileName){
        List<String> dataList;
        File dataFile = new File(fileName);
        logger.debug("Reading file");

        try(BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
                dataList = br.lines().collect(Collectors.toList());
        } catch(IOException ioException){
            logger.fatal("File was not found");
            throw new RuntimeException(ioException);
        }
        logger.debug("File is read");
        return dataList;
    }
}
