package com.borzdykooa.internationalization.model;

import com.borzdykooa.internationalization.model.util.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item", schema = "goods_schema")
public class Item extends IdEntity {

    @Column(unique = true, nullable = false)
    private Integer code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_attribute", schema = "goods_schema",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
    )
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Set<ItemTranslation> itemTranslations = new HashSet<>();
}
