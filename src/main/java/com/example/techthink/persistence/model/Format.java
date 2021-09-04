package com.example.techthink.persistence.model;

import javax.persistence.*;

@Entity
public class Format {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private FormatName name;


    public Format(Integer id, FormatName name) {
        this.id = id;
        this.name = name;
    }

    public Format() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormatName getName() {
        return name;
    }

    public void setName(FormatName name) {
        this.name = name;
    }
}
