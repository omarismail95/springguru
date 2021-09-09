package com.omar.springguru.bootstrap;

import com.omar.springguru.model.Author;
import com.omar.springguru.model.Book;
import com.omar.springguru.model.Publisher;
import com.omar.springguru.repositories.AuthorRepository;
import com.omar.springguru.repositories.BookRepository;
import com.omar.springguru.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println(String.format("Started in bootstrap method...."));

        Publisher testPublisher = new Publisher();
        testPublisher.setCity("Amman");
        testPublisher.setAddressLine1("Amman line 1");
        testPublisher.setName("Vandam");
        testPublisher.setState("Amman");
        testPublisher.setZipCode("00962");

        publisherRepository.save(testPublisher);

        System.out.println(String.format("Publishers count after save is : %s",
                publisherRepository.count()));

        Author omar = new Author("Omar", "Ismail");
        Book test = new Book("The Lord Of Java" , "123");

        omar.getBooks().add(test);
        test.getAuthors().add(omar);
        test.setPublisher(testPublisher);
        testPublisher.getBooks().add(test);

        authorRepository.save(omar);
        bookRepository.save(test);
        publisherRepository.save(testPublisher);


        Author omar2 = new Author("Ahmad", "Omar");
        Book test2 = new Book("Spring" , "456");

        omar2.getBooks().add(test2);
        test2.getAuthors().add(omar2);
        test2.setPublisher(testPublisher);
        testPublisher.getBooks().add(test2);

        authorRepository.save(omar2);
        bookRepository.save(test2);
        publisherRepository.save(testPublisher);

        System.out.println(String.format("Number Of Books after save to H2 database is : %s",
                bookRepository.count()));

        System.out.println(String.format("Publishers Number Of Books : %s",
                testPublisher.getBooks().size()));

    }
}
