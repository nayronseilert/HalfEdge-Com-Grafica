package HalfEdge;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class Principal_HalfEdge extends JPanel {

	private static final int MAX_COORDINATE = 14;
	private static final int PREF_W = 600;
	private static final int PREF_H = 400;
	private static final int BORDER_GAP = 30;
	private static final Color GRAPH_COLOR = Color.red;
	private static final Color GRAPH_POINT_COLOR = Color.blue;
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
	private static final int GRAPH_POINT_WIDTH = 12;
	private static final int Y_HATCH_CNT = MAX_COORDINATE;
	HashMap<String, Face> faces;
	HashMap<String, Vertice> vertices;
	HashMap<String, HalfEdge> halfEdges;

	public Principal_HalfEdge(HashMap<String, Face> faces, HashMap<String, Vertice> vertices, HashMap<String, HalfEdge> halfEdges) {
		this.faces = faces;
		this.vertices = vertices;
		this.halfEdges = halfEdges;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (MAX_COORDINATE - 1);
		double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_COORDINATE - 1);

		// Nessa linha cria o eixo X e Y
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

		
                //cria marcas de rachuras no eixo y
		for (int i = 0; i < Y_HATCH_CNT; i++) {
			int x0 = BORDER_GAP;
			int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
			int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
			int y1 = y0;
			g2.drawLine(x0, y0, x1, y1);
		}

		//cria rachuras para eixo x
		for (int i = 0; i < Y_HATCH_CNT; i++) {
			int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (Y_HATCH_CNT) + BORDER_GAP;
			int x1 = x0;
			int y0 = getHeight() - BORDER_GAP;
			int y1 = y0 - GRAPH_POINT_WIDTH;
			g2.drawLine(x0, y0, x1, y1);
		}
		
		for(String key : faces.keySet()){
			Face f = faces.get(key);
			String initialHalfEdge = f.halfEdge;
			HalfEdge h = halfEdges.get(initialHalfEdge);
			
			do{
				// Aqui imprime os vértice
				int x1 = (int) (vertices.get(h.VerticeOriginal).x * xScale + BORDER_GAP);
				int y1 = (int) ((MAX_COORDINATE - 1 - vertices.get(h.VerticeOriginal).y) * yScale + BORDER_GAP);
				ImprimirVerticeTela(x1, y1, g2);
				
				HalfEdge prox = halfEdges.get(h.proximoHalfEdge);
				int x2 = (int) (vertices.get(prox.VerticeOriginal).x * xScale + BORDER_GAP);
				int y2 = (int) ((MAX_COORDINATE - 1 - vertices.get(prox.VerticeOriginal).y) * yScale + BORDER_GAP);
				
				Stroke stroke1 = g2.getStroke();
				ImprimirArestaTela(x1, y1, x2, y2, g2);
				g2.setStroke(stroke1);
				
				h = prox;
			} while(!initialHalfEdge.equals(h.id));
		}
	}
        //essa função e responsavel por imprimir o vertice na tela
	private void ImprimirVerticeTela(int x1, int y1, Graphics2D g2) {
		g2.setColor(GRAPH_POINT_COLOR);
		g2.fillOval(x1 - GRAPH_POINT_WIDTH / 2, y1 - GRAPH_POINT_WIDTH / 2, GRAPH_POINT_WIDTH, GRAPH_POINT_WIDTH);
	}
	//Essa função imprime as arestas na tela
	private void ImprimirArestaTela(int x1, int y1, int x2, int y2, Graphics2D g2){
		g2.setColor(GRAPH_COLOR);
		g2.setStroke(GRAPH_STROKE);
		g2.drawLine(x1, y1, x2, y2);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}
      
}