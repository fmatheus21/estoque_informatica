package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "ean", length = 20, nullable = false)
    private String ean;

    @ManyToOne
    @JoinColumn(name = "id_manufacturer", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
}
