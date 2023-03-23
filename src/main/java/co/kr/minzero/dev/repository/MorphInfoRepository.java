package co.kr.minzero.dev.repository;

import co.kr.minzero.dev.model.MorphInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MorphInfoRepository extends R2dbcRepository<MorphInfo, Integer> {
}
