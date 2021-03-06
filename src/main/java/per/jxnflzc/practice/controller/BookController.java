package per.jxnflzc.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jxnflzc.practice.model.Book;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"ES图书接口"})
@RestController
@RequestMapping("/practice/book")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ApiOperation(value = "添加图书")
    @PostMapping(value = "/add")
    public ResponseBodyInfo<String> add(@RequestBody Book book) {
        LOGGER.debug("book:{}",book);
        try {
            bookRepository.save(book);
        } catch (Exception ex) {
            return ResponseBodyInfo.error("添加图书信息失败");
        }
        return ResponseBodyInfo.success("保存成功");
    }

    @ApiOperation(value = "通过id获取图书")
    @GetMapping(value = "/id/{id}")
    public ResponseBodyInfo<Book> queryById(@PathVariable("id") String id) {
        LOGGER.debug("id:{}",id);
        Book book = null;
        try {
            book = bookRepository.findBookById(id);
        } catch (Exception ex) {
            return ResponseBodyInfo.error("获取图书信息失败");
        }
        return ResponseBodyInfo.success(book);
    }

    @ApiOperation(value = "通过名称获取图书")
    @GetMapping(value = "/name/{name}")
    public ResponseBodyInfo<Book> queryByName(@PathVariable("name") String name) {
        LOGGER.debug("name:{}",name);
        Book book = null;
        try {
            book = bookRepository.findBookByName(name);
        } catch (Exception ex) {
            return ResponseBodyInfo.error("获取图书信息失败");
        }
        return ResponseBodyInfo.success(book);
    }

    @ApiOperation(value = "通过作者获取图书")
    @GetMapping(value = "/author/{author}")
    public ResponseBodyInfo<List<Book>> queryByAuthor(@PathVariable("author") String author) {
        LOGGER.debug("author:{}",author);
        List<Book> books = new ArrayList<>();
        try {
            books = bookRepository.findBookByAuthor(author);
        } catch (Exception ex) {
            return ResponseBodyInfo.error("获取图书信息失败");
        }
        return ResponseBodyInfo.success(books);
    }
}
