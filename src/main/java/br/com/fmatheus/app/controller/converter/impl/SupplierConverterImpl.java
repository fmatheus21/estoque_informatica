package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.SupplierConverter;
import br.com.fmatheus.app.controller.dto.request.DanfeXmlRequest;
import br.com.fmatheus.app.model.entity.Contact;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.entity.PersonType;
import br.com.fmatheus.app.model.entity.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class SupplierConverterImpl implements SupplierConverter {

    @Override
    public Person converterToEntity(DanfeXmlRequest request) {
        DanfeXmlRequest.NFe.InfNFe.Emit emit = request.getNFe().getInfNFe().getEmit();

        var person = Person.builder()
                .personType(PersonType.builder().id(2L).build())
                .name(emit.getXNome())
                .document(emit.getCnpj())
                .dateCreated(LocalDateTime.now())
                .idUserCreated(UUID.randomUUID())
                .build();

        var supplier = Supplier.builder()
                .person(person)
                .build();

        var contact = Contact.builder()
                .person(person)
                .phone(emit.getEnderEmit().getFone())
                .build();

        person.setSupplier(supplier);
        person.setContact(contact);

        return person;
    }

    @Override
    public Object converterToResponse(Person person) {
        throw new UnsupportedOperationException();
    }
}
