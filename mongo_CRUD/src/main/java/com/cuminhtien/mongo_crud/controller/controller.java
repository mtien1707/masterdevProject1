package com.cuminhtien.mongo_crud.controller;

import com.cuminhtien.mongo_crud.model.book;
import com.cuminhtien.mongo_crud.repository.bookRepository;
import com.cuminhtien.mongo_crud.service.impl.bookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class controller {

    @Autowired
    private bookRepository repository;

    @Autowired
    private bookServiceImpl service;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody book book) {
        repository.save(book);
        return "Added book with id: " + book.getId();
    }

    @GetMapping("/findAllBook")
    public List<book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/findBookById/{id}")
    public Optional<book> findBookById(@PathVariable String id) {
        return repository.findById(String.valueOf(id));
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable String id) {
        repository.deleteById(String.valueOf(id));
        return "Deleted book with id: " + id;
    }


    @GetMapping("/findBookAuthor/{text}")
    public Optional<book> findByAuthorContains(@PathVariable String text) {
        return repository.findByAuthorContains(text);
    }

    @GetMapping("/findByName/{name}")
    public Optional<book> findByName(@PathVariable String name) {
        return repository.findByBookNameContains(name);
    }

    @GetMapping("/findByDateBetween")
    public ResponseEntity<List<book>> getBookBetweenDate(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                         @RequestParam(value = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return new ResponseEntity<List<book>>(repository.findByPublishDateBetween(start, end), HttpStatus.OK);
    }

    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable String id, @RequestBody book book) {
        book.setId(id);
        repository.save(book);
        return "Updated book with id: " + id;
    }


    @GetMapping("/findByIndex/{name}")
    public List<book> getBookByIndex(@PathVariable String name) {
        return repository.findByText(name);
    }

    @GetMapping("/findByPage/{pageno}/{pagesize}")
    public List<book> getBookByPage(@PathVariable int pageno, @PathVariable int pagesize) {
        return service.getAllBook(pageno, pagesize);
//get map by publishDate desc pageable
//    @GetMapping("/findAllBookByDate")

//    @GetMapping("/findAllByPublishDate")
//    public List<book> getAllByPublishDate(@RequestParam(value = "page") int page,
//                                          @RequestParam(value = "size") int size){
//        return service.getAllBook( page, size);
//    }


//    @GetMapping("/findByText/{text}")
//    public List<book> getAllBy(@PathVariable String text){
//        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
//        List<book> books =  repository.findAllBy( criteria);
//        return books;
    }
}


