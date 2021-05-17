package school.xauat.datastructres.trienode;

/**
 * @author ：zsy
 * @date ：Created 2021/4/14 8:59
 * @description：前缀树
 */
public class Trie {

    public Trie[] children;
    public boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie cur = this;
        for(char ch : word.toCharArray()) {
            if(cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Trie();
            }
            cur = cur.children[ch - 'a'];
        }
        this.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node == null;
    }

    public Trie searchPrefix(String prefix) {
        Trie cur = this;
        for(char ch : prefix.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return null;
            }
            cur = cur.children[ch - 'a'];
        }
        return cur;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
    }
}
