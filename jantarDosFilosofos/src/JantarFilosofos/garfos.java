package JantarFilosofos;

public class garfos {

    private boolean[] vet_garfos = new boolean[5];

    public garfos() {
    }

    public synchronized void pegar(filosofo f) {
        int chave = f.getChave();

        while (vet_garfos[chave] || vet_garfos[(chave + 1) % 5]) {
            f.setStatus(2);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        vet_garfos[chave] = true;
        vet_garfos[(chave + 1) % 5] = true;

        System.out.println("Filosofo " + chave + " pegou garfos " + chave + " e " + (chave + 1) % 5);
        f.setStatus(1);
    }

    public synchronized void liberar(filosofo f) {
        int chave = f.getChave();
        int garfo1 = chave;
        int garfo2 = (chave + 1) % 5;

        vet_garfos[garfo1] = false;
        vet_garfos[garfo2] = false;

        System.out.println("Filosofo " + chave + " liberou garfos " + garfo1 + " e " + garfo2);

        notifyAll();
    }
}
