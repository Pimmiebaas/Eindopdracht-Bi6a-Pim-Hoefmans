package viruspackage;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;


/**
 * @author Pim Hoefmans
 *date 07-02-2018
 *klas Bi2a
 *Deze class bevat alle logica, hierin word het bestand ingelezen en worden dmv hashmaps, integers en strings de data verwerkt.
 */
public class VirusLogica {

	static HashMap<Integer,Virus> virussen = new HashMap<Integer,Virus>();
	static HashMap<Integer,Host> hosts = new HashMap<Integer,Host>();;
	
	public static HashMap<Integer, HashMap> haalData(String pad) throws Exception {
		
		BufferedReader lezer = new BufferedReader(new FileReader(pad));
		String regel = lezer.readLine();

		int ID = 0;
		int Virusnaam = 1;
		int Classificatie = 2;
		int Host = 7;
		int Hostnaam = 8;
		String[] line;

		regel = lezer.readLine();
		
		HashMap<Integer,HashMap> allData = new HashMap();
		
		
		while (regel != null) {
			line = regel.split("\t", -1);

			try {
				Virus tempVirus;
				Host tempHost;

				

				Integer id = Integer.valueOf(line[ID]);
				String vnaam= line[Virusnaam] ;
				String classi= line[Classificatie];
				Integer hostid = Integer.valueOf(line[Host]);
				String hostnaam = line[Hostnaam];

				if(virussen.containsKey(id)) {
					tempVirus = virussen.get(id);

				} else {
					tempVirus = new Virus();
					tempVirus.setClassificatie(classi);
					tempVirus.setId(id);
					tempVirus.setVirusnaam(vnaam);
					virussen.put(id, tempVirus);

				}

				if(hosts.containsKey(hostid)) {

					tempHost = hosts.get(hostid);

				}
				else {
					tempHost = new Host();
					tempHost.setId(hostid);
					tempHost.setHostnaam(hostnaam);
					hosts.put(hostid, tempHost);

				}


				tempVirus.addHost(tempHost.getId());
				tempHost.addVirus(tempVirus.getId());
				hosts.put(tempHost.getId(), tempHost);
				virussen.put(tempVirus.getId(), tempVirus);
				

			}
			catch(Exception e1) {
			} 
			
			regel = lezer.readLine();

		}
		
		allData.put(0, hosts);
		allData.put(7, virussen);
		return allData;
	}

	public static HashSet<Integer> calcs(Integer hostid1, Integer hostid2, String vircla) {
		HashSet<Integer> h1v = hosts.get(hostid1).getVirusSet();
		HashSet<Integer> h2v = hosts.get(hostid2).getVirusSet();
		
		
		HashSet<Integer> comp = new HashSet<Integer>(h1v);
		comp.retainAll(h2v);
		return comp;
		
	}

	public static HashSet<Integer> getHostToVirusses(int host1id) {
		
		return hosts.get(host1id).getVirusSet();
		
	}
	
	public static ArrayList<Virus> setToArrayList(HashSet<Integer> set){
		
		ArrayList<Virus> result = new ArrayList<Virus>();
		
		for(Integer i: set) {
			result.add(virussen.get(i));
		}
		
		return result;
		
	}
	
	public static String arrayListToString(ArrayList<Virus> virusList) {
		String result = "";
		for(Virus v : virusList) {
			
			result += v.getVirusnaam() + "\t(" + v.getId() + ")\n";
			
		}
		return result;
		
		
	}
	
	public static Comparator<Virus> compareByIDComparator = new Comparator<Virus>() {

		@Override
		public int compare(Virus vir1, Virus vir2) {
			int id1 = vir1.getId();
			int id2 = vir2.getId();
			
			return Integer.compare(id1, id2);
		}
		
	};
	
	public static Comparator<Virus> compareClassiComparator = new Comparator<Virus>() {

		@Override
		public int compare(Virus vir1, Virus vir2) {
			String classi1 = vir1.getClassificatie();
			String classi2 = vir2.getClassificatie();
			
			return classi1.compareTo(classi2);
		}
		
		
	};
	
	public static Comparator<Virus> compareAantalHosts = new Comparator<Virus>() {

		@Override
		public int compare(Virus vir1, Virus vir2) {
			int aantal1 = vir1.getHostSet().size();
			int aantal2 = vir2.getHostSet().size();
			return Integer.compare(aantal1, aantal2);
		}
		
		
		
	};
	
	
	
}
