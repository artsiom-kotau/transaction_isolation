package com.itechart.transactionisolation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Lock(LockModeType.NONE)
    List<Product> findAll();

    @Override
    @Lock(LockModeType.NONE)
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.NONE)
    Optional<Product> findById(Long aLong);
}

