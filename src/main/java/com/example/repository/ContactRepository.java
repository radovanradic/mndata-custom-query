package com.example.repository;

import com.example.entity.Contact;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.H2)
public interface ContactRepository extends PageableRepository<Contact, Long> {

    @Query("SELECT c.*, ca_.account_name AS ca_account_name, ca_.id AS ca_id, ca_.contact_id AS ca_contact_id FROM contact c " +
            "LEFT JOIN contact_account ca_ ON c.id = ca_.contact_id WHERE ca_.account_name LIKE :accountName")
    @Join(value = "contactAccounts", alias = "ca_")
    List<Contact> searchContactsByAccountName(String accountName);

    @Override
    @Join(value = "contactAccounts")
    @NonNull
    Optional<Contact> findById(@NotNull @NonNull Long id);
}
