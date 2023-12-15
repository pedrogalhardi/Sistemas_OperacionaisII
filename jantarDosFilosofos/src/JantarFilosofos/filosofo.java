package JantarFilosofos;

import java.util.Random;

public class filosofo extends Thread {

    private int filosofo;
    private int estado;
    private Jantar jantar;
    private int vezesComeu;
    private int vezesPensou;
    private long tempoSemComer;
    private long tempoMaximoEspera;
    private Random tempo = new Random();

    public filosofo(int chave, Jantar j) {
        this.filosofo = chave;
        this.jantar = j;
        this.vezesComeu = 0;
        this.vezesPensou = 0;
        this.tempoSemComer = 0;
        this.tempoMaximoEspera = 0;
    }

    public int getChave() {
        return filosofo;
    }

    public void setStatus(int i) {
        estado = i;
        switch (i) {
            case 0:
                jantar.SetInfo(filosofo, 0);
                break;
            case 1:
                jantar.SetInfo(filosofo, 1);
                break;
            case 2:
                jantar.SetInfo(filosofo, 2);
                break;
        }
    }

    public int getVezesComeu() {
        return vezesComeu;
    }

    public int getVezesPensou() {
        return vezesPensou;
    }

    public long getTempoSemComer() {
        return tempoSemComer;
    }

    public long getTempoMaximoEspera() {
        return tempoMaximoEspera;
    }

    private void pensando() {
        System.out.println("Filosofo " + filosofo + " esta pensando.");
        vezesPensou++;
        try {
            long tempoInicio = System.currentTimeMillis();
            Thread.sleep(tempo.nextInt(100, 500));
            long tempoFim = System.currentTimeMillis();
            long tempoPensando = tempoFim - tempoInicio;
            tempoSemComer += tempoPensando;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void comendo() {
        System.out.println("Filosofo " + filosofo + " esta comendo.");
        vezesComeu++;
        try {
            long tempoInicio = System.currentTimeMillis();
            Thread.sleep(tempo.nextInt(100, 500));
            long tempoFim = System.currentTimeMillis();
            long tempoComendo = tempoFim - tempoInicio;
            tempoMaximoEspera = Math.max(tempoMaximoEspera, tempoComendo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            setStatus(0);
            pensando();
            jantar.fork.pegar(this);
            setStatus(1);
            comendo();
            jantar.fork.liberar(this);
            setStatus(2);
        }
    }
}
