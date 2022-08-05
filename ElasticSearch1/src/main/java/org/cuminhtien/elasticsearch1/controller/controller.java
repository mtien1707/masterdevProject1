package org.cuminhtien.elasticsearch1.controller;



import org.cuminhtien.elasticsearch1.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
public class controller {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/get/{keyword}/{size}", method = RequestMethod.GET)
    public List<String> getTitle(@PathVariable String keyword, @PathVariable int size) throws IOException {
        repository titleSearchRepository = context.getBean(repository.class);
        return titleSearchRepository.getTitle(keyword, size);
    }
}
