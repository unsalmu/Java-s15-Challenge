import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        Librarian librarian = new Librarian("Melis", "pass123", lib);
        List<Author> authors = new ArrayList<>();

        Author a = new Author("Orhan Pamuk");
        Book b = a.newBook("B001", a.getName(),"Masumiyet Müzesi", 50.0, """
                Available""", "2. Baskı", BookType.STUDYBOOK);

        lib.newBook(b);

        Author author1 = new Author("Albert Einstein");
        Book b1 = author1.newBook(
                "H001",
                author1.getName(),            // (geçici, metodunuzda parametre var diye)
                "Relativity: The Special and General Theory",
                45.0,
                "issued",
                "1. Baskı",
                BookType.STUDYBOOK
        );
        lib.newBook(b1);

        Book b2 = author1.newBook(
                "B002",
                author1.getName(),
                "The Meaning of Relativity",
                40.0,
                "available",
                "2. Baskı",
                BookType.STUDYBOOK
        );
        lib.newBook(b2);

        Author author2 = new Author("Stephen Hawking");
        Book b3 = author2.newBook("B003", author2.getName(),
                "A Brief History of Time", 55.0, "available", "1. Baskı", BookType.STUDYBOOK);
        lib.newBook(b3);
        Book b4 = author2.newBook("B004", author2.getName(),
                "The Grand Design", 60.0, "available", "1. Baskı", BookType.STUDYBOOK);
        lib.newBook(b4);

        // 2) Roman kitaplar
        Author author3 = new Author("George Orwell");
        Book b5 = author3.newBook("B005", author3.getName(),
                "1984", 30.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b5);
        Book b6 = author3.newBook("B006", author3.getName(),
                "Animal Farm", 25.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b6);

        Author author4 = new Author("Jane Austen");
        Book b7 = author4.newBook("B007", author4.getName(),
                "Pride and Prejudice", 35.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b7);
        Book b8 = author4.newBook("B008", author4.getName(),
                "Sense and Sensibility", 32.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b8);

        Author author5 = new Author("Mary Shelley");
        Book b9 = author5.newBook("B009", author5.getName(),
                "Frankenstein", 28.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b9);
        Book b10 = author5.newBook("B010", author5.getName(),
                "The Last Man", 30.0, "available", "1. Baskı", BookType.JOURNAL);
        lib.newBook(b10);


        MemberRecord mr = new Student("S001", "Öğrenci", LocalDate.now(),
                3, 5, "Sümeyye Bozdoğan", "Beyoğlu" , "+3123452345");

        Reader reader = new Reader(mr);
        lib.registerReader(reader);

        MemberRecord mr2 = new Student("S002", "Öğrenci", LocalDate.now(), 0, 5,
                "Ali Veli", "Kadıköy", "+905551234002");
        Reader r2 = new Reader(mr2);
        lib.registerReader(r2);

        MemberRecord mr3 = new Student("S003", "Öğrenci", LocalDate.now(), 0, 5,
                "Ayşe Demir", "Üsküdar", "+905551234003");
        Reader r3 = new Reader(mr3);
        lib.registerReader(r3);

        MemberRecord mr4 = new Student("S004", "Öğrenci", LocalDate.now(), 0, 5,
                "Mehmet Can", "Beşiktaş", "+905551234004");
        Reader r4 = new Reader(mr4);
        lib.registerReader(r4);

        MemberRecord mr5 = new Student("S005", "Öğrenci", LocalDate.now(), 0, 5,
                "Zeynep Yılmaz", "Bakırköy", "+905551234005");
        Reader r5 = new Reader(mr5);
        lib.registerReader(r5);

