package com.borzdykooa.internationalization.model;

import com.borzdykooa.internationalization.model.util.IdEntity;
import com.borzdykooa.internationalization.model.util.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_translation", schema = "goods_schema")
public class ItemTranslation extends IdEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
