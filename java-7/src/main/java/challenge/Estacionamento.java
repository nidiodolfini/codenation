package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estacionamento {

    private static final int quantidadeDeVagas = 10;

    private List<Carro> carroLista;

    private List<Carro> getCarroLista() {
        return carroLista;
    }

    public void setCarroLista(Carro carro) {
        this.carroLista.add(carro);
    }

    public static int getQuantidadedevagas() {
        return quantidadeDeVagas;
    }

    public Estacionamento() {
        carroLista = new ArrayList<Carro>();
    }

    public int carrosEstacionados() {
        List<Carro> carroLista = getCarroLista();

        return carroLista.size();
    }

    public void estacionar(Carro carro) {
        List<Carro> carroLista = getCarroLista();
        List<Motorista> motoristaSeniorLista = getMotoristaSeniorLista();

        if (motoristaSeniorLista.size() > 0 &&
                motoristaSeniorLista.size() == carroLista.size() &&
                carro.getMotorista().getIdade() >= 60 )  {
            throw new EstacionamentoException();
        }

        if (carroLista.size() <= 9) {
            setCarroLista(carro);
        }
    }

    public boolean carroEstacionado(Carro carro) {
        return carro.getMotorista().getIdade() >= 60;

    }

    private List<Motorista> getMotoristaSeniorLista() {
        List<Carro> carrosEstacionados = getCarroLista();
        List<Motorista> motoristaSeniorLista = new ArrayList<Motorista>();

        if (carrosEstacionados.size() > 0) {
            motoristaSeniorLista = carrosEstacionados
                    .stream()
                    .filter(carroDaLista -> carroDaLista.getMotorista().getIdade() >= 60)
                    .map(carroDaLista -> carroDaLista.getMotorista())
                    .collect(Collectors.toList());
        }

        return motoristaSeniorLista;
    }
}
