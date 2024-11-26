package org.example.springshop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Book")
@Getter
@Setter
@NoArgsConstructor
public class Book extends Item {

    private String author;

    private String isbn;
}
