public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque();
        for (int i=0;i<word.length();i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);

        while(d.size() >= 2) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);

        while(d.size() >= 2) {
            if (cc.equalChars((char)d.removeFirst(),(char)d.removeLast()) == false) {
                return false;
            }
        }
        return true;
    }
}
