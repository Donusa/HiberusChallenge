package challenge.hiberus.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seo_metrics")
public class SeoMetricsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer speedIndex;
	private Double timeToInteractive;
	private String url;
	private String strategy;
	
	public SeoMetricsDTO() {
		super();
	}
	
	public SeoMetricsDTO(int speedIndex, double timeToInteractive) {
		this.speedIndex = speedIndex;
		this.timeToInteractive = timeToInteractive;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public int getSpeedIndex() {
		return speedIndex;
	}

	public void setSpeedIndex(int speedIndex) {
		this.speedIndex = speedIndex;
	}

	public double getTimeToInteractive() {
		return timeToInteractive;
	}

	public void setTimeToInteractive(double timeToInteractive) {
		this.timeToInteractive = timeToInteractive;
	}

}
