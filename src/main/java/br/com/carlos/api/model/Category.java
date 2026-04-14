package br.com.carlos.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imagemUrl")
    private String imagemUrl;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category_id")
    private Set<Product> products = new HashSet<>();
}
