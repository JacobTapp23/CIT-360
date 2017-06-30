import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
	private String code;
	private String name;
	private String continent;
	private String region;
	private Double surfaceArea;
	private Integer indepYear;
	private Long population;
	private Float lifeExpectancy;

	@Id
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getSurfaceArea() {
		return this.surfaceArea;
	}

	public void setSurfaceArea(Double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public Integer getIndepYear() {
		return this.indepYear;
	}

	public void setIndepYear(Integer indepYear) {
		this.indepYear = indepYear;
	}

	public Long getPopulation() {
		return this.population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public Float getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	public void setLifeExpectancy(Float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCode()).append(' ');
		sb.append(getName()).append(' ');
		sb.append(getContinent()).append(' ');
		sb.append(getRegion()).append(' ');
		sb.append(getSurfaceArea()).append(' ');
		sb.append(getIndepYear()).append(' ');
		sb.append(getPopulation()).append(' ');
		sb.append(getLifeExpectancy()).append(' ');
		return sb.toString();
	}
}
