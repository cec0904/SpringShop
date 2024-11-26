package org.example.springshop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Movie")
@Getter
@Setter
@NoArgsConstructor
public class Movie extends Item {

    private String director;

    private String actor;
}
