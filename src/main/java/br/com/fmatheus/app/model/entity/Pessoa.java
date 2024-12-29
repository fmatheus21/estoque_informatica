package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "documento", length = 20, nullable = false)
    private String documento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "id_usuario_criacao", nullable = false)
    private UUID idUsuarioCriacao;

    @ManyToOne
    @JoinColumn(name = "id_pesoa_tipo", referencedColumnName = "id", nullable = false)
    private PessoaTipo pessoaTipo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Contato contato;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Fornecedor fornecedor;

}
