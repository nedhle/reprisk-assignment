package com.reprisk.utils;

import com.reprisk.model.Company;
import com.reprisk.model.Trie;
import java.util.Set;

public class CompanyListLoader {
  public static Trie buildCompanyTrie(Set<Company> companies) {
    Trie trie = new Trie();
    for (Company company : companies) {
      trie.insert(CompanyNameUtil.cleanCompanyName(company.getName()));
    }
    return trie;
  }
}
