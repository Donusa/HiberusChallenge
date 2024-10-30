package challenge.hiberus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.hiberus.dto.SeoMetricsDTO;
import challenge.hiberus.repository.SeoMetricsRepository;

@Service
public class SeoMetricsService {
	
	
	@Autowired
	private SeoMetricsRepository seoMetricsRepository;
	
	public SeoMetricsDTO saveOrUpdateMetrics(SeoMetricsDTO metrics) {
		String normalizedUrl = normalizeUrl(metrics.getUrl());
		
		Optional<SeoMetricsDTO> existingMetricsOpt = seoMetricsRepository
										.findByUrlAndStrategy(normalizedUrl, metrics.getStrategy());
		
		if(!existingMetricsOpt.isPresent()) {
			return seoMetricsRepository.save(metrics);
		}
		
		SeoMetricsDTO existingMetrics = existingMetricsOpt.get();
		existingMetrics.setSpeedIndex(metrics.getSpeedIndex());
		existingMetrics.setTimeToInteractive(metrics.getTimeToInteractive());
		return seoMetricsRepository.save(existingMetrics);
	}
	
	public List<SeoMetricsDTO> findAll(){
		return seoMetricsRepository.findAll();
	}
	
	private String normalizeUrl(String url) {
		String originalScheme = "";
		if (url.startsWith("http://")) {
			originalScheme = "http://";
	        url = url.substring(7);
	    } else if (url.startsWith("https://")) {
	    	originalScheme = "https://";
	        url = url.substring(8);
	    }

	    if (url.startsWith("www.")) {
	        url = url.substring(4);
	    }
	    return originalScheme + url.toLowerCase();
	}
}
