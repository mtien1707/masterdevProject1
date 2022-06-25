package com.cuminhtien.mongo_crud.service.impl;

import com.cuminhtien.mongo_crud.model.book;
import com.cuminhtien.mongo_crud.repository.bookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class bookServiceImpl  {


    private bookRepository bookReposity;

    public List<book> getAllBook(Integer pageNo, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by("publishDate").descending());

        Page<book> pagedResult = bookReposity.findAll((org.springframework.data.domain.Pageable) paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<book>();
        }
    }
}
