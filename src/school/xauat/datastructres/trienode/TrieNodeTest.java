package school.xauat.datastructres.trienode;

/**
 * @author ：zsy
 * @date ：Created 2021/4/14 23:19
 * @description：前缀树
 */
public class TrieNodeTest {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        root.insert("apple");
        root.insert("app");
        root.insert("ap");
        System.out.println(root.startWith("ba"));
        System.out.println(root.startWith("ba"));
        System.out.println(root.search("apple"));
        System.out.println(root.search("appl"));
        System.out.println(root.startWith("app"));
    }
}
class TrieNode {
    private TrieNode[] childrens;
    private boolean isEnd;

    public TrieNode() {
        this.childrens = new TrieNode[26];
        isEnd = false;
    }

    public void insert(String word) {
        TrieNode node = this;
        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.childrens[index] == null) {
                node.childrens[index] = new TrieNode();
            }
            node = node.childrens[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = this;
        for(char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.childrens[index] == null) {
                return null;
            }
            node = node.childrens[index];
        }
        return node;
    }
}
