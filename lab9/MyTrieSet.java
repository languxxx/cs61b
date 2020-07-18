import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private static class Node{
        private char c;
        private boolean keyFlag;
        private HashMap<Character, Node> map;

        public Node(char c, boolean isKey) {
            this.c = c;
            this.keyFlag = isKey;
            this.map = new HashMap<>();
        }

        public boolean isKey() {
            return this.keyFlag;
        }

        public void setKey(boolean b) {
            this.keyFlag = b;
        }

        public boolean containsKey(char c) {
            return map.containsKey(c);
        }

        public void put(char c) {
            Node n = new Node(c, false);
            map.put(c, n);
        }

        public Node get(char c) {
            return map.get(c);
        }

        public boolean hasNext() {
            return !map.isEmpty();
        }

        public char getChar() {
            return this.c;
        }

        public Collection<Node> getAllChild() {
            return map.values();
        }

        public void clear() {
            map.clear();
        }
    }

    private Node root;

    public MyTrieSet() {
        this.root = new Node((char) 0, false);
        this.keyList = new ArrayList<>();
    }

    /** Clears all items out of Trie */
    public void clear() {
        root.clear();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.containsKey(c)) {
                return false;
            }
            curr = curr.get(c);
        }
        return curr.isKey();
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }

        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.containsKey(c)) {
                curr.put(c);
            }
            curr = curr.get(c);
        }
        curr.setKey(true);
    }

    private List<String> keyList;

    private void searchKey(Node n, String s) {
        s = s + n.getChar();
        if (!n.hasNext()) {
            keyList.add(s);
            return;
        }
        if (n.isKey()) {
            keyList.add(s);
        }
        Collection<Node> nodes = n.getAllChild();
        for(Node node : nodes) {
            searchKey(node, s);
        }
    }
    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        keyList.clear();

        if (prefix == null || prefix.length() < 1) {
            return keyList;
        }

        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.containsKey(c)) {
                return keyList;
            }
            curr = curr.get(c);
        }
        searchKey(curr, prefix.substring(0, prefix.length() - 1));

        return keyList;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
