package modelo;

import java.util.Objects;

public class Hora {
	
	private int horas;
	private int minutos;
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	@Override
	public String toString() {
		String h=String.valueOf(horas);
		String m=String.valueOf(minutos);
		if(horas<10) 
			h="0"+horas;
		if(minutos<10) 
			m="0"+minutos;
		return  h + ":" + m ;
	}
	@Override
	public int hashCode() {
		return Objects.hash(horas, minutos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hora other = (Hora) obj;
		return horas == other.horas && minutos == other.minutos;
	}
	

}
