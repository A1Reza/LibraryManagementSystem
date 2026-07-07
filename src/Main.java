import exception.BookNotFoundException;
import exception.MemberNotFoundException;
import model.Book;
import model.Member;
import repository.BookRepository;
import repository.MemberRepository;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();


        bookRepository.insertBook(new Book(0, "Clean Code", "Robert C. Martin", 45.99, 10));
        bookRepository.insertBook(new Book(0, "Effective Java", "Joshua Bloch", 55.50, 8));
        bookRepository.insertBook(new Book(0, "Java Concurrency in Practice", "Brian Goetz", 60.00, 5));
        bookRepository.insertBook(new Book(0, "Head First Java", "Kathy Sierra", 39.99, 12));
        bookRepository.insertBook(new Book(0, "Design Patterns", "GoF", 70.00, 7));


        memberRepository.registerMember(new Member(0, "Ali Ahmadi", "09121234567"));
        memberRepository.registerMember(new Member(0, "Sara Mohammadi", "09123334455"));
        memberRepository.registerMember(new Member(0, "Reza Asadipour", "09125556677"));


        try {
            bookRepository.updateBookPrice(1, 49.99);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            bookRepository.deleteBook(2);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            memberRepository.deleteMember(3);
        } catch (MemberNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.println(bookRepository.findBookById(1));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.println(memberRepository.findMemberById(1));
        } catch (MemberNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}