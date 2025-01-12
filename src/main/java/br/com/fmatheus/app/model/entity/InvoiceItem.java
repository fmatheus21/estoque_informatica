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
@Table(name = "invoice_item")
public class InvoiceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "serial_number", length = 40, nullable = false)
    private String serialNumber;

    @Column(name = "asset", length = 10)
    private String asset;

    @Lob
    @Column(name = "observation", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String observation;

    @NotNull
    @Column(name = "id_user_created", nullable = false)
    private UUID idUserCreated;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "id_invoice", referencedColumnName = "id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    private Product product;

}
