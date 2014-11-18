package javagui.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.Color;

import javax.swing.JComponent;
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

import Backend.GuardarComo;
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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.Toolkit;

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
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.GridLayout;

import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JComboBox;

import java.awt.event.MouseMotionAdapter;

public class main extends JFrame {
	boolean ovalo = false;
	boolean bold = false;
	boolean agregarNota = false;
	boolean agregarActor = false;
	boolean agregarRelExtension = false;
	boolean agregarRelActor = false;
	boolean agregarRelEspecializacion = false;
	boolean agregarCasoDeUso = false;
	boolean agregarRelAgregacion = false;
	boolean agregarRelAsociacion = false;
	boolean agregarRelComposicion = false;
	boolean agregarRelHerencia = false;
	boolean agregarRelDependencia = false;
	boolean agregarClase = false;
	boolean borrarElementos = false;
	int contadorNotas = 0;
	
	Container c = new Container();
	Map<String,int[]> diccionario = new HashMap<String,int[]>();

	private JPanel contentPane;
	JLayeredPane desktopPane = new JLayeredPane();

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
			//JTextArea textArea = new JTextArea();
			JTextPane textArea = new JTextPane();
			StyledDocument textPaneDoc = textArea.getStyledDocument();
			textArea.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");

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
					acerca.getContentPane().add(jp);
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
			
////////////NUEVO ARCHIVO/////////////////////////////////////////////////////////////////////////////////////////
			JMenu mnNuevo = new JMenu("Nuevo");
			mnArchivo.add(mnNuevo);
			
			JMenuItem mntmUml = new JMenuItem("UML");
			mntmUml.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NuevoUML(textArea);
				}
			});
			mnNuevo.add(mntmUml);
			
			JMenuItem mntmDiagramaDeClases = new JMenuItem("Diagrama de Clases");
			mntmDiagramaDeClases.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NuevoDiagramaClases(textArea);
				}
			});
			mnNuevo.add(mntmDiagramaDeClases);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			JLabel lblEditorXml = DefaultComponentFactory.getInstance().createLabel("Editor XML:");
			lblEditorXml.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			//JTextArea textArea = new JTextArea();
			JScrollPane scrollBar = new JScrollPane(textArea);
			
			JMenuItem mntmAbrirArchivo = new JMenuItem("Abrir archivo...");
			mntmAbrirArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AbrirArchivo(textArea);

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
			
