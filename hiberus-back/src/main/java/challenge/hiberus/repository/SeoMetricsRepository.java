package challenge.hiberus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.hiberus.dto.SeoMetricsDTO;

public interface SeoMetricsRepository extends JpaRepository<SeoMetricsDTO, Long>{
	Optional<SeoMetricsDTO> findByUrlAndStrategy(String url, String strategy);
}
