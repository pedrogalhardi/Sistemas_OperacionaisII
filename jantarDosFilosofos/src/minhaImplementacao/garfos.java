package minhaImplementacao;

public class garfos {

    private boolean[] garfosOcupados = new boolean[5];
    private Object[] monitors = new Object[5];

    public garfos() {
        for (int i = 0; i < 5; i++) {
            monitors[i] = new Object();
        }
    }

    public void pegar(filosofo f) {
        int chave = f.getChave();
        int garfoEsquerdo = chave;
        int garfoDireito = (chave + 1) % 5;

        synchronized (monitors[garfoEsquerdo]) {
            while (garfosOcupados[garfoEsquerdo]) {
                f.setStatus(2);
                try {
                    monitors[garfoEsquerdo].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            garfosOcupados[garfoEsquerdo] = true;
        }

        synchronized (monitors[garfoDireito]) {
            while (garfosOcupados[garfoDireito]) {
                f.setStatus(2);
                try {
                    monitors[garfoDireito].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            garfosOcupados[garfoDireito] = true;
        }

        System.out.println("Filosofo " + chave + " pegou garfos " + garfoEsquerdo + " e " + garfoDireito);
        f.setStatus(1);
    }

    public void liberar(filosofo f) {
        int chave = f.getChave();
        int garfoEsquerdo = chave;
        int garfoDireito = (chave + 1) % 5;

        synchronized (monitors[garfoEsquerdo]) {
            garfosOcupados[garfoEsquerdo] = false;
            monitors[garfoEsquerdo].notifyAll();
        }

        synchronized (monitors[garfoDireito]) {
            garfosOcupados[garfoDireito] = false;
            monitors[garfoDireito].notifyAll();
        }

        System.out.println("Filosofo " + chave + " liberou garfos " + garfoEsquerdo + " e " + garfoDireito);
    }
}