///////GUARDAR COMO//////////////////////////////////////////////////////////////////////////////////////////////////
			JMenu mnGuardarComo = new JMenu("Guardar Como...");
			mnArchivo.add(mnGuardarComo);
			
			//GUARDAR COMO: XML
			JMenuItem mntmXml = new JMenuItem("XML");
			mntmXml.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Lector l = new Lector();
					String texto = textArea.getText();
					String comprobar = comprobarLectura(l, texto);
					if(comprobar != "error"){
						GuardarComo gc = new GuardarComo(); 
						gc.GuardarComoXML(texto);
					}
					else{
						VentanaAdvertencia(texto,"XML");
					}
				}
			});
			mnGuardarComo.add(mntmXml);
			
			//GUARDAR COMO: PNG
			JMenuItem mntmPng = new JMenuItem("Diagrama como imágen");
			mntmPng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuardarComo gc = new GuardarComo();
					JLayeredPane auxD = new JLayeredPane();
					c.removeAll();
					c.setSize(desktopPane.getSize());
					auxD.setSize(desktopPane.getSize());
					auxD.setVisible(true);
					auxD.setBackground(Color.white);
					c.setVisible(true);
					c.setBackground(Color.white);
					auxD = desktopPane;
					auxD.setBackground(Color.white);

					for(int i = auxD.getComponentCount()-1;i>=0;i--){
						if(auxD.getComponent(i).getName().contains("editorPaneN")){
							auxD.remove(i);
						}
					}
					c = auxD;
					gc.GuardarComoImagen(c);
				}
			});
			mnGuardarComo.add(mntmPng);
			

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			JDesktopPane desktopPane_1 = new JDesktopPane();
			scrollPane.setViewportView(desktopPane_1);
			
			//GUARDAR COMO: CARPETA
			JMenuItem mntmCrearCarpetaCon = new JMenuItem("Crear carpeta con ambos archivos");
			mntmCrearCarpetaCon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						String texto = textArea.getText();
						Lector l = new Lector();
						String comprobar = comprobarLectura(l, texto);
						if(comprobar != "error"){
							GuardarComo gc = new GuardarComo(); 
							gc.GuardarComoCarpeta(texto, c);
						}
						else{
							VentanaAdvertencia(texto,"carpeta");
					}
					}
			});
			mnGuardarComo.add(mntmCrearCarpetaCon);
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			JScrollPane scrollBar_1 = new JScrollPane(desktopPane);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(253, 245, 230));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			desktopPane.setFocusable(true);		
			
			desktopPane.setVisible(true);
			desktopPane.setBackground(Color.white);
	

			//CREAR DIAGRAMA
			JButton btnConvertir = new JButton("Crear diagrama");
			btnConvertir.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					desktopPane.removeAll();
					ImprimirDiagrama(textArea, desktopPane);
					//c = desktopPane;
					
				}
			});


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
			

			
			JEditorPane editorPane = new JEditorPane();
			editorPane.setText("  DIAGRAMA");
			editorPane.setEditable(false);
			editorPane.setForeground(Color.WHITE);
			editorPane.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			editorPane.setBackground(UIManager.getColor("Desktop.background"));
			editorPane.setBounds(1, 25, 61, 11);
			desktopPane_1.add(editorPane);
			
			JEditorPane editorPane_1 = new JEditorPane();
			editorPane_1.setText("   CASOS DE");
			editorPane_1.setEditable(false);
			editorPane_1.setForeground(Color.WHITE);
			editorPane_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			editorPane_1.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_1.setBounds(1, 37, 61, 11);
			desktopPane_1.add(editorPane_1);
			
			JEditorPane editorPane_2 = new JEditorPane();
			editorPane_2.setText("       USO");
			editorPane_2.setEditable(false);
			editorPane_2.setForeground(Color.WHITE);
			editorPane_2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			editorPane_2.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_2.setBounds(1, 49, 62, 11);
			desktopPane_1.add(editorPane_2);
			
			JEditorPane editorPane_3 = new JEditorPane();
			editorPane_3.setText("___________________");
			editorPane_3.setForeground(Color.WHITE);
			editorPane_3.setEditable(false);
			editorPane_3.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
			editorPane_3.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_3.setBounds(2, 61, 100, 7);
			desktopPane_1.add(editorPane_3);
			
			JButton bRelActor = new JButton("");
			bRelActor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = true;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelActor.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/asociacion.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelActor.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/asociacion.png")));
			bRelActor.setBounds(32, 72, 30, 32);
			desktopPane_1.add(bRelActor);
			bRelActor.setToolTipText("Relación con actor");
			
			JButton bRelEspecializacion = new JButton("");
			bRelEspecializacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = true;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelEspecializacion.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/herencia.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelEspecializacion.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/herencia.png")));
			bRelEspecializacion.setBounds(32, 105, 30, 32);
			desktopPane_1.add(bRelEspecializacion);
			bRelEspecializacion.setToolTipText("Relación de especialización");
			
			JButton bRelExtension = new JButton("");
			bRelExtension.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = true;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelExtension.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/punteada.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
					
				}
			});
			bRelExtension.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/punteada.png")));
			bRelExtension.setBounds(1, 72, 30, 32);
			desktopPane_1.add(bRelExtension);
			bRelExtension.setToolTipText("Relación de extensión o inclusión");
			
			JButton bActor = new JButton("");
			bActor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = true;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bActor.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/Persona.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bActor.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/Persona.png")));
			bActor.setBounds(1, 105, 30, 32);
			desktopPane_1.add(bActor);
			bActor.setToolTipText("Actor");
			
			JButton bCasodeUso = new JButton("");
			bCasodeUso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = true;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bCasodeUso.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/oval.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bCasodeUso.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/oval.png")));
			bCasodeUso.setBounds(0, 139, 62, 62);
			desktopPane_1.add(bCasodeUso);
			bCasodeUso.setToolTipText("Caso de uso");
			
			JEditorPane editorPane_4 = new JEditorPane();
			editorPane_4.setText("  DIAGRAMA");
			editorPane_4.setEditable(false);
			editorPane_4.setForeground(Color.WHITE);
			editorPane_4.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			editorPane_4.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_4.setBounds(1, 213, 61, 11);
			desktopPane_1.add(editorPane_4);
			
			
			JEditorPane dtrpnDeClases = new JEditorPane();
			dtrpnDeClases.setText("  DE CLASES");
			dtrpnDeClases.setEditable(false);
			dtrpnDeClases.setForeground(Color.WHITE);
			dtrpnDeClases.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			dtrpnDeClases.setBackground(UIManager.getColor("Desktop.background"));
			dtrpnDeClases.setBounds(1, 225, 61, 11);
			desktopPane_1.add(dtrpnDeClases);
			
			JEditorPane editorPane_5 = new JEditorPane();
			editorPane_5.setText("___________________");
			editorPane_5.setEditable(false);
			editorPane_5.setForeground(Color.WHITE);
			editorPane_5.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
			editorPane_5.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_5.setBounds(2, 236, 100, 7);
			desktopPane_1.add(editorPane_5);
			
			JButton bRelAgregacion = new JButton("");
			bRelAgregacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = true;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelAgregacion.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/agregacion.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			
			bRelAgregacion.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/agregacion.png")));
			bRelAgregacion.setBounds(1, 248, 30, 32);
			desktopPane_1.add(bRelAgregacion);
			bRelAgregacion.setToolTipText("Relación de agregación");
			
			JButton bRelAsociacion = new JButton("");
			bRelAsociacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = true;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelAsociacion.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/asociacion.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelAsociacion.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/asociacion.png")));
			bRelAsociacion.setBounds(32, 248, 30, 32);
			desktopPane_1.add(bRelAsociacion);
			bRelAsociacion.setToolTipText("Relación de asociación");
			
			JButton bRelComposicion = new JButton("");
			bRelComposicion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = true;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelComposicion.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/composicion.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelComposicion.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/composicion.png")));
			bRelComposicion.setBounds(1, 282, 30, 32);
			desktopPane_1.add(bRelComposicion);
			bRelComposicion.setToolTipText("Relación de composición");
			
			JButton bRelHerencia = new JButton("");
			bRelHerencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = true;
					agregarRelDependencia = false;
					agregarClase = false;
					bRelHerencia.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/herencia.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelHerencia.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/herencia.png")));
			bRelHerencia.setBounds(32, 282, 30, 32);
			desktopPane_1.add(bRelHerencia);
			bRelHerencia.setToolTipText("Relación de herencia");
			
			JButton bRelDependencia = new JButton("");
			bRelDependencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = true;
					agregarClase = false;
					bRelDependencia.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/punteada.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bRelDependencia.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/punteada.png")));
			bRelDependencia.setBounds(1, 315, 30, 32);
			desktopPane_1.add(bRelDependencia);
			bRelDependencia.setToolTipText("Relación de dependencia");
			
			JButton bClase = new JButton("");
			bClase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = false;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = true;
					bClase.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/caja.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bClase.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/caja.png")));
			bClase.setBounds(0, 347, 62, 62);
			desktopPane_1.add(bClase);
			bClase.setToolTipText("Clase");
			
			JEditorPane dtrpnNotas = new JEditorPane();
			dtrpnNotas.setText("  NOTAS");
			dtrpnNotas.setEditable(false);
			dtrpnNotas.setForeground(Color.WHITE);
			dtrpnNotas.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			dtrpnNotas.setBackground(UIManager.getColor("Desktop.background"));
			dtrpnNotas.setBounds(1, 421, 61, 11);
			desktopPane_1.add(dtrpnNotas);
			
			JEditorPane editorPane_6 = new JEditorPane();
			editorPane_6.setText("___________________");
			editorPane_6.setEditable(false);
			editorPane_6.setForeground(Color.WHITE);
			editorPane_6.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
			editorPane_6.setBackground(UIManager.getColor("Desktop.background"));
			editorPane_6.setBounds(2, 434, 100, 7);
			desktopPane_1.add(editorPane_6);
			
			JButton bNota = new JButton("");
			bNota.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarNota = true;
					agregarRelEspecializacion = false;
					agregarActor = false;
					agregarRelExtension = false;
					agregarRelActor = false;
					agregarCasoDeUso = false;
					agregarRelAgregacion = false;
					agregarRelAsociacion = false;
					agregarRelComposicion = false;
					agregarRelHerencia = false;
					agregarRelDependencia = false;
					agregarClase = false;
					bNota.setFocusPainted(true);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
		        	Image image = toolkit.getImage("src/javagui/resources/nota2.png");
		        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
					desktopPane.setCursor(c);
				}
			});
			bNota.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/nota2.png")));
			bNota.setToolTipText("Nota");
			bNota.setBounds(0, 444, 62, 62);
			desktopPane_1.add(bNota);
			
			desktopPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					if(agregarNota){
						String imgsrc = "";
						try {
							imgsrc = new File("src/javagui/resources/nota2.png").toURI().toURL().toExternalForm();
						} catch (MalformedURLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String html = "<img src=\""+imgsrc+"\" width=\"74\" height=\"85\">";
						String nombre = "editorPaneN"+String.valueOf(contadorNotas);
						JTextPane editorPane = new JTextPane();
						editorPane.setName(nombre);
						editorPane.setContentType("text/html");
						editorPane.setText(html +"<br> contenido");
						editorPane.setVisible(true);
						editorPane.setBounds(e.getX(), e.getY(), 70, 150);
						editorPane.addMouseListener(new MouseAdapter(){
							@Override
							public void mousePressed(MouseEvent e2){
								//textArea.setText("mousePressed");
								if(borrarElementos){
									//textArea.setText("borrarElemento");
									desktopPane.remove(editorPane);
									borrarElementos = false;
									desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
									desktopPane.updateUI();

								}
							}
						});
						desktopPane.add(editorPane);
						
						agregarNota = false;
						bNota.setFocusPainted(false);
						String text = textArea.getText();
						textArea.setText(text.concat("\n<Nota id = \"n"+contadorNotas + "\" content =\"contenido\"/>"));
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						int[] pos ={e.getX(), e.getY(), 70, 150};
						diccionario.put("n"+contadorNotas, pos);
						contadorNotas++;

					}

					else if(agregarActor){
						String imgsrc = "";
						try {
							imgsrc = new File("src/javagui/resources/man.png").toURI().toURL().toExternalForm();
						} catch (MalformedURLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String html = "<img src=\""+imgsrc+"\" width=\"74\" height=\"85\">";
						String nombre = "actor"+String.valueOf(contadorNotas);
						JTextPane editorPane = new JTextPane();
						editorPane.setName(nombre);
						editorPane.setContentType("text/html");
						editorPane.setText(html +"<br> nombre");
						editorPane.setVisible(true);
						editorPane.setBounds(e.getX(), e.getY(), 70, 150);
						editorPane.addMouseListener(new MouseAdapter(){
							@Override
							public void mousePressed(MouseEvent e2){
								textArea.setText("mousePressed");
								if(borrarElementos){
									textArea.setText("borrarElemento");
									desktopPane.remove(editorPane);
									borrarElementos = false;
									desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

								}
							/*	else{
								editorPane.addMouseListener(new MouseAdapter(){									
									@Override
									public void mouseReleased(MouseEvent e3){
										textArea.setText("mouseReleased");
										editorPane.setBounds(e3.getX(),e3.getY(), 70, 150);
									}
								});
								}*/
							}
						});
						desktopPane.add(editorPane);
						
						agregarActor = false;
						bActor.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelExtension){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/punteada.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						
						jlp.addMouseListener(new MouseAdapter(){
							@Override
							public void mousePressed(MouseEvent e){
								//textArea.setText("mousePressed");
								if(borrarElementos){
									//textArea.setText("borrarElemento");
									desktopPane.remove(e.getComponent());
									borrarElementos = false;
									desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

								}
							/*	else{
								editorPane.addMouseListener(new MouseAdapter(){									
									@Override
									public void mouseReleased(MouseEvent e3){
										textArea.setText("mouseReleased");
										editorPane.setBounds(e3.getX(),e3.getY(), 70, 150);
									}
								});
								}*/
							}
						});
						desktopPane.add(jlp);
						agregarRelExtension = false;
						bRelExtension.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

						
					}
					else if(agregarRelActor){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/asociacion.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelActor = false;
						bRelActor.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelEspecializacion){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/herencia.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelEspecializacion = false;
						bRelEspecializacion.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarCasoDeUso){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/oval.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarCasoDeUso = false;
						bCasodeUso.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelAgregacion){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/agregacion.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelAgregacion = false;
						bRelAgregacion.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelAsociacion){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/asociacion.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelAsociacion = false;
						bRelAsociacion.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelComposicion){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/composicion.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelComposicion = false;
						bRelComposicion.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelHerencia){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/herencia.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelHerencia = false;
						bRelHerencia.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarRelDependencia){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/punteada.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarRelDependencia = false;
						bRelDependencia.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					else if(agregarClase){
						desktopPane.setFocusable(true);
						JLayeredPane jlp = new JLayeredPane();
						ImageIcon ic = new ImageIcon("src/javagui/resources/caja2.png");
						//setSize(ic.getIconWidth(),ic.getIconHeight());
						Graphics g = desktopPane.getGraphics();
						g.drawImage(ic.getImage(),e.getX(),e.getY(),ic.getIconWidth(),ic.getIconHeight(), null);
						jlp.paint(g);
						desktopPane.add(jlp);
						agregarClase = false;
						bClase.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					}
					/*else if(borrarElementos){
						Component[] componentes = desktopPane.getComponents();
						for(int i = 0; i < componentes.length; i++){
							//componentes[i].is
						}
						
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						
					}*/
					else{
						//int position = onTop.isSelected() ? 0 : 1;
					    //desktopPane.setLayer(dukeLabel,
					      //                   layerList.getSelectedIndex(),
					        //                 position);

					}

				}

			});
			
			
