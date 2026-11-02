public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");
        Directory pictures = new Directory("pictures");
        Directory holiday = new Directory("holiday");

        root.add(new File("notes.txt", 0.5));
        root.add(documents);
        root.add(pictures);

        documents.add(new File("report.pdf", 2.0));
        documents.add(new File("todo.txt", 0.1));

        pictures.add(new File("cat.jpg", 3.5));
        pictures.add(holiday);

        holiday.add(new File("beach.jpg", 4.2));
        holiday.add(new File("trip.txt", 0.3));

        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);
        System.out.printf("Total size: %.1f MB%n", sizeVisitor.getTotalSize());

        SearchVisitor txtSearch = new SearchVisitor(file -> file.getName().endsWith(".txt"));
        root.accept(txtSearch);
        System.out.println("\n.txt files:");
        for (File file : txtSearch.getResults()) {
            System.out.println(file.getName() + " (" + file.getSize() + " MB)");
        }

        SearchVisitor nameSearch = new SearchVisitor(file -> file.getName().contains("a"));
        root.accept(nameSearch);
        System.out.println("\nFiles with 'a' in name:");
        for (File file : nameSearch.getResults()) {
            System.out.println(file.getName() + " (" + file.getSize() + " MB)");
        }
    }
}
