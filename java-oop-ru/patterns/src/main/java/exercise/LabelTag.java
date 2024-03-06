package exercise;

// BEGIN
class LabelTag implements TagInterface {

    String tag;
    TagInterface subTag;

    LabelTag(String tag, TagInterface subTag) {
        this.tag = tag;
        this.subTag = subTag;
    }

    @Override
    public String render() {
        String result = String.format("<label>%s%s</label>", tag, subTag.render());
        return result;
    }
}
// END
