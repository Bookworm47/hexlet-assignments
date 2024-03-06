package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
class PairedTag extends Tag {
    private String tagName;
    private Map<String, String> attributes;
    String tagBody;
    List<Tag> singleTags;

    PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> singleTags) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.tagBody = tagBody;
        this.singleTags = singleTags;
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
        sb.append(">");
        sb.append(tagBody);
        for (Tag childTag : singleTags) {
            sb.append(childTag.toString());
        }
        result = sb.append(String.format("</%s>", tagName)).toString();
        return result;
    }
}
// END
