package viruspackage;

import java.util.HashSet;
import java.util.Set;

public class Virus implements Comparable<Object>{
	
	private int id;
	private String classificatie;
	private String Virusnaam;
	
	private Set<Integer> hostSet;
	
	protected final int getId() {
		return id;
	}
	protected final void setId(int id) {
		this.id = id;
	}
	
	protected final String getVirusnaam() {
		return Virusnaam;
	}
	protected final void setVirusnaam(String virusnaam) {
		Virusnaam = virusnaam;
	}
	protected final String getClassificatie() {
		return classificatie;
	}
	protected final void setClassificatie(String classificatie) {
		this.classificatie = classificatie;
	}
	
	public void addHost(Integer Host) {
		hostSet.add(Host);
	}

	public Virus() {
		super();
		hostSet = new HashSet<Integer>(); 
		
	}
	
	protected final Set<Integer> getHostSet() {
		return hostSet;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
		
	}
}
