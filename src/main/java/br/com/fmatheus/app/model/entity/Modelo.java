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
@Table(name = "modelo")
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "partnumber", length = 30, nullable = false)
    private String partnumber;

    @NotBlank
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id", nullable = false)
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    private Produto produto;
    
}
