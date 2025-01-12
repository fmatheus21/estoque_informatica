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
@Table(name = "supplier")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "id_user_updated")
    private UUID idUserUpdated;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    private Person person;

}
