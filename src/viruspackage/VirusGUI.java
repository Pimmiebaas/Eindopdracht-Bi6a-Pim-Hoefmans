package viruspackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class VirusGUI {
	
	
	private HashMap<Integer, HashMap> allData; 
	private JFrame frame;
	private JTextArea VLijst2;
	private JTextArea Overeenkomst;
	private JTextArea filelocatie;
	private JButton btnNewButton;
	JComboBox HostId1, HostId2, VirCla;
	private JTextArea VLijst1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VirusGUI window = new VirusGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VirusGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 921, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 842, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("Choose File");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File file = fc.getSelectedFile();
			filelocatie.setText(file.getAbsolutePath());
		
			try {
				HashMap<Integer,Host> hosts;
				allData = VirusLogica.haalData(file.getAbsolutePath());
				hosts = allData.get(0);
				for(Host h:hosts.values()) {
					HostId1.addItem(h.getHostnaam()+"\t"+ h.getId());
					HostId2.addItem(h.getHostnaam()+"\t"+h.getId());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		});
		
		btnNewButton.setBounds(646, 13, 120, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calculate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				calculateFunc();
				
			}
		});
		
		
		btnNewButton_1.setBounds(646, 51, 120, 25);
		panel.add(btnNewButton_1);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(29, 17, 56, 16);
		panel.add(lblFile);
		
		filelocatie = new JTextArea();
		filelocatie.setBackground(Color.WHITE);
		filelocatie.setBounds(154, 14, 350, 22);
		panel.add(filelocatie);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(78, 232, 174, 189);
		panel.add(scrollPane);
		
		VLijst1 = new JTextArea();
		scrollPane.setViewportView(VLijst1);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(592, 232, 174, 189);
		panel.add(scrollPane_2);
		
		VLijst2 = new JTextArea();
		scrollPane_2.setViewportView(VLijst2);
		VLijst2.setColumns(10);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(330, 232, 174, 189);
		panel.add(scrollPane_1);
		
		Overeenkomst = new JTextArea();
		scrollPane_1.setViewportView(Overeenkomst);
		Overeenkomst.setColumns(10);
		
		VirCla = new JComboBox();
		VirCla.setBounds(154, 52, 350, 22);
		panel.add(VirCla);
		String[] virusclasses = {"none", "dsDNA", "ssDNA", "ssRNA", "dsRNA",
				"Retro", "Satellites or virophages", "Viroid", "Others"};
		VirCla.setModel(new DefaultComboBoxModel(virusclasses));
	
		
		HostId1 = new JComboBox();
		HostId1.setBounds(78, 105, 158, 22);
		panel.add(HostId1);
		
		JLabel lblNewLabel = new JLabel("Host ID");
		lblNewLabel.setBounds(29, 108, 56, 16);
		panel.add(lblNewLabel);
		
		HostId2 = new JComboBox();
		HostId2.setBounds(592, 105, 174, 22);
		panel.add(HostId2);
		
		JLabel lblViras = new JLabel("Viral Classification");
		lblViras.setBounds(29, 55, 113, 16);
		panel.add(lblViras);
		
		JRadioButton rdbtnId = new JRadioButton("ID");
		rdbtnId.setBounds(88, 136, 127, 25);
		panel.add(rdbtnId);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Classificatie");
		rdbtnNewRadioButton.setBounds(88, 166, 127, 25);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnAantalHosts = new JRadioButton("Aantal hosts");
		rdbtnAantalHosts.setBounds(88, 196, 127, 25);
		panel.add(rdbtnAantalHosts);
	}

	protected void calculateFunc() {
		
		int host1ID = Integer.valueOf(((String) HostId1.getSelectedItem()).split("\t")[1]);
		int host2ID = Integer.valueOf(((String) HostId2.getSelectedItem()).split("\t")[1]);
		String itemVirCla = (String)VirCla.getSelectedItem();
		
		HashSet<Integer> host1Set = VirusLogica.getHostToVirusses(host1ID);
		HashSet<Integer> host2Set = VirusLogica.getHostToVirusses(host2ID);
		HashSet<Integer> overlapSet =  VirusLogica.calcs(host1ID,host2ID, itemVirCla);
			
		ArrayList<Virus> host1List = VirusLogica.setToArrayList(host1Set);
		ArrayList<Virus> host2List = VirusLogica.setToArrayList(host2Set);
		ArrayList<Virus> overlapList = VirusLogica.setToArrayList(overlapSet);
		
		//classification magic
		
		//sorting magic
		
		String veldHost1 = VirusLogica.arrayListToString(host1List);
		String veldHost2 = VirusLogica.arrayListToString(host2List);
		String veldOverlap = VirusLogica.arrayListToString(overlapList);
		
		VLijst1.setText(veldHost1);
		VLijst2.setText(veldHost2);
		Overeenkomst.setText(veldOverlap);
		
		
	}

	protected JTextArea getVLijst1() {
		return VLijst1;
	}
	protected JTextArea getOvereenkomst() {
		return Overeenkomst;
	}
	protected JTextArea getVLijst2() {
		return VLijst2;
	}
}