public class XMLPrinter extends PrinterDecorator {

    public static final String DEFAULT_TAG = "message";

    private final String tag;

    public XMLPrinter(Printer wrapped) {
        this(wrapped, DEFAULT_TAG);
    }

    public XMLPrinter(Printer wrapped, String tag) {
        super(wrapped);
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("The XML tag cannot be empty.");
        }
        this.tag = tag;
    }

    @Override
    protected String decorate(String message) {
        return "<" + this.tag + ">" + escape(message) + "</" + this.tag + ">";
    }

    public String getTag() {
        return this.tag;
    }

    public static String unescape(String message) {
        return message
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&");
    }

    private static String escape(String message) {
        return message
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
