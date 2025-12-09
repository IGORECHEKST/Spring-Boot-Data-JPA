package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import java.math.BigDecimal;

@FilterDef(name = "deletedBookFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Entity
@Data
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET is_deleted = TRUE WHERE id=?")
@Filter(name = "deletedBookFilter", condition = "is_deleted = :isDeleted")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
     private boolean isDeleted = false;
}
