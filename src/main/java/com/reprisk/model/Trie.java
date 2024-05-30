package com.reprisk.model;

import lombok.Data;

@Data
public class Trie {
  private final TrieNode root = new TrieNode();

  public void insert(String word) {
    TrieNode node = root;
    for (char ch : word.toCharArray()) {
      node = node.children.computeIfAbsent(ch, k -> new TrieNode());
    }
    node.isEndOfWord = true;
  }
}