// 2. Fakülte Üyeler
        MemberRecord mr6 = new Faculty("F001", "Faculty", LocalDate.now(), 0, 5,
                "Dr. Ahmet Yılmaz", "Kadıköy", "+905551234006");
        Reader r6 = new Reader(mr6);
        lib.registerReader(r6);

        MemberRecord mr7 = new Faculty("F002", "Faculty", LocalDate.now(), 0, 5,
                "Dr. Bahri Dalyılmaz", "Kadıköy", "+905551234006");
        Reader r7 = new Reader(mr6);
        lib.registerReader(r7);

        MemberRecord mr8 = new Faculty("F003", "Faculty", LocalDate.now(), 0, 5,
                "Dr. Mehmet Aslan", "Üsküdar", "+905551234008");
        Reader r8 = new Reader(mr8);
        lib.registerReader(r8);

        MemberRecord mr9 = new Faculty("F004", "Faculty", LocalDate.now(), 0, 5,
                "Prof. Ayşegül Karaca", "Ataşehir", "+905551234009");
        Reader r9 = new Reader(mr9);
        lib.registerReader(r9);

        MemberRecord mr10 = new Faculty("F005", "Faculty", LocalDate.now(), 0, 5,
                "Dr. Burak Deniz", "Şişli", "+905551234010");
        Reader r10 = new Reader(mr10);
        lib.registerReader(r10);



        while (true) {
            System.out.println("\n=== Kütüphane Menüsü ===");
            System.out.println("1) Kitap Ekle");
            System.out.println("2) Kitap Listele");
            System.out.println("3) Kitap Seç");
            System.out.println("4) Kitap Güncelle");
            System.out.println("5) Kitap Sil");
            System.out.println("6) Türe Göre Seç");
            System.out.println("7) Yazara Göre Seç");
            System.out.println("8) Ödünç Al");
            System.out.println("9) İade Et");
            System.out.println("10) Üye Fatura Kes");
            System.out.println("11) Üye Ekle");
            System.out.println("12) Tüm Üyeleri Listele");
            System.out.println("13) Üzerindeki Kitaplar");
            System.out.println("0) Çıkış");
            System.out.print("Seçiminiz: ");
            String sel = sc.nextLine();

            switch (sel) {
                case "1":
                    // TODO: Kullanıcıdan bilgi al, Author.new_book + lib.new_book
                    System.out.println("Yazar adı: ");
                    String authorName = sc.nextLine().trim();
                    Author author = authors.stream()
                            .filter(c -> c.getName().equalsIgnoreCase(authorName))
                            .findFirst().orElseGet(() -> {
                                Author na = new Author(authorName);
                                authors.add(na);
                                return na;
                            });
                    System.out.println("Kitap ID: ");
                    String bookId = sc.nextLine().trim();

                    // 3) Kitap başlığı
                    System.out.print("Başlık: ");
                    String title = sc.nextLine().trim();

                    // 4) Fiyat
                    System.out.print("Fiyat: ");
                    double price;
                    try {
                        price = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Geçersiz fiyat, 0.0 kabul edildi.");
                        price = 0.0;
                    }

                    // 5) Edition
                    System.out.print("Baskı (edition): ");
                    String edition = sc.nextLine().trim();

                    // 6) Tür (enum)
                    System.out.print("Tür (STUDYBOOK, JOURNAL, MAGAZINE): ");
                    BookType type;
                    try {
                        type = BookType.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Geçersiz tür, STUDYBOOK seçildi.");
                        type = BookType.STUDYBOOK;
                    }

                    // 7) status sabit "available"
                    String status = "available";

                    Book newBook = author.newBook(bookId, author.getName(), title, price, status, edition, type);

                    Book registered = lib.newBook(newBook);
                    if (registered != null) {
                        System.out.println("Kitap eklendi: " + registered.getTitle());
                    }
                    break;
                case "2":
                    lib.showBook();
                    break;

                case "3":
                    System.out.println("Arama kriteri: 1=ID, 2=Başlık, 3=Yazar");
                    String crit = sc.nextLine().trim();
                    List<Book> results = Collections.emptyList();
                    Book chosen = null;

                    switch (crit) {
                        case "1":
                            System.out.print("Kitap ID: ");
                            chosen = lib.findById(sc.nextLine().trim());
                            if (chosen == null) System.out.println("Bulunamadı.");
                            break;
                        case "2":
                            System.out.print("Başlık: ");
                            results = lib.findByTitle(sc.nextLine().trim());
                            break;
                        case "3":
                            System.out.print("Yazar adı: ");
                            results = lib.findByAuthor(sc.nextLine().trim());
                            break;
                        default:
                            System.out.println("Geçersiz seçim.");
                    }

                    if (chosen == null && results != null) {
                        if (results.isEmpty()) {
                            System.out.println("Sonuç yok.");
                        } else {
                            System.out.println("\nBulunan kitaplar:");
                            results.forEach(Book::display);
                            System.out.print("Seçmek için ID girin: ");
                            chosen = lib.findById(sc.nextLine().trim());
                            if (chosen == null) System.out.println("Geçersiz ID.");
                        }
                    }

                    if (chosen != null) {
                        System.out.println("\nSeçilen kitap:");
                        chosen.display();
                    }
                    break;

                case "4":
                    // Kitap Güncelle
                    System.out.print("Güncellenecek Kitap ID: ");
                    String upId = sc.nextLine().trim();
                    Book upBook = lib.findById(upId);
                    if (upBook == null) {
                        System.out.println("Kitap bulunamadı: " + upId);
                        break;
                    }
                    System.out.print("Yeni Başlık (mevcut: " + upBook.getTitle() + "): ");
                    String newTitle = sc.nextLine().trim();
                    System.out.print("Yeni Fiyat (mevcut: " + upBook.getPrice() + "): ");
                    double newPrice = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Yeni Baskı (mevcut: " + upBook.getEdition() + "): ");
                    String newEdition = sc.nextLine().trim();
                    System.out.print("Yeni Tür (STUDYBOOK, JOURNAL, MAGAZINE) (mevcut: "
                            + upBook.getType() + "): ");
                    BookType newType = BookType.valueOf(sc.nextLine().trim().toUpperCase());

                    lib.updateBook(upId, newTitle, newPrice, newEdition, newType);
                    break;

                case "5":
                    // Kitap Sil
                    System.out.print("Silinecek Kitap ID: ");
                    String delId = sc.nextLine().trim();
                    lib.removeBook(delId);
                    break;

                case "6":

                    System.out.print("Kategori (STUDYBOOK, JOURNAL, MAGAZINE): ");
                    String kt = sc.nextLine().trim().toUpperCase();
                    BookType cat;
                    try {
                        cat = BookType.valueOf(kt);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Geçersiz kategori! Lütfen STUDYBOOK, JOURNAL veya MAGAZINE girin.");
                        break;  // hatalı girişte menüye dön
                    }

                    List<Book> byCat = lib.findByType(cat);
                    if (byCat.isEmpty()) {
                        System.out.println(cat + " kategorisinde kitap yok.");
                    } else {
                        System.out.println("\n" + cat + " kategorisindeki kitaplar:");
                        byCat.forEach(Book::display);
                    }

                    break;

                case "7":
                    System.out.print("Yazar adı: ");
                    String searchAuthor = sc.nextLine().trim();
                    List<Book> authorBooks = lib.findByAuthor(searchAuthor);
                    if (authorBooks.isEmpty()) {
                        System.out.println("Bu yazara ait kitap bulunamadı: "
                                + searchAuthor);
                    } else {
                        System.out.println("\n" + searchAuthor
                                + " adlı yazara ait kitaplar:");
                        authorBooks.forEach(Book::display);
                    }
                    break;

                case "8":
                    System.out.print("Kitap ID: ");
                    String kb = sc.nextLine();
                    System.out.print("Üye ID: ");
                    String mb = sc.nextLine();
                    lib.lendBook(kb, mb);

                    Book borrowed = lib.getBook(kb);
                    if (borrowed != null) {
                        double amount = borrowed.getPrice();
                        librarian.createBill(mb, amount, lib);
                    }

                    break;
                case "9":
                    System.out.print("Kitap ID: ");
                    String ib = sc.nextLine();
                    System.out.print("Üye ID: ");
                    String im = sc.nextLine();
                    lib.takeBackBook(ib, im, librarian);
                    break;
                case "10":
                    System.out.print("Üye ID: ");
                    String cm = sc.nextLine();
                    System.out.print("Tutar: ");
                    double amt = Double.parseDouble(sc.nextLine());
                    // Librarian örneği kullanalım
                    Librarian libn = new Librarian("Melis", "pass", lib);
                    libn.createBill(cm, amt, lib);
                    break;

                case "11":
                    // ÜYE EKLE
                    System.out.print("Üye türü (1=Öğrenci, 2=Akademisyen): ");
                    String t = sc.nextLine().trim();
                    boolean isFaculty = t.equals("2");

                    // 2) Ortak bilgileri oku
                    System.out.print("Üye ID: ");
                    String id = sc.nextLine().trim();

                    System.out.print("Ad Soyad: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Adres: ");
                    String address = sc.nextLine().trim();

                    System.out.print("Telefon No: ");
                    String phoneNo = sc.nextLine().trim();

                    // 3) Kayıt tarihi ve limit
                    LocalDate dom      = LocalDate.now();
                    int       issued   = 0;
                    int       maxLimit = 5;

                    MemberRecord memberadded = isFaculty
                            ? new Faculty(  id, "Faculty", dom, issued, maxLimit, name, address, phoneNo )
                            : new Student(  id, "Student", dom, issued, maxLimit, name, address, phoneNo );

                    Reader readerAdded = new Reader(memberadded);
                    lib.registerReader(readerAdded);
                    System.out.println(readerAdded.getName() + " eklendi.");

                    break;

                case "12":
                    // 1. Tüm üyeleri listele
                    System.out.println("\n=== Kayıtlı Üyeler ===");
                    List<Reader> allReaders = lib.getAllReaders();
                    if (allReaders.isEmpty()) {
                        System.out.println("Henüz hiç üye yok.");
                        break;
                    }
                    allReaders.forEach(r -> System.out.println(" • " + r.whoYouAre()));

                    // 2. Arama alt menüsü
                    System.out.println("\nÜye arama: 1=ID, 2=İsim, 0=Geri");
                    String sub = sc.nextLine().trim();
                    switch (sub) {
                        case "1":
                            System.out.print("Aranan Üye ID: ");
                            String searchId = sc.nextLine().trim();
                            Reader byId = lib.getReader(searchId);
                            if (byId != null) {
                                System.out.println("Bulundu: " + byId.whoYouAre());
                            } else {
                                System.out.println("Üye bulunamadı: " + searchId);
                            }
                            break;

                        case "2":
                            System.out.print("Aranan İsim: ");
                            String searchName = sc.nextLine().trim();
                            List<Reader> byName = lib.getReaderByName(searchName);
                            if (byName.isEmpty()) {
                                System.out.println("İsimle eşleşen üye yok.");
                            } else {
                                System.out.println("Eşleşen üyeler:");
                                byName.forEach(r -> System.out.println(" • " + r.whoYouAre()));
                            }
                            break;

                        case "0":
                            // Geri dön
                            break;

                        default:
                            System.out.println("Geçersiz seçim.");
                    }
                    break;

                case "13":
                    System.out.print("Üye ID: ");
                    String member = sc.nextLine().trim();
                    Reader theReader = lib.getReader(member);

                    if (theReader == null) {
                        System.out.println("Üye bulunamadı: " + member);
                        break;
                    }

                    Set<Book> setBorrowed = theReader.getBorrowedBooks();
                    if (setBorrowed.isEmpty()) {
                        System.out.println(theReader.getName() + " üzerinde kitap yok.");
                    } else {
                        System.out.println("\n" + theReader.getName() + " üzerindeki kitaplar:");
                        setBorrowed.forEach(Book::display);      // veya title/ID bas
                    }
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