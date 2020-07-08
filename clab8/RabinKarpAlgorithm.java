public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int pLength = pattern.length(), iLength = input.length();
        RollingString iString = new RollingString(input.substring(0,pLength), pLength);
        RollingString pString = new RollingString(pattern, pLength);

        for (int i = pLength - 1; i < iLength; i++) {
            if (i != pLength - 1)
                iString.addChar(input.charAt(i));
            if (iString.hashCode() == pString.hashCode()) {
                if (iString.equals(pString)) {
                    return i - pLength + 1;
                }
            }

        }
        return -1;
    }

}
