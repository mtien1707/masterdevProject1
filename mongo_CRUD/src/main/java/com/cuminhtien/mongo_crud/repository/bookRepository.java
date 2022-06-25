package com.cuminhtien.mongo_crud.repository;


import com.cuminhtien.mongo_crud.model.book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface bookRepository extends MongoRepository<book, String>  {


    Optional<book> findByAuthorContains(String author);

    List<book> findByPublishDateBetween(Date publishDate, Date publishDate1);

    Optional<book> findByBookNameContains(String name);

    @Query("{'$text':{$search:?0}}")
    List<book> findByText(String text);


    //get all book by publishDate desc pageable


//    repository get all book sort by publishDate desc paging from 0 to 10






}









