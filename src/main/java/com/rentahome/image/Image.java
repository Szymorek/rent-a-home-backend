package com.rentahome.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    Long id;
    private String name;
    private String location;
    private long fileSize;
    @Lob
    byte[] content;

    public Image(String name, String location, long fileSize, byte[] content) {
        this.name = name;
        this.location = location;
        this.fileSize = fileSize;
        this.content = content;
    }
}
