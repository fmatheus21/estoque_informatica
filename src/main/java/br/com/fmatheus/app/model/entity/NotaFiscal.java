package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notafical")
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "numero", length = 20, nullable = false)
    private String numero;

    @NotBlank
    @Column(name = "chave_acesso", length = 44, nullable = false)
    private String chaveAcesso;

    @NotBlank
    @Lob
    @Column(name = "arquivo_xml", nullable = false, columnDefinition = "LONGTEXT")
    private String arquivoXml;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notaFiscal", fetch = FetchType.LAZY)
    private Collection<NotaFiscalItem> notaFiscalItems;

}
