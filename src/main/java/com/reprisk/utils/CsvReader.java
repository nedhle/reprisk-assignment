package com.reprisk.utils;

import com.reprisk.model.Company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvReader {
  public static Set<Company> readCompanies(String filePath) {
    InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(filePath);
    try {
      assert inputStream != null;
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

        return reader
            .lines()
            .skip(1)
            .map(
                line -> {
                  String[] parts = line.split((";"));
                  return Company.builder().id(Integer.valueOf(parts[0])).name(parts[1]).build();
                })
            .collect(Collectors.toSet());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
