package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    SingleTag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String result;
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName).append(" ");
        for (Map.Entry att : attributes.entrySet()) {
            sb.append(att.getKey()).append(String.format("=\"%s\" ", att.getValue()));
        }
        sb.delete(sb.length() - 1, sb.length());
        result = sb.append(">").toString();
        return result;
    }
}
// END
