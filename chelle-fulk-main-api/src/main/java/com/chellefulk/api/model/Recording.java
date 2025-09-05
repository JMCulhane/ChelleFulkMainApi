


package com.chellefulk.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;

    private String title;
    private int yearPublished;
    private String description;
    @ElementCollection
    private List<String> performers;
    private int trackCount;
    private String link;
    @ElementCollection
    private List<Sample> samples;

    public void setId(Long id) {
        this.id = id;
    }
}
