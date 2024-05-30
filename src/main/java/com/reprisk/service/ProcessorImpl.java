package com.reprisk.service;

import com.reprisk.model.Article;
import com.reprisk.model.Company;
import com.reprisk.model.Trie;
import com.reprisk.model.TrieNode;
import com.reprisk.utils.CompanyListLoader;
import com.reprisk.utils.CompanyNameUtil;
import com.reprisk.utils.ResultReporter;
import com.reprisk.utils.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.reprisk.utils.CsvReader.readCompanies;

@Slf4j
@Service
public class ProcessorImpl implements Processor {

  @Override
  public void process() {
    Logger logger = LoggerFactory.getLogger(ProcessorImpl.class);
    String startTime = String.valueOf(new java.util.Date());

    Set<Company> companies = readCompanies("files/160408_company_list.csv");
    Set<Article> articles = XmlReader.getXmlFiles("files/data");

    Trie companyTrie = CompanyListLoader.buildCompanyTrie(companies);

    Map<String, Set<String>> companyMentions = findCompanies(articles, companyTrie);

    ResultReporter.reportResults(companyMentions);

    String endTime = String.valueOf(new java.util.Date());

    logger.info(
        "Process Start Time : {} End Time : {} \n {} Company found \n More information in result.txt..",
        startTime,
        endTime,
        companyMentions.size());
  }

  public Map<String, Set<String>> findCompanies(Set<Article> articles, Trie companyTrie) {
    Map<String, Set<String>> companyMentions = new HashMap<>();

    for (Article article : articles) {
      Set<String> foundCompanies = searchCompaniesInArticle(article, companyTrie);
      for (String company : foundCompanies) {
        if (CompanyNameUtil.isValidCompanyMention(article.getContent(), company)) {
          companyMentions.computeIfAbsent(company, k -> new HashSet<>()).add(article.getContent());
        }
      }
    }
    return companyMentions;
  }

  private Set<String> searchCompaniesInArticle(Article article, Trie companyTrie) {
    Set<String> foundCompanies = new HashSet<>();
    TrieNode root = companyTrie.getRoot();
    int length = article.getContent() == null ? 0 : article.getContent().length();

    for (int i = 0; i < length; i++) {
      TrieNode node = root;
      for (int j = i; j < length; j++) {
        char ch = article.getContent().charAt(j);
        node = node.children.get(ch);
        if (node == null) {
          break;
        }
        if (node.isEndOfWord) {
          foundCompanies.add(article.getContent().substring(i, j + 1));
        }
      }
    }
    return foundCompanies;
  }
}
