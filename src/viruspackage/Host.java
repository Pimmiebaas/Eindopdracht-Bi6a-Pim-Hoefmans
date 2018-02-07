package viruspackage;

import java.util.HashSet;

/**
 * @author Pim
 *@date 7-2-2018
 *Deze class is de Host Class, hierin staan de gegevens van de buttons 
 */
public class Host implements Comparable<Object>{

	private String hostnaam;
	private int id;
	private String classificatie;
	
	/**
	 * @param virusSet
	 */
	public Host() {
		super();
		virusSet = new HashSet<Integer>();
	}
	private HashSet<Integer> virusSet;
	
	protected final HashSet<Integer> getVirusSet() {
		return virusSet;
	}
	protected final String getHostnaam() {
		return hostnaam;
	}
	protected final void setHostnaam(String hostnaam) {
		this.hostnaam = hostnaam;
	}
	protected final int getId() {
		return id;
	}
	protected final void setId(int id) {
		this.id = id;
	}
	protected final String getClassificatie() {
		return classificatie;
	}
	protected final void setClassificatie(String classificatie) {
		this.classificatie = classificatie;
	}
	
	public void addVirus(Integer vir) {
		virusSet.add(vir);
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