//////////ESTILOS//////////////////////////////////////////////////////////////////////////////////
			JMenu mnNewMenu = new JMenu("Estilos");
			menuBar.add(mnNewMenu);
			
			JMenuItem mntmNormal = new JMenuItem("Normal");
			mntmNormal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea.setBackground(Color.white);
					textArea.setForeground(Color.black);
					contentPane.setBackground(new Color(253, 245, 230));
					desktopPane_1.setBackground(new Color(65,105,170));
					editorPane.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_1.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_2.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_3.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_4.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_5.setBackground(UIManager.getColor("Desktop.background"));
					editorPane_6.setBackground(UIManager.getColor("Desktop.background"));
					dtrpnDeClases.setBackground(UIManager.getColor("Desktop.background"));
					dtrpnNotas.setBackground(UIManager.getColor("Desktop.background"));
					StyleContext sc = StyleContext.getDefaultStyleContext();
					AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.black);
					textPaneDoc.getForeground(aset);
				}
			});
			mnNewMenu.add(mntmNormal);
			
			JMenuItem mntmProgramador = new JMenuItem("Programador");
			mntmProgramador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String text = textArea.getText();
					text = text + " ";
					textArea.setText(text);
					textArea.setBackground(Color.black);
					textArea.setForeground(Color.green);
					contentPane.setBackground(Color.gray);
					desktopPane_1.setBackground(Color.darkGray);
					editorPane.setBackground(Color.darkGray);
					editorPane_1.setBackground(Color.darkGray);
					editorPane_2.setBackground(Color.darkGray);
					editorPane_3.setBackground(Color.darkGray);
					editorPane_4.setBackground(Color.darkGray);
					editorPane_5.setBackground(Color.darkGray);
					editorPane_6.setBackground(Color.darkGray);
					dtrpnDeClases.setBackground(Color.darkGray);
					dtrpnNotas.setBackground(Color.darkGray);
					StyleContext sc = StyleContext.getDefaultStyleContext();
					AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.green);
					textPaneDoc.getForeground(aset);

				}
			});
			mnNewMenu.add(mntmProgramador);
