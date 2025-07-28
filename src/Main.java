import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        Author a = new Author("Orhan Pamuk");
        Book b = a.newBook("B001", a.getName(), 50.0, """
                Available""", "2. Baskı", LocalDate.now(), BookType.STUDYBOOK);

        lib.newBook(b);

        MemberRecord mr = new Student("S001", "Öğrenci", LocalDate.now(),
                3, 5, "Sümeyye Bozdoğan", "Beyoğlu" , "+3123452345");

        Reader reader = new Reader("Buğra Canpolat", mr);
        lib.registerReader(reader);

        while (true) {
            System.out.println("\n=== Kütüphane Menüsü ===");
            System.out.println("1) Kitap Ekle");
            System.out.println("2) Kitap Listele");
            System.out.println("3) Ödünç Al");
            System.out.println("4) İade Et");
            System.out.println("5) Üye Fatura Kes");
            System.out.println("0) Çıkış");
            System.out.print("Seçiminiz: ");
            String sel = sc.nextLine();

            switch (sel) {
                case "1":
                    // TODO: Kullanıcıdan bilgi al, Author.new_book + lib.new_book
                    break;
                case "2":
                    lib.showBook();
                    break;
                case "3":
                    System.out.print("Kitap ID: ");
                    String kb = sc.nextLine();
                    System.out.print("Üye ID: ");
                    String mb = sc.nextLine();
                    lib.lendBook(kb, mb);
                    break;
                case "4":
                    System.out.print("Kitap ID: ");
                    String ib = sc.nextLine();
                    System.out.print("Üye ID: ");
                    String im = sc.nextLine();
                    lib.takeBackBook(ib, im);
                    break;
                case "5":
                    System.out.print("Üye ID: ");
                    String cm = sc.nextLine();
                    System.out.print("Tutar: ");
                    double amt = Double.parseDouble(sc.nextLine());
                    // Librarian örneği kullanalım
                    Librarian libn = new Librarian("Melis", "pass");
                    libn.createBill(cm, amt, lib);
                    break;
                case "0":
                    System.out.println("Çıkılıyor…");
                    sc.close();
                    return;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }

    }
}