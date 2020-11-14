package per.jxnflzc.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.model.Book;
import per.jxnflzc.practice.repositories.BookRepository;

import java.util.List;

@Api(tags = {"ES图书接口"})
@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ApiOperation(value = "添加图书")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Book book) {
        logger.debug("book:{}",book);
        bookRepository.save(book);
        return new ResponseEntity<>("保存成功", HttpStatus.OK);
    }

    @ApiOperation(value = "通过id获取图书")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity queryById(@PathVariable("id") String id) {
        logger.debug("id:{}",id);
        Book book = bookRepository.findBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @ApiOperation(value = "通过名称获取图书")
    @GetMapping(value = "/name/{name}")
    public ResponseEntity queryByName(@PathVariable("name") String name) {
        logger.debug("name:{}",name);
        Book book = bookRepository.findBookByName(name);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @ApiOperation(value = "通过作者获取图书")
    @GetMapping(value = "/author/{author}")
    public ResponseEntity queryByAuthor(@PathVariable("author") String author) {
        logger.debug("author:{}",author);
        List<Book> books = bookRepository.findBookByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
