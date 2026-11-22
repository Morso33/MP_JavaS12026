import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Command-line UI for the book recommendation system (Prototype pattern demo). */
public class Main {
    private static final Path SAVE_FILE = Path.of("recommendations.txt");
    private static final List<Recommendation> lists = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        if (Files.exists(SAVE_FILE)) {
            load();
        }
        if (lists.isEmpty()) {
            seed();
        }
        while (true) {
            System.out.println("\n=== Book Recommendations ===");
            System.out.println("1) View all lists");
            System.out.println("2) Clone a list and modify the clone");
            System.out.println("3) Create a new list from scratch");
            System.out.println("4) Save to " + SAVE_FILE);
            System.out.println("0) Quit");
            switch (ask("Choose: ")) {
                case "1" -> viewAll();
                case "2" -> cloneAndEdit();
                case "3" -> createNew();
                case "4" -> save();
                case "0" -> { return; }
                default -> System.out.println("Unknown option.");
            }
        }
    }

    // ---------- menu actions ----------

    private static void viewAll() {
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] " + lists.get(i));
        }
    }

    private static void cloneAndEdit() {
        Recommendation original = pickList();
        if (original == null) return;
        Recommendation copy = original.clone();
        String audience = ask("Target audience for the clone [" + copy.getTargetAudience() + "]: ");
        if (!audience.isBlank()) copy.setTargetAudience(audience);
        lists.add(copy);
        System.out.println("Cloned. The original is untouched.");
        edit(copy);
    }

    private static void createNew() {
        String audience = ask("Target audience: ");
        if (audience.isBlank()) {
            System.out.println("Audience cannot be empty.");
            return;
        }
        Recommendation r = new Recommendation(audience);
        lists.add(r);
        edit(r);
    }

    private static void edit(Recommendation r) {
        while (true) {
            System.out.println("\n" + r);
            System.out.println("a) Add book  r) Remove book  t) Change audience  d) Done");
            switch (ask("Edit: ").toLowerCase()) {
                case "a" -> {
                    String title = ask("Title: ");
                    String author = ask("Author: ");
                    String genre = ask("Genre: ");
                    int year = askInt("Year: ");
                    if (title.isBlank() || author.isBlank()) {
                        System.out.println("Title and author are required.");
                    } else {
                        r.addBook(new Book(author, title, genre, year));
                    }
                }
                case "r" -> {
                    int n = askInt("Book number to remove: ");
                    System.out.println(r.removeBook(n - 1) == null ? "No such book." : "Removed.");
                }
                case "t" -> {
                    String t = ask("New audience: ");
                    if (!t.isBlank()) r.setTargetAudience(t);
                }
                case "d" -> { return; }
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private static Recommendation pickList() {
        viewAll();
        int n = askInt("List number: ");
        if (n < 1 || n > lists.size()) {
            System.out.println("No such list.");
            return null;
        }
        return lists.get(n - 1);
    }

    // ---------- persistence: "#audience" line, then "author|title|genre|year" lines ----------

    private static void save() {
        List<String> out = new ArrayList<>();
        for (Recommendation r : lists) {
            out.add("#" + r.getTargetAudience());
            for (Book b : r.getBooks()) {
                out.add(String.join("|", clean(b.getAuthor()), clean(b.getTitle()),
                        clean(b.getGenre()), String.valueOf(b.getPublicationYear())));
            }
        }
        try {
            Files.write(SAVE_FILE, out, StandardCharsets.UTF_8);
            System.out.println("Saved " + lists.size() + " lists.");
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    private static void load() {
        try {
            Recommendation current = null;
            for (String line : Files.readAllLines(SAVE_FILE, StandardCharsets.UTF_8)) {
                if (line.startsWith("#")) {
                    current = new Recommendation(line.substring(1));
                    lists.add(current);
                } else if (current != null && !line.isBlank()) {
                    String[] p = line.split("\\|", 4);
                    if (p.length == 4) {
                        current.addBook(new Book(p[0], p[1], p[2], Integer.parseInt(p[3].trim())));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not read save file (" + e.getMessage() + "); starting fresh.");
            lists.clear();
        }
    }

    private static String clean(String s) { return s.replace("|", "/").replace("\n", " "); }

    // ---------- helpers ----------

    private static String ask(String prompt) {
        System.out.print(prompt);
        return in.hasNextLine() ? in.nextLine().trim() : "0"; // EOF -> quit
    }

    private static int askInt(String prompt) {
        try {
            return Integer.parseInt(ask(prompt));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void seed() {
        Recommendation kids = new Recommendation("Children");
        kids.addBook(new Book("Roald Dahl", "Matilda", "Fantasy", 1988));
        kids.addBook(new Book("A. A. Milne", "Winnie-the-Pooh", "Children's", 1926));
        lists.add(kids);

        Recommendation ya = new Recommendation("Young adults");
        ya.addBook(new Book("Suzanne Collins", "The Hunger Games", "Dystopian", 2008));
        ya.addBook(new Book("John Green", "The Fault in Our Stars", "Romance", 2012));
        lists.add(ya);

        Recommendation scifi = new Recommendation("Science fiction fans");
        scifi.addBook(new Book("Frank Herbert", "Dune", "Science fiction", 1965));
        scifi.addBook(new Book("Isaac Asimov", "Foundation", "Science fiction", 1951));
        lists.add(scifi);
    }
}
