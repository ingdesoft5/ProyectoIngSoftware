package javagui.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import Backend.Atributo;
import Backend.Metodo;

public class Clases  extends GroupLayout {
	private int Alto = 0;
	private int Ancho = 0;
	public Clases(Container host,int x,int y, String nombreClase, Stack<Metodo> metodos2, Stack<Atributo> atributos2){
		super(host);
		int ancho = 0;
		String[] metodos = new String[metodos2.size()];
		int auxM = metodos2.size();
		for(int i = 0 ; i<auxM ; i++){
			Metodo m = metodos2.pop();
			String parametros = "";
			for(int j = 0 ; j < m.parametros.size() ; j++){
				if(j == 0){
					parametros = parametros + m.parametros.get(j).nombre + ":" + m.parametros.get(j).tipo;
				}
				else{
					parametros = parametros + "," + m.parametros.get(j).nombre + ":" + m.parametros.get(j).tipo;
				}
			}
			String textMetodo = m.nombre +"(" +parametros + ")" + ":" + m.tipo;
			metodos[metodos.length-i-1] = textMetodo;
			ancho = Math.max(ancho,textMetodo.length());
		}
		String[] atributos = new String[atributos2.size()];
		int auxA = atributos2.size();
		for(int i = 0 ; i<auxA ; i++){
			Atributo a = atributos2.pop();
			String textAtributo = a.visibilidad + a.nombre + ":" + a.tipo;
			atributos[atributos.length-i-1] = textAtributo;
			ancho = Math.max(ancho, textAtributo.length());
		}
		Alto = 25+25*atributos.length+25*metodos.length+1;
		Ancho = ancho*7 + 5; 
		BufferedImage img = new BufferedImage(ancho*7+5,25+25*atributos.length+25*metodos.length+1, BufferedImage.TYPE_INT_RGB );
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0, ancho*7+4, 25+25*atributos.length+25*metodos.length);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, ancho*7+4, 25);
		g.drawRect(0, 25, ancho*7+4, 25*atributos.length);
		g.drawRect(0, 25+25*atributos.length, ancho*7+4, 25*metodos.length);
		g.drawString(nombreClase, 10, 17);
		int largoAux = (atributos.length)*25;
		for(int i = 0; i<atributos.length;i++){
			String text = atributos[i] + "";
			g.drawString(text, 10,25+25*i+17);
		}
		for(int i = 0; i<metodos.length;i++){
			String text = metodos[i] + "";
			g.drawString(text, 10, 25+(largoAux)+25*i+17);
		}
		//773 × 587
		if(ancho*7+5+x>773){
			if(y+25+25*atributos.length+25*metodos.length+1>587){
				host.setSize(ancho*7+5 + x, y+25+25*atributos.length+25*metodos.length+1);
				host.setPreferredSize(new Dimension(ancho*7+5 + x,y+25+25*atributos.length+25*metodos.length+1));
			}
			else{
				host.setSize(ancho*7+5 + x, 587);
				host.setPreferredSize(new Dimension(ancho*7+5 + x,587));
			}
		}
		else{
			if(y+25+25*atributos.length+25*metodos.length+1>587){
				host.setSize(773, y+25+25*atributos.length+25*metodos.length+1);
				host.setPreferredSize(new Dimension(773,y+25+25*atributos.length+25*metodos.length+1));
			}
			else{
				host.setSize(773, 587);
				host.setPreferredSize(new Dimension(773,587));
			}
		}

		ImageIcon icon = new ImageIcon(img);
		JLabel jl = new JLabel(icon);
		jl.setForeground(Color.black);
		jl.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
		jl.setName("clase");
		host.add(jl);

			// TODO Auto-generated constructor stub
		}

	public int altoClase(){
		return Alto;
	}
	public int anchoClase(){
		return Ancho;
	}
}
	

