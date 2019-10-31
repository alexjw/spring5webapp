package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository PublisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, guru.springframework.spring5webapp.repositories.PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.PublisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher publisher = new Publisher();
        publisher.setName("Harper Collins");
        publisher.setAddress("New York");
        Book ddd = new Book("Domain Driven Design", "1234");
        ddd.setPublisher(publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        PublisherRepository.save(publisher);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Wrox");
        publisher1.setAddress("Miami");
        Book noEJB = new Book("J2EE Development without EJB", "23444");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher1);

        PublisherRepository.save(publisher1);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