//////////////////////////////////////////////////////////////////////////////////////////////////////
			
///////////BARRA DE HERRAMIENTAS SUPERIOR/////////////////////////////////////////////////////////////			
			JButton btnTipografa = new JButton("");
			toolBar.add(btnTipografa);
			
					btnTipografa.setIcon(new ImageIcon(main.class.getResource("/com/sun/javafx/scene/web/skin/Bold_16x16_JFX.png")));
					
					JComboBox<Serializable> comboBox_1 = new JComboBox<Serializable>();
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/black.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/red.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/blue.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/green.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/brown.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/cyan.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/gray.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/magenta.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/purple.png")));
					comboBox_1.addItem(new ImageIcon(("src/javagui/resources/yellow.png")));

					comboBox_1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if(comboBox_1.getSelectedIndex()==0){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.black);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							if(comboBox_1.getSelectedIndex()==1){
								//textArea.setForeground(Color.red);
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.red);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
								
							}
							else if(comboBox_1.getSelectedIndex()==2){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.blue);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
															}
							else if(comboBox_1.getSelectedIndex()==3){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.green);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
								
							}
							else if(comboBox_1.getSelectedIndex()==4){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.orange);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							else if(comboBox_1.getSelectedIndex()==5){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.cyan);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							else if(comboBox_1.getSelectedIndex()==6){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.gray);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							else if(comboBox_1.getSelectedIndex()==7){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.magenta);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							else if(comboBox_1.getSelectedIndex()==8){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.pink);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
							else if(comboBox_1.getSelectedIndex()==9){
								StyleContext sc = StyleContext.getDefaultStyleContext();
								AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, Color.yellow);
								textPaneDoc.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()-textArea.getSelectionStart(), aset, true);
							}
						}
					});
					toolBar.add(comboBox_1);
					
					JButton button_7 = new JButton("");
					button_7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							borrarElementos = true;
							Toolkit toolkit = Toolkit.getDefaultToolkit();
				        	Image image = toolkit.getImage("src/javagui/resources/basurero.png");
				        	Cursor c = toolkit.createCustomCursor(image , new Point(desktopPane.getX(),desktopPane.getY()), "img");
							desktopPane.setCursor(c);
							for(int i = 0; i<desktopPane.getComponentCount();i++){
								desktopPane.getComponent(i).setCursor(c);
							}
						}
					});
					toolBar.add(button_7);
					button_7.setIcon(new ImageIcon(main.class.getResource("/javagui/resources/basurero.png")));
					button_7.setToolTipText("Borrar todo");
					
					JPanel panel_2 = new JPanel();
					toolBar.add(panel_2);
					FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					flowLayout.setHgap(0);
					flowLayout.setVgap(0);
			

			
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						if(bold){
							textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.BOLD,13 ));
						}
						else if(!bold){
							textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN,13 ));
						}					
					}
				}
			});
			btnTipografa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bold = !bold;
					if(bold){
						textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.BOLD,13 ));
						btnTipografa.setBorderPainted(true);
					}
					else if(!bold){
						textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN,13 ));
						btnTipografa.setBorderPainted(false);
					}
				}
			});
			panel_2.add(comboBox);
			comboBox.addItem("Arial");
			comboBox.addItem("Calibri");
			comboBox.addItem("Cambria");
			comboBox.addItem("Tahoma");
			comboBox.addItem("Times New Roman");

			contentPane.setLayout(gl_contentPane);
			paintComponents(getGraphics());
			
