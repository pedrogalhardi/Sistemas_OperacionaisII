/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package minhaImplementacao;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class Jantar extends JFrame {

    private static final long serialVersionUID = 8531554653309568273L;
    private Image fundo, arist, descar, pitagor, socrat, plat, mes;
    private filosofo f0, f1, f2, f3, f4;
    public garfos fork;
    private JPanel contentPane;

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Qual o tempo desejado de execucao?");

        //int time = scanner.nextInt();
        EventQueue.invokeLater(() -> {
            try {
                Jantar frame = new Jantar();
                frame.setVisible(true);

                new Thread(() -> {
                    long startTime = System.currentTimeMillis();
                    long endTime = startTime + 180_000;
                    //long endTime = startTime + time;

                    while (System.currentTimeMillis() < endTime) {

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    imprimirResultados(frame.f0);
                    imprimirResultados(frame.f1);
                    imprimirResultados(frame.f2);
                    imprimirResultados(frame.f3);
                    imprimirResultados(frame.f4);

                    System.exit(0);
                }).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void imprimirResultados(filosofo f) {
        System.out.println("Filosofo " + f.getChave()
                + " comeu " + f.getVezesComeu()
                + " vezes e pensou " + f.getVezesPensou() + " vezes.");

        System.out.println("Tempo total sem comer: " + f.getTempoSemComer() + " ms");
        System.out.println("Media de espera: " + (f.getTempoSemComer() / f.getVezesComeu()) + " ms por refeicao");
        System.out.println("Tempo maximo de espera: " + f.getTempoMaximoEspera() + " ms");

        System.out.println("--------------------------------------------------");
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(mes, 90, 230, null);

        graficos.drawImage(arist, 210, 125, null);
        graficos.drawImage(plat, 15, 295, null);
        graficos.drawImage(socrat, 340, 495, null);
        graficos.drawImage(pitagor, 405, 295, null);
        graficos.drawImage(descar, 90, 490, null);

    }

    public void jantar_WindowDestroy(Object target) {
        System.exit(0);
    }

    public void SetInfo(int chave, int estado) {

        if (chave == 0) {

            if (estado == 0) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\aristoteles.png");
                arist = referencia.getImage();

            } else if (estado == 1) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\aristoteles2.png");
                arist = referencia.getImage();

            } else if (estado == 2) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\aristoteles1.png");
                arist = referencia.getImage();
            }
        } else if (chave == 1) {
            if (estado == 0) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\platao.png");
                plat = referencia.getImage();

            } else if (estado == 1) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\platao2.png");
                plat = referencia.getImage();

            } else if (estado == 2) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\platao1.png");
                plat = referencia.getImage();

            }
        } else if (chave == 2) {

            if (estado == 0) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\descartes.png");
                descar = referencia.getImage();

            } else if (estado == 1) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\descartes2.png");
                descar = referencia.getImage();

            } else if (estado == 2) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\descartes1.png");
                descar = referencia.getImage();
            }
        } else if (chave == 3) {
            if (estado == 0) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\socrates.png");
                socrat = referencia.getImage();

            } else if (estado == 1) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\socrates2.png");
                socrat = referencia.getImage();

            } else if (estado == 2) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\socrates1.png");
                socrat = referencia.getImage();

            }

        } else if (chave == 4) {

            if (estado == 0) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\pitagoras.png");
                pitagor = referencia.getImage();

            } else if (estado == 1) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\pitagoras2.png");
                pitagor = referencia.getImage();

            } else if (estado == 2) {
                ImageIcon referencia = new ImageIcon("src\\Imagens\\pitagoras1.png");
                pitagor = referencia.getImage();

            }
        }

        repaint();
    }

    /**
     * Create the frame.
     */
    public Jantar() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Jantar.class.getResource("/Imagens/Icon.png")));

        ImageIcon referencia = new ImageIcon("src\\Imagens\\fundo.png");
        fundo = referencia.getImage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 515, 597);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        referencia = new ImageIcon("src\\Imagens\\mesa.png");

        fork = new garfos();
        f0 = new filosofo(0, this);
        f1 = new filosofo(1, this);
        f2 = new filosofo(2, this);
        f3 = new filosofo(3, this);
        f4 = new filosofo(4, this);

        f0.start();
        f1.start();
        f2.start();
        f3.start();
        f4.start();

    }

    public void start() {
    }

    public void stop() {
    }

    public void destroy() {
    }
}
