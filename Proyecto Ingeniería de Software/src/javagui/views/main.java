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
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNombre = new JMenu("Nombre");
		menuBar.add(mnNombre);
		
		JMenuItem mntmAcercaDeNombre = new JMenuItem("Acerca de Nombre");
		mnNombre.add(mntmAcercaDeNombre);
		
		Component verticalStrut_2 = Box.createVerticalStrut(7);
		mnNombre.add(verticalStrut_2);
		
		JMenuItem mntmSalirDeNombre = new JMenuItem("Salir de Nombre");
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
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmDeshacer = new JMenuItem("Deshacer");
		mnEditar.add(mntmDeshacer);
		
		JMenuItem mntmRehacer = new JMenuItem("Rehacer");
		mnEditar.add(mntmRehacer);
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
							Oval o = new Oval(desktopPane, 100*(i+1), 120*(i+1), l.diagCU.CasosDeUso.elementAt(i).name);
							Actor a = new Actor(desktopPane, 100*(i+1), 120*(i+1));
						}

					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		if(ovalo){

		}
		JScrollPane scrollBar_1 = new JScrollPane(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
		
	
		
		JButton btnTipografa = new JButton("");
		toolBar.add(btnTipografa);
		
				btnTipografa.setIcon(new ImageIcon(main.class.getResource("/com/sun/javafx/scene/web/skin/Bold_16x16_JFX.png")));
				
				JButton button = new JButton("");
				toolBar.add(button);
				button.setIcon(new ImageIcon(main.class.getResource("/com/sun/javafx/scene/web/skin/FontColor_16x16_JFX.png")));
				
				JButton button_7 = new JButton("");
				toolBar.add(button_7);
				button_7.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/basurero.png")));
				
				JPanel panel_2 = new JPanel();
				toolBar.add(panel_2);
				FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				flowLayout.setHgap(0);
				flowLayout.setVgap(0);
				
				Choice choice = new Choice();
				panel_2.add(choice);
		
		JDesktopPane panel = new JDesktopPane();
		scrollPane.setViewportView(panel);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/Persona.png")));
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/asociacion.png")));
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText("       USO");
		editorPane.setForeground(Color.WHITE);
		editorPane.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane.setBackground(UIManager.getColor("Desktop.background"));
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setText("  DIAGRAMA");
		editorPane_1.setForeground(Color.WHITE);
		editorPane_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane_1.setBackground(UIManager.getColor("Desktop.background"));
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setText("   CASOS DE");
		editorPane_2.setForeground(Color.WHITE);
		editorPane_2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		editorPane_2.setBackground(UIManager.getColor("Desktop.background"));
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		editorPane_3.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_3.setForeground(Color.WHITE);
		editorPane_3.setText("___________________");
		
		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/oval.png")));
		
		JEditorPane dtrpnDiagrama = new JEditorPane();
		dtrpnDiagrama.setBackground(UIManager.getColor("Desktop.background"));
		dtrpnDiagrama.setForeground(Color.WHITE);
		dtrpnDiagrama.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		dtrpnDiagrama.setText("  DIAGRAMA ");
		
		JEditorPane dtrpnDeClases = new JEditorPane();
		dtrpnDeClases.setBackground(UIManager.getColor("Desktop.background"));
		dtrpnDeClases.setForeground(UIManager.getColor("Button.highlight"));
		dtrpnDeClases.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		dtrpnDeClases.setText("  DE CLASES");
		
		JEditorPane editorPane_4 = new JEditorPane();
		editorPane_4.setBackground(UIManager.getColor("Desktop.background"));
		editorPane_4.setForeground(UIManager.getColor("ComboBox.buttonHighlight"));
		editorPane_4.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		editorPane_4.setText("___________________");
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/agregacion.png")));
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/composicion.png")));
		
		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/herencia.png")));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(editorPane_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(editorPane_2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(editorPane_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(editorPane_4, 0, 0, Short.MAX_VALUE)
							.addComponent(dtrpnDeClases, 0, 0, Short.MAX_VALUE)
							.addComponent(dtrpnDiagrama, 0, 0, Short.MAX_VALUE)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(1)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(button_6)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(editorPane_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(editorPane_2, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(editorPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(2)
					.addComponent(button_5)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(dtrpnDiagrama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(dtrpnDeClases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(editorPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(button_6)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		paintComponents(getGraphics());
	}
}



