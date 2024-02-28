package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    private String revLine;
    ReversedSequence(String line) {
        revLine = reverceSeq(line);
    }
    private String reverceSeq(String line) {
        String result;
        StringBuilder sb = new StringBuilder();
        for (int i = line.length(); i > 0; i--) {
            sb.append(line.charAt(i - 1));
        }
        result = sb.toString();
        return result;
    }

    @Override
    public int length() {
        return revLine.length();
    }

    @Override
    public char charAt(int index) {
        return revLine.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return revLine.subSequence(start, end);
    }

    @Override
    public String toString() {
        return revLine;
    }
}
// END
