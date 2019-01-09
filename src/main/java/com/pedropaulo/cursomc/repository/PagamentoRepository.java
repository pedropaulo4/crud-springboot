package com.pedropaulo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedropaulo.cursomc.domain.Pagamento;

// Basta criar o repository da superclasse



@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
