package challenge.hiberus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.hiberus.dto.SeoMetricsDTO;
import challenge.hiberus.services.PageSpeedService;
import challenge.hiberus.services.SeoMetricsService;

@RestController
@RequestMapping("/api/seo")
public class SeoComparisonController {

	@Value("${API_KEY}")
	private String API_KEY;
    private final String API_URL = "https://www.googleapis.com/pagespeedonline/v5/runPagespeed";

    
    @Autowired
    private PageSpeedService pageSpeedService;
    
    @Autowired
    private SeoMetricsService seoMetricsService;

    @PostMapping("/stats")
    public SeoMetricsDTO checkSeoMetrics(
            @RequestParam String url,
            @RequestParam String strategy) {
    	
    	String apiUrl = API_URL 
    			+"?url="+url
    			+"&key="+API_KEY
    			+"&strategy="+strategy;
    	
        SeoMetricsDTO metrics = pageSpeedService.getSeoMetrics(apiUrl);
        metrics.setStrategy(strategy);
        metrics.setUrl(url);
        return seoMetricsService.saveOrUpdateMetrics(metrics);
    }
	
    @GetMapping("/history")
    public List<SeoMetricsDTO> getSeoHistory(){
    	return seoMetricsService.findAll();
    }
    
}
