package HalfEdge;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ArquivoHalfEdges {
	public HashMap<String, Face> faces = new HashMap<String, Face>();
	public HashMap<String, Vertice> vertices = new HashMap<String, Vertice>();
	public HashMap<String, HalfEdge> halfEdges = new HashMap<String, HalfEdge>();
	
	public ArquivoHalfEdges(String path) throws IOException{
		LeituraArquivoHalfEdge(path);
	}
	
	private void LeituraArquivoHalfEdge(String path) throws IOException{
		FileInputStream stream = new FileInputStream(path);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();

		while(linha != null) {
			if(linha.length() > 0){
				String[] valores = linha.split(" ");
				if(valores[0].charAt(0) == 'f'){
					//f1 e1_1
					
					Face f = new Face();
					f.id = valores[0];
					f.halfEdge = valores[1];
					
					faces.put(f.id, f);
				}
				else if(valores[0].charAt(0) == 'e'){
					//e1_1 v1 e1_2 f1 e2_1
					
					HalfEdge h = new HalfEdge();
					h.id = valores[0];
					h.VerticeOriginal = valores[1];
					h.proximoHalfEdge = valores[2];
					h.face = valores[3];
					h.anteriorHalfEdge = valores[4];
					
					halfEdges.put(h.id, h);
				}
				else if(valores[0].charAt(0) == 'v'){
					//v1 1 1 e1_1
					
					Vertice v = new Vertice();
					v.id = valores[0];
					v.x = Integer.parseInt(valores[1]);
					v.y = Integer.parseInt(valores[2]);
					v.halfEdge = valores[3];
					
					vertices.put(v.id, v);
				}
			}
			
			linha = br.readLine();
		}
		
		for(String key : faces.keySet()){
			System.out.println(faces.get(key).id);
		}
		
		for(String key : vertices.keySet()){
			System.out.println(vertices.get(key).id);
		}
		
		for(String key : halfEdges.keySet()){
			System.out.println(halfEdges.get(key).id);
		}
	}
}
