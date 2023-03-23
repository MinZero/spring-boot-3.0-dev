package co.kr.minzero.dev.repository;

import co.kr.minzero.dev.model.Complex;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplexRepository extends R2dbcRepository<Complex, Integer> {
}
