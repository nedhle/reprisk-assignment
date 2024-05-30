package com.reprisk.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ResultReporter {
  public static void reportResults(Map<String, Set<String>> companyMentions) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
      for (Map.Entry<String, Set<String>> entry : companyMentions.entrySet()) {
        String company = entry.getKey();
        Set<String> articles = entry.getValue();
        writer.write("Company: " + company);
        writer.newLine();
        writer.write("Mentioned in Articles:");
        writer.newLine();
        for (String article : articles) {
          writer.write(" - " + article);
          writer.newLine();
        }
        writer.newLine();
        writer.write("############################################");
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