////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

	public String comprobarLectura(Lector l, String xml){
		if(xml.contains("<Nota content =")){
			String[] arreglo = xml.split("<Nota ");
			xml = arreglo[0];
		}

		File f = new File("temp.xml");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write(xml);
			fw.close();
			l.leer(f);
			return l.tipo;
		}
		catch(IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "error";
		}
	}
	
	public void ImprimirDiagrama(JTextPane textArea, JLayeredPane desktopPane){
		desktopPane.removeAll();
		Lector l = new Lector();
		String xml = textArea.getText();
		String comprobar = comprobarLectura(l, xml);
		if(comprobar == "UCD"){
			for(int i = 0; i<l.diagCU.CasosDeUso.size();i++){
				Oval o = new Oval(desktopPane, 100*(i+1), 100*(i+1)+50, l.diagCU.CasosDeUso.elementAt(i).name);
				int[] pos ={150, 100*(i+1)+50, 280, 100};
				diccionario.put(l.diagCU.CasosDeUso.elementAt(i).id, pos);
			}
			int contadorp = 0, contadors = 0;
			for(int i = 0; i<l.diagCU.Actores.size();i++){
				
				if(l.diagCU.Actores.elementAt(i).type.equals("primary")){
					
					Actor a = new Actor(desktopPane, 0, contadorp + 20, l.diagCU.Actores.elementAt(i).name );
					contadorp++;
					desktopPane.updateUI();
					int[] pos ={50, contadorp+20, 0, 0};
					diccionario.put(l.diagCU.Actores.elementAt(i).id, pos);
				}
				else{
					Actor b = new Actor(desktopPane, 550, contadors + 20, l.diagCU.Actores.elementAt(i).name );
					contadors++;
					int[] pos ={550, contadorp+20, 0, 0};
					diccionario.put(l.diagCU.Actores.elementAt(i).id, pos);
				}				
			}
		}
		else if(comprobar == "CD"){
			for(int i = 0 ; i<l.diagC.Clases.size() ; i++){
				Clases c = new Clases(desktopPane, 100*(i+1), 100*(i+1)+50,l.diagC.Clases.elementAt(i).nombreClase,l.diagC.Clases.elementAt(i).metodos, l.diagC.Clases.elementAt(i).atributos );
				int[] pos ={100*(i+1), 100*(i+1)+50, 0, 0};
				diccionario.put(l.diagC.Clases.elementAt(i).id, pos);
			}
		
		}
		else if(comprobar == "error"){
			JFrame error = new JFrame();
			error.setSize(600, 80);
			JPanel jp2 = new JPanel();
			JLabel ep2 = new JLabel();
			ep2.setText("No se puede crear el diagrama, ya que se encontró un error en el XML");
			jp2.add(ep2);
			error.getContentPane().add(jp2);
			error.setVisible(true);
		}
	}

	public void AbrirArchivo(JTextPane textArea){
		MenuPrincipal mp = new MenuPrincipal();
		mp.Abrir();
		if(mp.l.tipo=="UCD"){
			String text = "<UseCaseDiagram name= \""+mp.l.diagCU.nombre+"\"> \n   <actors> \n";
			for(int i=0; i<mp.l.diagCU.Actores.size();i++){
				text = text + "      <actor type = \""+ (mp.l.diagCU.Actores.elementAt(i).type).toString()+"\" id = \""+ (mp.l.diagCU.Actores.elementAt(i).id).toString() +"\" name = \"" + (mp.l.diagCU.Actores.elementAt(i).name).toString()+"\" />"+"\n";
			}
			text = text + "   </actors> \n"+"   <usecases>\n";
			for(int i=0; i<mp.l.diagCU.CasosDeUso.size(); i++){
				text = text + "      <usecase id = \""+(mp.l.diagCU.CasosDeUso.elementAt(i).id).toString()+"\" name = \""+(mp.l.diagCU.CasosDeUso.elementAt(i).name).toString() + "\" />\n";
			}
			text = text + "   </usecases>\n"+"   <connections> \n";
			for(int i=0; i<mp.l.diagCU.Conexiones.size();i++){
				text = text + "      <connection type = \""+ (mp.l.diagCU.Conexiones.elementAt(i).type).toString()+"\" from = \"" + (mp.l.diagCU.Conexiones.elementAt(i).from).toString() + "\" to = \"" + (mp.l.diagCU.Conexiones.elementAt(i).to).toString() + "\"/>\n"; 
			}
			text = text + "   </connections>\n </UseCaseDiagram>";
			textArea.setText(text);
		}
		else{
			String text = "<ClassDiagram name = \"" + mp.l.diagC.nombre+"\"> \n";
			
			for(int i = 0; i<mp.l.diagC.Clases.size(); i++){
				text = text + "   <class id = \""+(mp.l.diagC.Clases.elementAt(i).id).toString()+"\" name = \""+ (mp.l.diagC.Clases.elementAt(i).nombreClase).toString()+"\"> \n";
				text = text + "      <attributes>\n";
				
				for(int j = 0; j<mp.l.diagC.Clases.elementAt(i).atributos.size(); j++){
					text = text + "         <att name = \""+(mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).nombre).toString()+"\" type = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).tipo).toString() + "\" visibility = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).visibilidad).toString() + "\"/>\n";
				}
				
				text = text + "      </attributes>\n";
				text = text + "     <methods>\n";				
				
				for(int j = 0; j<mp.l.diagC.Clases.elementAt(i).metodos.size(); j++){
					text = text + "         <method name = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).nombre).toString()+"\" type = \"" + (mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).tipo).toString()+"\">\n";
					for(int k = 0; k<mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.size();k++){
						text = text + "            <param name = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.elementAt(k).nombre).toString()+"\" type = \""+(mp.l.diagC.Clases.elementAt(i).metodos.elementAt(j).parametros.elementAt(k).tipo).toString()+"\"/>\n";
					}
					text = text + "         </method>\n";	
				}
				text = text + "      </methods>\n";
				text = text + "   </class>\n";
			}
			text = text + "</ClassDiagram>\n";
			textArea.setText(text);
		}
	}
	
	public void VentanaAdvertencia(String texto, String tipo){
		JFrame acerca = new JFrame();
		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setSize(90, 29);
		JButton bGuardar = new JButton("Guardar");
		bGuardar.setSize(90, 29);
		acerca.setSize(450, 80);
		JPanel jp = new JPanel();
		JLabel ep = new JLabel();
		ep.setText("Este XML presenta errores ¿Desea guardar de todas maneras?");
		jp.add(ep);
		jp.add(bCancelar);
		jp.add(bGuardar);
		acerca.getContentPane().add(jp);
		acerca.setVisible(true);
		bCancelar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				acerca.dispose();
			}
			
		});
		bGuardar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				acerca.dispose();
				GuardarComo gc = new GuardarComo(); 
				if(tipo=="XML"){
					gc.GuardarComoXML(texto);
				}
				else if(tipo=="carpeta"){
					gc.GuardarComoCarpeta(texto, desktopPane);
				}
				else{
					gc.GuardarComoImagen(desktopPane);
				}
			}
			
		});
	}
	
	public void NuevoUML(JTextPane textArea){
		String text = "<UseCaseDiagram name= \"nombre \"> \n   <actors> \n   </actors> \n"+"   <usecases>\n   </usecases>\n"+"   <connections> \n   </connections>\n </UseCaseDiagram>";
		textArea.setText(text);
	}
	
	public void NuevoDiagramaClases(JTextPane textArea){

		String text = "<ClassDiagram name = \"nombre\"> \n";
		text = text + "   <class id = \"idClase\" name = \"nombre\"> \n";
		text = text + "      <attributes>\n";
		text = text + "         <att name = \"nombreAtributo\" type = \"tipo\" visibility = \" visibilidad\">\n";
		text = text + "      </attributes>\n";
		text = text + "     <methods>\n";
		text = text + "         <method name = \"nombreMétodo\" type = \"tipo\">\n";
		text = text + "            <param name = \"nombreParametro\" type = \"tipo\"/>\n";
		text = text + "         </method>\n";
		text = text + "      </methods>\n";
		text = text + "   </class>\n";
		text = text + "</ClassDiagram>";
		textArea.setText(text);
		
	}
	
	public void CloseFrame(){
		super.dispose();		
	}
}



