package com.borzdykooa.internationalization.model;

import com.borzdykooa.internationalization.model.util.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute", schema = "goods_schema")
public class Attribute extends IdEntity {

    @Column(name = "en_name", nullable = false)
    private String enName;

    @Column(name = "ru_name", nullable = false)
    private String ruName;

    @ManyToMany(mappedBy = "attributes")
    private Set<Item> items = new HashSet<>();
}
