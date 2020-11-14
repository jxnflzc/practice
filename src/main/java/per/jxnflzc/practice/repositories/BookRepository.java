package per.jxnflzc.practice.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import per.jxnflzc.practice.model.Book;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Book findBookByName(String name);
    List<Book> findBookByAuthor(String author);
    Book findBookById(String id);
}
