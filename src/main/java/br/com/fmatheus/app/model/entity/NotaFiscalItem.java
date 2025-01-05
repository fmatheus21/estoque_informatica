package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "notafiscal_item")
public class NotaFiscalItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "serial_number", length = 40, nullable = false)
    private String serialNumber;

    @NotBlank
    @Lob
    @Column(name = "observacao", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String observacao;

    @NotBlank
    @Column(name = "id_usuario_criacao", nullable = false)
    private UUID idUsuarioCriacao;

    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_notafiscal", referencedColumnName = "id", nullable = false)
    private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

}
