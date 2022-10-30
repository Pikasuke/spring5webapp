package Bootstrap;

import Entity.Author;
import Entity.Book;
import Entity.Publisher;
import Repositories.AuthorRepository;
import Repositories.BookRepository;
import Repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started with data");

        Publisher publisher = new Publisher("Publish", "address Publish");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book DDD = new Book("DDD", "123456");
        eric.getBooks().add(DDD);
        DDD.getAuthors().add(eric);
        DDD.setPublisher(publisher);
        publisher.getBooks().add(DDD);

        authorRepository.save(eric);
        bookRepository.save(DDD);

        Author simon = new Author("Simon", "Tony");
        Book J2EE = new Book("J2EE", "234567");
        eric.getBooks().add(J2EE);
        J2EE.getAuthors().add(simon);
        J2EE.setPublisher(publisher);
        publisher.getBooks().add(J2EE);

        authorRepository.save(simon);
        bookRepository.save(J2EE);
        publisherRepository.save(publisher);


        System.out.println("Number of books = " + bookRepository.count());
        System.out.println("Publisher number books = " + publisher.getBooks().size());
    }
}
