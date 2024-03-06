package exercise;

// BEGIN
class InputTag implements TagInterface {
    private final String type;
    private final String value;

    InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        String result = String.format("<input type=\"%s\" value=\"%s\">", type, value);
        return result;
    }
}
// END
