package HalfEdge;

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
         JFrame frame = new JFrame("Half Edge");
        
        int x1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opção HalfEdge\n 1-Triangulo\n 2-cubo", "X1", JOptionPane.QUESTION_MESSAGE));
        if (x1 == 1) {
            //ler aquivo triangulo
            ArquivoHalfEdges A = new ArquivoHalfEdges("entrada.txt");//ler o arquivo TXT contendo um half-edge
            Principal_HalfEdge mainPanel = new Principal_HalfEdge(A.faces, A.vertices, A.halfEdges);
            frame.getContentPane().add(mainPanel);//adiciona o conteuno no frame
        } else if (x1 == 2) {
            //ler arquivo com o cubo
            ArquivoHalfEdges A = new ArquivoHalfEdges("entrada-cubo.txt");//ler o arquivo TXT contendo um half-edge
            Principal_HalfEdge mainPanel = new Principal_HalfEdge(A.faces, A.vertices, A.halfEdges);
            frame.getContentPane().add(mainPanel);//adiciona o conteuno no frame
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

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
