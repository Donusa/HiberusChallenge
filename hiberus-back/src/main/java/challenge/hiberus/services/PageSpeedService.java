package challenge.hiberus.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import challenge.hiberus.dto.SeoMetricsDTO;

@Service
public class PageSpeedService {

	@SuppressWarnings("unchecked")
	public SeoMetricsDTO getSeoMetrics(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});
		if (response.getStatusCode() == HttpStatus.OK) {
			Map<String, Object> lighthouseResult = (Map<String, Object>) response.getBody().get("lighthouseResult");
			Map<String, Object> audits = (Map<String, Object>) lighthouseResult.get("audits");
			Map<String, Object> interactive = (Map<String, Object>) audits.get("interactive");
            Map<String, Object> metrics = (Map<String, Object>) audits.get("metrics");

            Map<String, Object> details = (Map<String, Object>) metrics.get("details");
            List<Map<String, Object>> items = (List<Map<String, Object>>) details.get("items");

            SeoMetricsDTO metricsDto = new SeoMetricsDTO();
            if (!items.isEmpty()) {
                metricsDto.setSpeedIndex(((Number) items.get(0).get("speedIndex")).intValue());
            }

			metricsDto.setTimeToInteractive(((Number) interactive.get("score")).doubleValue());

			return metricsDto;
		}
		return null;
	}

}
