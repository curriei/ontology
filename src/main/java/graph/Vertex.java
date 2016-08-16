package graph;

public class Vertex {

	public Vertex(String value) {
		this.value = value;
	}
	
	public Vertex(String value, Color color) {
		this.value = value;
		this.color = color;
	}
	private String value;
	private Color color;
	private Integer distance;
	private Vertex ancestor;
	
	
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public Vertex getAncestor() {
		return ancestor ;
	}

	public void setAncestor(Vertex ancestor) {
		this.ancestor = ancestor;
	}

}
