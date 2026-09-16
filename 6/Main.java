public class Main {

    public static void main(String[] args) {

        String message = "Hello World!";

        System.out.println("[1] Plain printer");
        Printer printer = new BasicPrinter();
        printer.print(message);

        System.out.println();
        System.out.println("[2] XML only");
        Printer xmlPrinter = new XMLPrinter(new BasicPrinter());
        xmlPrinter.print(message);

        System.out.println();
        System.out.println("[3] Encryption only");
        Printer encryptedPrinter = new EncryptedPrinter(new BasicPrinter());
        encryptedPrinter.print(message);

        System.out.println();
        System.out.println("[4] Encryption inside XML (the assignment example)");
        Printer printer2 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()));
        printer2.print(message);

        System.out.println();
        System.out.println("[5] XML inside encryption (the decorators the other way around)");
        Printer printer3 = new XMLPrinter(new EncryptedPrinter(new BasicPrinter()));
        printer3.print(message);

        System.out.println();
        System.out.println("[6] A custom tag and a custom shift");
        Printer printer4 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter(), "secret"), 13);
        printer4.print(message);

        System.out.println();
        System.out.println("[7] The encrypted message is decryptable");
        String encrypted = EncryptedPrinter.encrypt(message, EncryptedPrinter.DEFAULT_SHIFT);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + EncryptedPrinter.decrypt(encrypted, EncryptedPrinter.DEFAULT_SHIFT));
        System.out.println("Round trip works: "
                + message.equals(EncryptedPrinter.decrypt(encrypted, EncryptedPrinter.DEFAULT_SHIFT)));
    }
}
