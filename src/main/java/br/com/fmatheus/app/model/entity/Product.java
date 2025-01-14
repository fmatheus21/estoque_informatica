package br.com.fmatheus.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Size(min = 1, max = 200)
    @NotBlank
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Size(min = 8, max = 14)
    @NotBlank
    @Column(name = "ean", length = 20, nullable = false)
    private String ean;

    @JsonIncludeProperties({"id", "name"})
    @ManyToOne
    @JoinColumn(name = "id_manufacturer", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
}
