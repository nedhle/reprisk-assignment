package com.reprisk.utils;

import java.util.regex.Pattern;

public class CompanyNameUtil {
  private static final Pattern LEGAL_TERMS_PATTERN =
      Pattern.compile("\\s*(Limited|Ltd\\.|Inc\\.|Corp\\.|LLC)\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern PARENTHESES_PATTERN = Pattern.compile("\\\\((.*?)\\\\)");
  private static final Pattern QUOTES_PATTERN = Pattern.compile("^\"|\"$");
  private static final Pattern BEGIN_PARENTHESES_PATTERN = Pattern.compile("^\\(|\\)$");

  /**
   * Cleans a company name by removing unwanted characters such as parentheses, quotes, legal terms,
   * and whitespace from the beginning and end of the name.
   *
   * @param companyName The company name to clean
   * @return The cleaned company name
   */
  public static String cleanCompanyName(String companyName) {
    if (companyName == null || companyName.isEmpty()) {
      return companyName;
    }

    companyName = companyName.trim();
    companyName = PARENTHESES_PATTERN.matcher(companyName).replaceAll("");
    companyName = BEGIN_PARENTHESES_PATTERN.matcher(companyName).replaceAll("");
    companyName = QUOTES_PATTERN.matcher(companyName).replaceAll("");
    companyName = LEGAL_TERMS_PATTERN.matcher(companyName).replaceAll("");

    return companyName.trim();
  }

  public static boolean isValidCompanyMention(String text, String company) {

    String pattern = "\\b" + Pattern.quote(company) + "\\b";
    return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(text).find();
  }
}
