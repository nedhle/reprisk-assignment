package com.reprisk.utils;

import com.reprisk.model.Article;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {
  public static Set<Article> getXmlFiles(String filePath) {
    Set<Article> articles = new HashSet<>();

    List<File> xmlFiles = getXMLFiles(filePath);

    for (File file : xmlFiles) {
      try {
        articles.add(readXMLFileWithInputStream(file));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return articles;
  }

  private static List<File> getXMLFiles(String folderPath) {
    List<File> xmlFiles = new ArrayList<>();
    File folder = new File(folderPath);
    if (folder.exists() && folder.isDirectory()) {
      File[] files = folder.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
            xmlFiles.add(file);
          }
        }
      }
    }
    return xmlFiles;
  }

  private static Article readXMLFileWithInputStream(File file) {
    Article article = new Article();
    try {

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);
      NodeList nodeList = doc.getElementsByTagName("text");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String textContent = element.getTextContent();

          article.setContent(textContent.trim());
        }
      }
    } catch (Exception ignored) {

    }
    return article;
  }
}
