package com.example.lab1b.model.View;

import com.example.lab1b.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Immutable
@Subselect("SELECT * FROM A")
public class Material {
    @Id
    @Column(name = "Housing_id")
    private Long housingId;

    private Integer categories_num;

}
