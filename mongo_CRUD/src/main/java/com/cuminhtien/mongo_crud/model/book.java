package com.cuminhtien.mongo_crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor


@Document(collection = "tiencm8")
public class book {
    @Id
    private String id;


    private @TextIndexed String bookName;


    private @TextIndexed String author;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishDate;

    private String description;
    
}
