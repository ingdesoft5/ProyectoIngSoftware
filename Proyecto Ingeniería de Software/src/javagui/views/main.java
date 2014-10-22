package javagui.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Canvas;

import javax.swing.JEditorPane;

import java.awt.TextField;

import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Backend.Lector;
import Backend.MenuPrincipal;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.TextArea;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.GridLayout;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class main extends JFrame {
	boolean ovalo = false;

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					main frame = new main();
					frame.setSize(1000, 750);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 591);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNombre = new JMenu("ISD5");
		menuBar.add(mnNombre);
		
		JMenuItem mntmAcercaDeNombre = new JMenuItem("Acerca de ISD5");
		mntmAcercaDeNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame acerca = new JFrame();
				acerca.setSize(500, 80);
				JPanel jp = new JPanel();
				JLabel ep = new JLabel();
				ep.setText("Software para crear diagramas de casos de usos o de clases");
				jp.add(ep);
				acerca.add(jp);
				acerca.setVisible(true);
				
			}
		});
		mnNombre.add(mntmAcercaDeNombre);
		
		Component verticalStrut_2 = Box.createVerticalStrut(7);
		mnNombre.add(verticalStrut_2);
		
		JMenuItem mntmSalirDeNombre = new JMenuItem("Salir de ISD5");
		mntmSalirDeNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
			}
		});
		mnNombre.add(mntmSalirDeNombre);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		mnArchivo.add(mnNuevo);
		
		JMenuItem mntmUml = new JMenuItem("UML");
		mnNuevo.add(mntmUml);
		
		JMenuItem mntmDiagramaDeClases = new JMenuItem("Diagrama de Clases");
		mnNuevo.add(mntmDiagramaDeClases);
		
		JLabel lblEditorXml = DefaultComponentFactory.getInstance().createLabel("Editor XML:");
		lblEditorXml.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JTextArea textArea = new JTextArea();
		JScrollPane scrollBar = new JScrollPane(textArea);
		
		JMenuItem mntmAbrirArchivo = new JMenuItem("Abrir archivo...");
		mntmAbrirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.Abrir();
				if(mp.l.tipo=="UCD"){
					textArea.append("<UseCaseDiagram name= \""+mp.l.diagCU.nombre+"\"> \n");
					textArea.append("   <actors> \n");
					for(int i=0; i<mp.l.diagCU.Actores.size();i++){
						textArea.append("      <actor type = \""+ (mp.l.diagCU.Actores.elementAt(i).type).toString()+"\" id = \""+ (mp.l.diagCU.Actores.elementAt(i).id).toString() +"\" name = \"" + (mp.l.diagCU.Actores.elementAt(i).name).toString()+"\" />"+"\n");
					}
					textArea.append("   </actors> \n"+"   <usecases>\n");
					for(int i=0; i<mp.l.diagCU.CasosDeUso.size(); i++){
						textArea.append("      <usecase id = \""+(mp.l.diagCU.CasosDeUso.elementAt(i).id).toString()+"\" name = \""+(mp.l.diagCU.CasosDeUso.elementAt(i).name).toString() + "\" />\n");
					}
					textArea.append("   </usecases>\n"+"   <connections> \n");
					for(int i=0; i<mp.l.diagCU.Conexiones.size();i++){
						textArea.append("      <connection type = \""+ (mp.l.diagCU.Conexiones.elementAt(i).type).toString()+"\" from = \"" + (mp.l.diagCU.Conexiones.elementAt(i).from).toString() + "\" to = \"" + (mp.l.diagCU.Conexiones.elementAt(i).to).toString() + "\"/>\n");
					}
					
					textArea.append("   </connections>\n </UseCaseDiagram>");
				}
				else{
					textArea.append("<ClassDiagram name = \"" + mp.l.diagC.nombre+"\"> \n");
					for(int i = 0; i<mp.l.diagC.Clases.size(); i++){
						textArea.append("   <class id = \""+(mp.l.diagC.Clases.elementAt(i).id).toString()+"\" name = \""+ (mp.l.diagC.Clases.elementAt(i).nombreClase).toString()+"\"> \n");
						textArea.append("      <attributes>\n");
						for(int j = 0; j<mp.l.diagC.Clases.elementAt(i).atributos.size(); j++){
							textArea.append("         <att name = \""+(mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).nombre).toString()+"\" type = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).tipo).toString() + " visibility = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).visibilidad).toString() + "\">\n");
						}
						textArea.append("      </attributes>\n");
						textArea.append("     <methods>\n");
						for(int j = 0; j<mp.l.diagC.Clases.elementAt(i).metodos.size(); j++){
							textArea.append("         <method name = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).nombre).toString()+"\" type = \"" + (mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).tipo).toString()+"\">\n");
							for(int k = 0; k<mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.size();k++){
								textArea.append("            <param name = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.elementAt(k).nombre).toString()+"\" type = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.elementAt(k).tipo).toString()+"\"/>\n");
							}
							textArea.append("         </method>\n");
						}
						textArea.append("      </methods>\n");
						textArea.append("   </class>\n");
					}
					textArea.append("</ClassDiagram\n");
				}

			}
		});

		mnArchivo.add(mntmAbrirArchivo);
		
		Component verticalStrut = Box.createVerticalStrut(7);
		mnArchivo.add(verticalStrut);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mnArchivo.add(mntmCerrar);
		
		Component verticalStrut_1 = Box.createVerticalStrut(7);
		mnArchivo.add(verticalStrut_1);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mntmGuardar);
		
		JMenu mnGuardarComo = new JMenu("Guardar Como...");
		mnArchivo.add(mnGuardarComo);
		
		JMenuItem mntmXml = new JMenuItem("XML");
		mnGuardarComo.add(mntmXml);
		
		JMenuItem mntmPng = new JMenuItem("Diagrama como imágen");
		mnGuardarComo.add(mntmPng);
		
		JMenuItem mntmCrearCarpetaCon = new JMenuItem("Crear carpeta con ambos archivos");
		mnGuardarComo.add(mntmCrearCarpetaCon);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 245, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setVisible(false);
		desktopPane.setBackground(Color.WHITE);
		JButton btnConvertir = new JButton("Crear diagrama");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String xml = textArea.getText();
				File f = new File("temp.xml");
				FileWriter fw;
				try {
					fw = new FileWriter(f);
					fw.write(xml);
					fw.close();
					Lector l = new Lector();
					l.leer(f);
					if(l.tipo == "UCD"){
						for(int i = 0; i<l.diagCU.CasosDeUso.size();i++){
							desktopPane.setSize(600, i*100+200);
							Oval o = new Oval(desktopPane, 100*(i+1), 100*(i+1)+50, l.diagCU.CasosDeUso.elementAt(i).name);
						}
						int contadorp = 0, contadors = 0;
						for(int i = 0; i<l.diagCU.Actores.size();i++){
							
							if(l.diagCU.Actores.elementAt(i).type.equals("primary")){
								
								Actor a = new Actor(desktopPane, 0, contadorp + 20, l.diagCU.Actores.elementAt(i).name );
								contadorp++;
							}
							else{
								
								Actor b = new Actor(desktopPane, 550, contadors + 20, l.diagCU.Actores.elementAt(i).name );
								contadors++;

							}
							
							
						}
					}
					else if(l.tipo == "CD"){
						
					}
					else if(l.tipo == "error"){
						JFrame error = new JFrame();
						error.setSize(600, 80);
						JPanel jp2 = new JPanel();
						JLabel ep2 = new JLabel();
						ep2.setText("No se puede crear el diagrama, ya que se encontró un error en el XML");
						jp2.add(ep2);
						error.add(jp2);
						error.setVisible(true);
					}
					
					
				} catch (IOException e1) {

					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JScrollPane scrollBar_1 = new JScrollPane(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JToolBar toolBar = new JToolBar();
		//JScrollPane scrollBar_1 = new JScrollPane(mc);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnConvertir))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblEditorXml))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(127))
						.addComponent(scrollBar_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(22)
									.addComponent(lblEditorXml, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
									.addGap(22)
									.addComponent(btnConvertir))
								.addComponent(scrollBar_1, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		scrollPane.setViewportView(desktopPane_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText("  DIAGRAMA");
		editorPane.setForeground(Color.WHITE);
		editorPane.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane.setBackground(UIManager.getColor("Desktop.background"));
		editorPane.setBounds(1, 25, 61, 11);
		desktopPane_1.add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setText("   CASOS DE");
		editorPane_1.setForeground(Color.WHITE);
		editorPane_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane_1.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_1.setBounds(1, 37, 61, 11);
		desktopPane_1.add(editorPane_1);
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setText("       USO");
		editorPane_2.setForeground(Color.WHITE);
		editorPane_2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane_2.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_2.setBounds(1, 49, 62, 11);
		desktopPane_1.add(editorPane_2);
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setText("___________________");
		editorPane_3.setForeground(Color.WHITE);
		editorPane_3.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		editorPane_3.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_3.setBounds(2, 61, 100, 7);
		desktopPane_1.add(editorPane_3);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/asociacion.png")));
		button_2.setBounds(32, 72, 30, 32);
		desktopPane_1.add(button_2);
		button_2.setToolTipText("Relación con actor");
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/herencia.png")));
		button_4.setBounds(32, 105, 30, 32);
		desktopPane_1.add(button_4);
		button_4.setToolTipText("Relación de especialización");
		
		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/punteada.png")));
		button_5.setBounds(1, 72, 30, 32);
		desktopPane_1.add(button_5);
		button_5.setToolTipText("Relación de extensión o inclusión");
		
		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/Persona.png")));
		button_6.setBounds(1, 105, 30, 32);
		desktopPane_1.add(button_6);
		button_6.setToolTipText("Actor");
		
		JButton button_8 = new JButton("");
		button_8.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/oval.png")));
		button_8.setBounds(0, 139, 62, 62);
		desktopPane_1.add(button_8);
		button_8.setToolTipText("Caso de uso");
		
		JEditorPane editorPane_4 = new JEditorPane();
		editorPane_4.setText("  DIAGRAMA");
		editorPane_4.setForeground(Color.WHITE);
		editorPane_4.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane_4.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_4.setBounds(1, 244, 61, 11);
		desktopPane_1.add(editorPane_4);
		
		
		JEditorPane dtrpnDeClases = new JEditorPane();
		dtrpnDeClases.setText("  DE CLASES");
		dtrpnDeClases.setForeground(Color.WHITE);
		dtrpnDeClases.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		dtrpnDeClases.setBackground(UIManager.getColor("Desktop.background"));
		dtrpnDeClases.setBounds(1, 256, 61, 11);
		desktopPane_1.add(dtrpnDeClases);
		
		JEditorPane editorPane_5 = new JEditorPane();
		editorPane_5.setText("___________________");
		editorPane_5.setForeground(Color.WHITE);
		editorPane_5.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		editorPane_5.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_5.setBounds(2, 267, 100, 7);
		desktopPane_1.add(editorPane_5);
		
		JButton button_9 = new JButton("");
		button_9.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/agregacion.png")));
		button_9.setBounds(1, 279, 30, 32);
		desktopPane_1.add(button_9);
		button_9.setToolTipText("Relación de agregación");
		
		JButton button_10 = new JButton("");
		button_10.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/asociacion.png")));
		button_10.setBounds(32, 279, 30, 32);
		desktopPane_1.add(button_10);
		button_10.setToolTipText("Relación de asociación");
		
		JButton button_11 = new JButton("");
		button_11.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/composicion.png")));
		button_11.setBounds(1, 313, 30, 32);
		desktopPane_1.add(button_11);
		button_11.setToolTipText("Relación de composición");
		
		JButton button_12 = new JButton("");
		button_12.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/herencia.png")));
		button_12.setBounds(32, 313, 30, 32);
		desktopPane_1.add(button_12);
		button_12.setToolTipText("Relación de herencia");
		
		JButton button_13 = new JButton("");
		button_13.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/punteada.png")));
		button_13.setBounds(1, 348, 30, 32);
		desktopPane_1.add(button_13);
		button_13.setToolTipText("Relación de dependencia");
		
		JButton button_14 = new JButton("");
		button_14.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/caja.png")));
		button_14.setBounds(0, 381, 62, 62);
		desktopPane_1.add(button_14);
		button_14.setToolTipText("Clase");
	
		
		JButton btnTipografa = new JButton("");
		toolBar.add(btnTipografa);
		
				btnTipografa.setIcon(new ImageIcon(main.class.getResource("/com/sun/javafx/scene/web/skin/Bold_16x16_JFX.png")));
				
				JButton button = new JButton("");
				toolBar.add(button);
				button.setIcon(new ImageIcon(main.class.getResource("/com/sun/javafx/scene/web/skin/FontColor_16x16_JFX.png")));
				
				JButton button_7 = new JButton("");
				toolBar.add(button_7);
				button_7.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/basurero.png")));
				button_7.setToolTipText("Borrar todo");
				
				JPanel panel_2 = new JPanel();
				toolBar.add(panel_2);
				FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				flowLayout.setHgap(0);
				flowLayout.setVgap(0);
	
		Choice choice = new Choice();
		panel_2.add(choice);
		choice.addItem("Arial");
		choice.addItem("Calibri");
		choice.addItem("Cambria");
		choice.addItem("Tahoma");
		choice.addItem("Times New Roman");
		contentPane.setLayout(gl_contentPane);
		paintComponents(getGraphics());
	}
	
	public void CloseFrame(){
		super.dispose();		
	}
}



