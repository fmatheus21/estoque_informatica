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
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "document", length = 20, nullable = false)
    private String document;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "id_user_created", nullable = false)
    private UUID idUserCreated;

    @ManyToOne
    @JoinColumn(name = "id_person_type", referencedColumnName = "id", nullable = false)
    private PersonType personType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Supplier supplier;

}
