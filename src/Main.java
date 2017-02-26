
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nayron
 */
public class Main {

    //Essa função e responsavel por criar o GUI
    private static void CriarTela() throws IOException {

        ArquivoHalfEdges A = new ArquivoHalfEdges("entrada.txt");//ler o arquivo TXT contendo um half-edge

        Principal_HalfEdge mainPanel = new Principal_HalfEdge(A.faces, A.vertices, A.halfEdges);

        JFrame frame = new JFrame("Half Edge");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);//adiciona o conteuno no frame
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
          // Cria um JFrame
    JFrame frame = new JFrame("JOptionPane exemplo");
Object[] itens = { "Cubo", "PERA", "BANANA" };
      Object selectedValue = JOptionPane.showInputDialog(null,
          "Escolha um item", "Opçao",
              JOptionPane.INFORMATION_MESSAGE, null,
                  itens, itens [0]); //

        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    CriarTela();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
