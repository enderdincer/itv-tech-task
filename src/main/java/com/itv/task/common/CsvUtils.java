package com.itv.task.common;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * CSV utilities
 *
 * @author enderdincer
 */
public final class CsvUtils {

  private static final char CSV_SEPARATOR = ',';

  private CsvUtils() {}

  public static <T> List<T> read(Class<T> tClass, String fileName) {

    try {
      Reader reader =
          new BufferedReader(new InputStreamReader(CsvUtils.class.getResourceAsStream(fileName)));

      MappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
      strategy.setType(tClass);

      CsvToBean<T> csvToBean =
          new CsvToBeanBuilder<T>(reader)
              .withType(tClass)
              .withSeparator(CSV_SEPARATOR)
              .withIgnoreLeadingWhiteSpace(true)
              .withIgnoreEmptyLine(true)
              .withMappingStrategy(strategy)
              .build();

      return csvToBean.parse();

    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public static List<String[]> readLines(String fileName) {
    Reader reader =
        new BufferedReader(new InputStreamReader(CsvUtils.class.getResourceAsStream(fileName)));

    try {

      return new CSVReader(reader).readAll();

    } catch (IOException | CsvException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public static void write(List<String[]> lines, String fileName) {

    try {
      File file = new File(fileName);

      if (!file.exists()) {
        file.createNewFile();
      }

      CSVWriter writer =
          new CSVWriter(
              new FileWriter(fileName),
              ICSVWriter.DEFAULT_SEPARATOR,
              ICSVWriter.NO_QUOTE_CHARACTER,
              ICSVWriter.NO_ESCAPE_CHARACTER,
              ICSVWriter.DEFAULT_LINE_END);

      for (String[] array : lines) {
        writer.writeNext(array);
      }

      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
