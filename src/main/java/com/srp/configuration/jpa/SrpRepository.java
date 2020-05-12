package com.srp.configuration.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SrpRepository<E,P extends Serializable> extends JpaRepository<E, P> {
}
