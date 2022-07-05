package com.example.infosystem.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Style {
    @Id
    @SequenceGenerator(name = "style_id", sequenceName = "style_id", allocationSize = 1)
    @GeneratedValue(generator = "style_id", strategy = GenerationType.SEQUENCE)
    private Long styleId;
    @Column(nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Style style = (Style) o;
        return styleId != null && Objects.equals(styleId, style.styleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}