public class EncryptedPrinter extends PrinterDecorator {

    public static final int DEFAULT_SHIFT = 7;

    // Caesar cipher over the printable ASCII range, so the result stays readable
    // as text and can be turned back into the original message.
    private static final char FIRST_PRINTABLE = ' ';
    private static final char LAST_PRINTABLE = '~';
    private static final int ALPHABET_SIZE = LAST_PRINTABLE - FIRST_PRINTABLE + 1;

    private final int shift;

    public EncryptedPrinter(Printer wrapped) {
        this(wrapped, DEFAULT_SHIFT);
    }

    public EncryptedPrinter(Printer wrapped, int shift) {
        super(wrapped);
        this.shift = Math.floorMod(shift, ALPHABET_SIZE);
    }

    @Override
    protected String decorate(String message) {
        return encrypt(message, this.shift);
    }

    public int getShift() {
        return this.shift;
    }

    public static String encrypt(String message, int shift) {
        return translate(message, shift);
    }

    public static String decrypt(String message, int shift) {
        return translate(message, -shift);
    }

    private static String translate(String message, int shift) {
        StringBuilder result = new StringBuilder(message.length());
        for (char character : message.toCharArray()) {
            if (character < FIRST_PRINTABLE || character > LAST_PRINTABLE) {
                result.append(character); // line breaks and other control characters are kept as is
                continue;
            }
            int offset = Math.floorMod(character - FIRST_PRINTABLE + shift, ALPHABET_SIZE);
            result.append((char) (FIRST_PRINTABLE + offset));
        }
        return result.toString();
    }
}
