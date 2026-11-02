import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User alice = new User("alice");
        User bob = new User("bob");
        User carol = new User("carol");
        User[] users = {alice, bob, carol};

        Library library = new Library();
        library.addDocument("menu", LocalDate.of(2026, 9, 1), "Cafeteria menu: pea soup on Thursday");
        library.addDocument("handbook", LocalDate.of(2026, 1, 15), "Employee handbook: be nice to each other");
        library.addProtectedDocument("salaries", LocalDate.of(2026, 10, 1), "Salaries: alice 5200, bob 4100", alice);
        library.addProtectedDocument("roadmap", LocalDate.of(2026, 6, 20), "Roadmap: launch v2 in Q1", alice, bob);
        library.addProtectedDocument("secret", LocalDate.of(2025, 12, 24), "Nobody can read this");

        System.out.println("=== Creation dates are always public ===");
        for (IDocument doc : library.getDocuments()) {
            System.out.printf("%-10s created %s (%s)%n", doc.getId(), doc.getCreationDate(),
                    doc instanceof DocumentProxy ? "protected" : "unprotected");
        }

        System.out.println("\n=== Every user tries every document ===");
        for (User user : users) {
            System.out.println("-- " + user);
            for (IDocument doc : library.getDocuments()) {
                tryRead(user, doc);
            }
        }

        System.out.println("\n=== Granting carol access to the roadmap ===");
        AccessControlService.getInstance().grantAccess("roadmap", carol.getUsername());
        tryRead(carol, library.getDocument("roadmap"));

        System.out.println("\n=== Revoking bob's access to the roadmap ===");
        AccessControlService.getInstance().revokeAccess("roadmap", bob.getUsername());
        tryRead(bob, library.getDocument("roadmap"));

        System.out.println("\n=== Adding a document with an existing id ===");
        try {
            library.addDocument("menu", LocalDate.now(), "Duplicate");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== AccessControlService is a singleton ===");
        System.out.println("Same instance: " + (AccessControlService.getInstance() == AccessControlService.getInstance()));
    }

    private static void tryRead(User user, IDocument doc) {
        try {
            System.out.printf("   %-10s OK      %s%n", doc.getId(), doc.getContent(user));
        } catch (AccessDeniedException e) {
            System.out.printf("   %-10s DENIED  %s%n", doc.getId(), e.getMessage());
        }
    }
}
