package com.example.heroku.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column
    private String name;

    @Column
    private int releaseDate;

    @Column
    private String director;

    @Column
    private String description;


}
