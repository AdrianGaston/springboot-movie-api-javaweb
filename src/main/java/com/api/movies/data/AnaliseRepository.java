
package com.api.movies.data;

import com.api.movies.model.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer> {
    Analise findByFilmeId(Integer filmeId);
}

