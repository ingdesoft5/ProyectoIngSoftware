package javagui.views;

import java.awt.EventQueue;

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

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JDesktopPane;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Backend.Atributo;
import Backend.CasoDeUso;
import Backend.Clase;
import Backend.DiagramaCasosDeUso;
import Backend.GuardarComo;
import Backend.Lector;
import Backend.MenuPrincipal;
import Backend.Metodo;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.GridLayout;

import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;

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
	boolean borrar = false;
	int contadorNotas = 0;
	int xRel = 0;
	int yRel = 0;
	int contRel = 0;
	
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
			

			JLabel lblEditorXml = DefaultComponentFactory.getInstance().createLabel("Editor XML:");
			lblEditorXml.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			JScrollPane scrollBar = new JScrollPane(textArea);
	

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			JDesktopPane desktopPane_1 = new JDesktopPane();
			scrollPane.setViewportView(desktopPane_1);
			
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
					
				}
			});


			JToolBar toolBar = new JToolBar();
			
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
////////////NUEVO ARCHIVO/////////////////////////////////////////////////////////////////////////////////////////
			JMenu mnNuevo = new JMenu("Nuevo");
			mnArchivo.add(mnNuevo);
			
						JMenuItem mntmUml = new JMenuItem("UML");
						mntmUml.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								NuevoUML(textArea);
								desktopPane.removeAll();
								desktopPane.updateUI();
								desktopPane.setSize(773, 587);
								bRelExtension.setEnabled(true);
								bRelActor.setEnabled(true);
								bActor.setEnabled(true);
								bRelEspecializacion.setEnabled(true);
								bCasodeUso.setEnabled(true);
								bRelAgregacion.setEnabled(false);
								bRelAsociacion.setEnabled(false);
								bRelComposicion.setEnabled(false);
								bRelHerencia.setEnabled(false);
								bRelDependencia.setEnabled(false);
								bClase.setEnabled(false);
								
							}
						});
						mnNuevo.add(mntmUml);
						
						JMenuItem mntmDiagramaDeClases = new JMenuItem("Diagrama de Clases");
						mntmDiagramaDeClases.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								NuevoDiagramaClases(textArea);
								desktopPane.removeAll();
								desktopPane.updateUI();
								desktopPane.setSize(773, 587);
								bRelExtension.setEnabled(false);
								bRelActor.setEnabled(false);
								bActor.setEnabled(false);
								bRelEspecializacion.setEnabled(false);
								bCasodeUso.setEnabled(false);
								bRelAgregacion.setEnabled(true);
								bRelAsociacion.setEnabled(true);
								bRelComposicion.setEnabled(true);
								bRelHerencia.setEnabled(true);
								bRelDependencia.setEnabled(true);
								bClase.setEnabled(true);
							}
						});
						mnNuevo.add(mntmDiagramaDeClases);
			
			
			
			Component verticalStrut = Box.createVerticalStrut(7);
			mnArchivo.add(verticalStrut);
			
			JMenuItem mntmAbrirArchivo = new JMenuItem("Abrir archivo...");
			mntmAbrirArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tipoArchivo = AbrirArchivo(textArea);
					if(tipoArchivo=="UCD"){
						bRelExtension.setEnabled(true);
						bRelActor.setEnabled(true);
						bActor.setEnabled(true);
						bRelEspecializacion.setEnabled(true);
						bCasodeUso.setEnabled(true);
						bRelAgregacion.setEnabled(false);
						bRelAsociacion.setEnabled(false);
						bRelComposicion.setEnabled(false);
						bRelHerencia.setEnabled(false);
						bRelDependencia.setEnabled(false);
						bClase.setEnabled(false);	
					}
					else{
						bRelExtension.setEnabled(false);
						bRelActor.setEnabled(false);
						bActor.setEnabled(false);
						bRelEspecializacion.setEnabled(false);
						bCasodeUso.setEnabled(false);
						bRelAgregacion.setEnabled(true);
						bRelAsociacion.setEnabled(true);
						bRelComposicion.setEnabled(true);
						bRelHerencia.setEnabled(true);
						bRelDependencia.setEnabled(true);
						bClase.setEnabled(true);
					}

				}
			});
			
						mnArchivo.add(mntmAbrirArchivo);
			
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
						
						aumentarPanel(desktopPane, e.getX(), e.getY(), 70,150);
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
					else if(borrar){
						desktopPane.removeAll();
						desktopPane.setSize(773,587);
						desktopPane.setPreferredSize(new Dimension(773,587));
						desktopPane.updateUI();
						textArea.setText("");
						borrar = false;
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						desktopPane.updateUI();

					}
					else if(agregarActor){
						addActor("Actor", textArea, "type", "id", "name",e.getX(), e.getY());
	
							agregarActor = false;
							bActor.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

						}

					
					else if(agregarRelExtension){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel == 2){
							Conexion c = new Conexion(desktopPane, "extend", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelExtension = false;
							bRelExtension.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
							
					}
					else if(agregarRelActor){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "basic", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelActor = false;
							bRelActor.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarRelEspecializacion){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "isa", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelEspecializacion = false;
							bRelEspecializacion.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarCasoDeUso){
						addCasodeUso(desktopPane,textArea,"Caso de Uso", "id", "name", e.getX(), e.getY());
						agregarCasoDeUso = false;
						bCasodeUso.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					else if(agregarRelAgregacion){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "aggregation", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelAgregacion = false;
							bRelAgregacion.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarRelAsociacion){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "association", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelAsociacion = false;
							bRelAsociacion.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarRelComposicion){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "composition", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelComposicion = false;
							bRelComposicion.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarRelHerencia){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "inheritance", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelHerencia = false;
							bRelHerencia.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarRelDependencia){
						desktopPane.setFocusable(true);

						if(contRel == 0){
							xRel = e.getX();
							yRel = e.getY();
						}
						
						contRel++;
						if(contRel ==2){
							Conexion c = new Conexion(desktopPane, "dependency", xRel, yRel, e.getX(), e.getY());
							contRel = 0;
							agregarRelDependencia = false;
							bRelDependencia.setFocusPainted(false);
							desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
					else if(agregarClase){
						addClase(textArea, "Clase", "id", e.getX(), e.getY());
						desktopPane.setFocusable(true);
						
						agregarClase = false;
						bClase.setFocusPainted(false);
						desktopPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
							borrar = true;
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
		if(xml.contains("<Nota id =")){
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
	int x = 0;
	
	public void ImprimirDiagrama(JTextPane textArea, JLayeredPane desktopPane){
		desktopPane.removeAll();
		Lector l = new Lector();
		String xml = textArea.getText();
		String comprobar = comprobarLectura(l, xml);
		
		if(comprobar == "UCD"){
			dibujados.add(l.diagCU.CasosDeUso.elementAt(0));
			Oval o = new Oval(desktopPane, 350, 20, l.diagCU.CasosDeUso.elementAt(0).name);
			int[] pos1 ={350, 20, 280, 100};
			diccionario.put(l.diagCU.CasosDeUso.elementAt(0).id.trim(), pos1);
			dibujarDC(l.diagCU, l.diagCU.CasosDeUso.elementAt(0),150);
			
			int contadorp = 0, contadors = 0;
			for(int i = 0; i<l.diagCU.Actores.size();i++){
				
				if(l.diagCU.Actores.elementAt(i).type.equals("primary")){
					Actor a = new Actor(desktopPane, 0, contadorp*100, l.diagCU.Actores.elementAt(i).name );
					aumentarPanel(desktopPane,0,contadorp+20,50,50);
					contadorp++;
					desktopPane.updateUI();
					int[] pos ={0, contadorp+20, 50, 50};
					diccionario.put(l.diagCU.Actores.elementAt(i).id.trim(), pos);
					for(int j = 0; j<l.diagCU.Conexiones.size();j++){
						if(l.diagCU.Actores.elementAt(i).id.trim().equals(l.diagCU.Conexiones.elementAt(j).from.trim())){
							Conexion c = new Conexion(desktopPane, "basic", 20,(contadorp-1)*100+15,diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[0],50+diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[1]);
						}
					}
				}
				else{
					Actor b = new Actor(desktopPane, x + 100, contadors*100, l.diagCU.Actores.elementAt(i).name );
					aumentarPanel(desktopPane,x+100,contadors*100,150,100);
					contadors++;
					int[] pos ={x+100, contadors*100, 150, 100};
					diccionario.put(l.diagCU.Actores.elementAt(i).id.trim(), pos);
					for(int j = 0; j<l.diagCU.Conexiones.size();j++){
						if(l.diagCU.Actores.elementAt(i).id.trim().equals(l.diagCU.Conexiones.elementAt(j).from.trim())){
							Conexion c = new Conexion(desktopPane, "basic",x+100-25,(contadors-1)*100+15, diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[0],50+diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[1]);
						}
					}
				}				
			}
			for(int i = 0; i<l.diagCU.CasosDeUso.size();i++){
				for(int j = 0 ; j<l.diagCU.Conexiones.size(); j++){
					if(l.diagCU.CasosDeUso.elementAt(i).id.trim().equals(l.diagCU.Conexiones.elementAt(j).from.trim())){
						Conexion c = new Conexion(desktopPane, l.diagCU.Conexiones.elementAt(j).type.trim(), diccionario.get(l.diagCU.CasosDeUso.elementAt(i).id.trim())[0], diccionario.get(l.diagCU.CasosDeUso.elementAt(i).id.trim())[1], diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[0],diccionario.get(l.diagCU.Conexiones.elementAt(j).to.trim())[1]);
				}
				}
			}
		}
		else if(comprobar == "CD"){
			boolean arriba = true;
			int xArriba = 100;
			int xAbajo = 100;
			int yAbajo = 0;
			for(int i = 0 ; i<l.diagC.Clases.size() ; i++){
				if(arriba){
					Clases c = new Clases(desktopPane, xArriba, 130,l.diagC.Clases.elementAt(i).nombreClase,l.diagC.Clases.elementAt(i).metodos, l.diagC.Clases.elementAt(i).atributos );
					yAbajo = c.altoClase() + 270;
					xArriba = xArriba + c.anchoClase() + 130;
					arriba = false;
				}
				else{
					Clases c = new Clases(desktopPane,xAbajo , yAbajo,l.diagC.Clases.elementAt(i).nombreClase,l.diagC.Clases.elementAt(i).metodos, l.diagC.Clases.elementAt(i).atributos );
					xAbajo = xAbajo + c.anchoClase() + 130;
					arriba = true;
				}
				int[] pos ={100*(i+1), 100*(i+1)+50, 0, 0};
				diccionario.put(l.diagC.Clases.elementAt(i).id.trim(), pos);

			}
			for(int j = 0 ; j<l.diagC.Clases.size(); j++){
				for(int k = 0; k<l.diagC.Clases.size();k++){
					if(l.diagC.Conexiones.elementAt(j).to.trim().equals(l.diagC.Clases.elementAt(k).id.trim())){
						if(k>j){
							if(j%2 == 0){ //j par
								if(k%2 == 0){ //k par
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								}
								else{ //k impar
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								
								}
							}
							else{ //j impar
								if(k%2 != 0){ //k impar
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
								}
								else{ //k par
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								
								}
							}
						}
						else{ //k<=j
							if(j%2 == 0){ //j par
								if(k%2 == 0){ //k par
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								}
								else{ //k impar
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-40);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								
								}
							}
							else{ //j impar
								if(k%2 != 0){ //k impar
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]-60);
								}
								else{ //k par
									Conexion c = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40);
									Conexion c2 = new Conexion(desktopPane, l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+40);
									Conexion c3 = new Conexion(desktopPane,l.diagC.Conexiones.elementAt(j).tipo,diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1],diccionario.get(l.diagC.Conexiones.elementAt(j).to.trim())[0]+20,diccionario.get(l.diagC.Conexiones.elementAt(j).from.trim())[1]+60);
								
								}
							}
						}
						
					}
				}
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

	public String AbrirArchivo(JTextPane textArea){
		desktopPane.removeAll();
		desktopPane.updateUI();
		desktopPane.setSize(773, 587);		
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
			return "UCD";
		}
		else{
			String text = "<ClassDiagram name = \"" + mp.l.diagC.nombre+"\"> \n";
			
			for(int i = 0; i<mp.l.diagC.Clases.size(); i++){
				text = text + "   <class id = \""+(mp.l.diagC.Clases.elementAt(i).id).toString()+"\" name = \""+ (mp.l.diagC.Clases.elementAt(i).nombreClase).toString()+"\"> \n";
				text = text + "      <attributes>\n";
				
				for(int j = 0; j<mp.l.diagC.Clases.elementAt(i).atributos.size(); j++){
					text = text + "         <att name = \""+(mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).nombre).toString()+"\" type = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).tipo).toString() + "\" visibility = \"" + (mp.l.diagC.Clases.elementAt(i).atributos.elementAt(j).visibilidad).toString() + "\" />\n";
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
			return "CD";
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
		text = text + "         <att name = \"nombreAtributo\" type = \"tipo\" visibility = \" visibilidad\"/>\n";
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

	public void aumentarPanel(JLayeredPane host, int posX, int posY, int ancho, int alto){
		//773 × 587
		if(Math.max(posX + ancho,host.getSize().width)>773){
			if(alto>587){
				host.setSize(Math.max(posX + ancho,host.getSize().width),Math.max(posY + alto,host.getSize().height));
				host.setPreferredSize(new Dimension(Math.max(posX + ancho,host.getSize().width),Math.max(posY + alto,host.getSize().height)));
			}
			else{
				host.setSize(Math.max(posX + ancho,host.getSize().width), 587);
				host.setPreferredSize(new Dimension(Math.max(posX + ancho,host.getSize().width),587));
			}
		}
		else{
			if(Math.max(posY + alto,host.getSize().height)>587){
				host.setSize(773, Math.max(posY + alto,host.getSize().height));
				host.setPreferredSize(new Dimension(773,Math.max(posY + alto,host.getSize().height)));
			}
			else{
				host.setSize(773, 587);
				host.setPreferredSize(new Dimension(773,587));
			}
		}
	}
	
	int i = 0;
	Stack<CasoDeUso> dibujados = new Stack<CasoDeUso>();
	public void dibujarDC(DiagramaCasosDeUso casos, CasoDeUso casoDeUso, int distY){

		Stack<CasoDeUso> conectado = new Stack<CasoDeUso>();
			int conexiones = 0;
			for(int j = 0; j<casos.Conexiones.size() ; j++){
				if((casos.Conexiones.get(j).from.trim()).equals(casoDeUso.id.trim())){
					for(int k = 0; k<casos.CasosDeUso.size(); k++){
						if((casos.CasosDeUso.elementAt(k).id.trim()).equals(casos.Conexiones.get(j).to.trim())){
							if(dibujados.indexOf(casos.CasosDeUso.elementAt(k)) == -1){
								conectado.add(casos.CasosDeUso.elementAt(k));
								dibujados.add(casos.CasosDeUso.elementAt(k));
								conexiones++;
							}
						}
					}
				}
				if((casos.Conexiones.get(j).to.trim()).equals(casoDeUso.id.trim())){
					for(int k = 0; k<casos.CasosDeUso.size(); k++){
						if((casos.CasosDeUso.elementAt(k).id.trim()).equals(casos.Conexiones.get(j).from.trim())){
							if(dibujados.indexOf(casos.CasosDeUso.elementAt(k)) == -1){
							conectado.add(casos.CasosDeUso.elementAt(k));
							dibujados.add(casos.CasosDeUso.elementAt(k));
							conexiones++;
							}
						}
					}
				}
				
			}
			int auxY = 200;
			for(int m = 0; m< conectado.size(); m++){
				if(conexiones == 1){
					DiagramaCasosDeUso auxC = casos;
					Oval o = new Oval(desktopPane, 350, distY, conectado.elementAt(m).name);
					aumentarPanel(desktopPane,350,distY,280,100);
					x = Math.max(x, 350+280);
					int[] pos ={350, distY, 280, 100};
					diccionario.put(conectado.elementAt(m).id.trim(), pos);
					dibujarDC(casos, conectado.elementAt(m),distY+150);
				}
				else{
					Oval o = new Oval(desktopPane, auxY, distY, conectado.elementAt(m).name);
					aumentarPanel(desktopPane,auxY ,distY ,280 ,100);
					x = Math.max(x, auxY + 280);
					int[] pos ={auxY, distY, 280, 100};
					diccionario.put(conectado.elementAt(m).id.trim(), pos);
					auxY = auxY +350;
					dibujarDC(casos, conectado.elementAt(m),distY+150);
				}
			}
			
			
		
		
	}
	boolean agregado = false;
	String auxNombre = "";
	public boolean addActor( String elemento, JTextPane textArea, String s1, String s2, String s3, int x, int y){
		JFrame agregarE = new JFrame();
		JPanel jp = new JPanel();
		Box b = Box.createVerticalBox();
		
		JLabel ep = new JLabel();
		ep.setText("Agregar " + elemento + ":");
		b.add(ep);
		
		JTextField textField = new JTextField(s1);
		textField.setSize(200,120);
		b.add(textField);

		JTextField textField2 = new JTextField(s2);
		textField2.setSize(200,120);
		b.add(textField2);

		JTextField textField3 = new JTextField(s3);
		textField3.setSize(200,120);
		b.add(textField3);

		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setSize(90, 29);
		JButton bCrear = new JButton("Crear Elemento");
		bCrear.setSize(90, 29);
		agregarE.setSize(300, 200);
		JPanel pAux = new JPanel();
		pAux.add(bCancelar);
		pAux.add(bCrear);
		b.add(pAux);
		jp.add(b);
		
		agregarE.getContentPane().add(jp);
		agregarE.setVisible(true);
		bCancelar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregado = false;
				agregarE.dispose();
			}
			
		});
		bCrear.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregarE.dispose();
				Backend.Actor a = new Backend.Actor(textField.getText(), textField2.getText(), textField3.getText());
				ImageIcon ic = new ImageIcon("src/javagui/resources/man.png");
				JLabel jl = new JLabel(textField3.getText(),ic,JLabel.CENTER);
				jl.setVerticalTextPosition(JLabel.BOTTOM);
				jl.setHorizontalTextPosition(JLabel.CENTER);
				jl.setForeground(Color.black);
				jl.setName("actor");
				jl.setBounds(x,y,ic.getIconWidth()+100,ic.getIconHeight()+50);
				desktopPane.add(jl);
				String text = textArea.getText();
				if(text != "" && text.contains("<actors>")){
					String[] textAuxiliar = text.split("<actors>");
					text = textAuxiliar[0] + "<actors> <actor type = \"" + textField.getText() + "\" id = \"" + textField2.getText() + "\" name = \"" + textField3.getText() +"\" />"+textAuxiliar[1];
				}
				else{
					text ="<actors> <actor type = \"" + textField.getText() + "\" id = \"" + textField2.getText() + "\" name = \"" + textField3.getText() +"\" /> </actors>";
						
				}
				textArea.setText(text);
			}
			
			
		});
		return agregado;
	}
	public void addClase( JTextPane textArea, String elemento, String s1,  int x, int y){
		JFrame agregarE = new JFrame();
		JPanel jp = new JPanel();
		Box b = Box.createVerticalBox();
		
		JLabel ep = new JLabel();
		ep.setText("Agregar " + elemento + ":");
		b.add(ep);
		
		JTextField textField = new JTextField(s1);
		textField.setSize(200,120);
		b.add(textField);
		JTextField textField8 = new JTextField("nombre");
		b.add(textField8);

		JTextField textField2 = new JTextField("nombre");
		textField2.setSize(200,120);
		b.add(textField2);
		JTextField textField3 = new JTextField("tipo");
		textField3.setSize(200,120);
		b.add(textField3);
		JTextField textField4 = new JTextField("visibilidad(+/-)");
		textField4.setSize(200,120);
		b.add(textField4);
		JButton bAtributos = new JButton("Agregar atributo");
		bAtributos.setSize(90,29);
		bAtributos.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JLabel jA = new JLabel("<att name = \""+ textField2.getText() + "\" type = \"" + textField3.getText() + "\" visibility = \"" + textField4.getText()+"\"/>");
				b.add(jA);
				textField2.setText("nombre");
				textField3.setText("tipo");
				textField4.setText("visibilidad(+/-)");
			}
			
		});
		b.add(bAtributos);
		
		JTextField textField5 = new JTextField("nombre");
		textField5.setSize(200,120);
		b.add(textField5);
		JTextField textField6 = new JTextField("tipo");
		textField6.setSize(200,120);
		b.add(textField6);

		JButton bMetodos = new JButton("Agregar método");
		bMetodos.setSize(90,29);
		
		bMetodos.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JLabel jM = new JLabel("<method name = \""+ textField5.getText() + "\" type = \"" + textField6.getText() + "\">");
				b.add(jM);
				textField5.setText("nombre");
				textField6.setText("tipo");
			}
			
		});
		b.add(bMetodos);
		
		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setSize(90, 29);
		JButton bCrear = new JButton("Crear Elemento");
		bCrear.setSize(90, 29);
		agregarE.setSize(500, 400);
		JPanel pAux = new JPanel();
		pAux.add(bCancelar);
		pAux.add(bCrear);
		b.add(pAux);
		jp.add(b);
		
		agregarE.getContentPane().add(jp);
		agregarE.setVisible(true);
		bCancelar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregado = false;
				agregarE.dispose();
			}
			
		});
		bCrear.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregarE.dispose();
				Clase c = new Clase(textField2.getText(), textField.getText());
				Stack<Backend.Metodo> m = new Stack<Backend.Metodo>();
				m.push(new Backend.Metodo(textField5.getText(), textField6.getText()));
				Stack<Backend.Atributo> a = new Stack<Backend.Atributo>();
				 a.push(new Backend.Atributo(textField2.getText(), textField3.getText(), textField4.getText()));
				Clases clase = new Clases(desktopPane, x, y, textField2.getText(), m,a);
				String text = textArea.getText();
				if(text != "" && text.contains("</ClassDiagram>")){
					String[] textAuxiliar = text.split("</ClassDiagram>");
					text = textAuxiliar[0] + "<class id = \""+ textField.getText() + "\" name = \"" + textField8.getText()+ "\">" + "<attributes>" + "<att name = \""+ textField2.getText() + "\" type = \"" + textField3.getText() + "\" visibility = \"" + textField4.getText()+"\"/>" + "</attributes> <methods>" + "<method name = \""+ textField5.getText() + "\" type = \"" + textField6.getText() + "\">"+ "</methods> </class> </ClassDiagram>";
				}
				else{
					text = "<ClassDiagram> <class id = \""+ textField.getText() + "\" name = \"" + textField8.getText()+ "\">" + "<attributes>" + "<att name = \""+ textField2.getText() + "\" type = \"" + textField3.getText() + "\" visibility = \"" + textField4.getText()+"\"/>" + "</attributes> <methods>" + "<method name = \""+ textField5.getText() + "\" type = \"" + textField6.getText() + "\">"+ "</methods> </class> </ClassDiagram>";
					
				}
				textArea.setText(text);
			}
			
			
		});
	}
	public void addCasodeUso(JLayeredPane desktopoPane, JTextPane textArea,  String elemento, String s1, String s2, int x, int y){
		JFrame agregarE = new JFrame();
		JPanel jp = new JPanel();
		Box b = Box.createVerticalBox();
		
		JLabel ep = new JLabel();
		ep.setText("Agregar " + elemento + ":");
		b.add(ep);
		
		JTextField textField = new JTextField(s1);
		textField.setSize(200,120);
		b.add(textField);

		JTextField textField2 = new JTextField(s2);
		textField2.setSize(200,120);
		b.add(textField2);

		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setSize(90, 29);
		JButton bCrear = new JButton("Crear Elemento");
		bCrear.setSize(90, 29);
		agregarE.setSize(300, 200);
		JPanel pAux = new JPanel();
		pAux.add(bCancelar);
		pAux.add(bCrear);
		b.add(pAux);
		jp.add(b);
		
		agregarE.getContentPane().add(jp);
		agregarE.setVisible(true);
		bCancelar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregado = false;
				agregarE.dispose();
			}
			
		});
		bCrear.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				agregarE.dispose();
				BufferedImage img = new BufferedImage( 280, 100, BufferedImage.TYPE_INT_ARGB );
				Graphics g = img.getGraphics();

				g.setColor( Color.BLACK );
				g.fillOval( 2, 2, 276, 96 );
				g.setColor( Color.WHITE );
				g.fillOval( 3, 3, 274, 94 );
				g.setColor(Color.BLACK);
				g.drawString(textField2.getText(),50,50);
				
				ImageIcon icon = new ImageIcon( img );
				JLabel jl = new JLabel(icon);
				jl.setForeground(Color.black);
				jl.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
				jl.setName("oval");

				jl.setBounds(x,y,icon.getIconWidth()+100,icon.getIconHeight()+50);
				desktopPane.add(jl);
				String text = textArea.getText();
				if(text != "" && text.contains("<usecases>")){
					String[] textAuxiliar = text.split("<usecases>");
					text = textAuxiliar[0] + "<usecases> <usecase id = \"" + textField.getText() + "\" name = \"" + textField2.getText() + "\" />"+textAuxiliar[1];
				}
				else{
					text = "<usecases> <usecase id = \"" + textField.getText() + "\" name = \"" + textField2.getText() + "\" /></usecases> ";
	
				}
				textArea.setText(text);
			}
			
			
		});
	}
	public void CloseFrame(){
		super.dispose();		
	}
}



