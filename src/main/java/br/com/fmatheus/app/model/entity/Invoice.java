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
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "number", length = 20, nullable = false)
    private String number;

    @NotBlank
    @Column(name = "access_key", length = 44, nullable = false)
    private String accessKey;

    @NotBlank
    @Lob
    @Column(name = "xml_file", nullable = false, columnDefinition = "LONGTEXT")
    private String xmlFile;

    @ManyToOne
    @JoinColumn(name = "id_supplier", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", fetch = FetchType.LAZY)
    private Collection<InvoiceItem> invoiceItems;

}
