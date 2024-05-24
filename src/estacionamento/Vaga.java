package estacionamento;

import java.util.ArrayList;
import java.util.List;

public record Vaga(
    int numero,
    String andar,
    Veiculo estacionado,
    Motorista dono)
{

    private static final String ARQUIVO = "vaga.txt";

    public boolean ocupada() {
        return estacionado != null;
    }

    public String nomeMotorista() {
        if (dono == null) return "[Vazio]";
        return dono.nome();
    }

    public String cnhMotorista() {
        if (dono == null) return "[Vazio]";
        return dono.cnh();
    }

    public String placaVeiculo() {
        if (estacionado == null) return "[Vazio]";
        return estacionado.placa();
    }

    public List<String> desconstruir() {
        String placa = estacionado == null ? "" : estacionado.placa();
        String cnh = dono == null ? "" : "" + dono.cnh();
        return List.of("" + numero, andar, placa, cnh);
    }

    public static Vaga construir(List<String> listinha) {
        if (listinha.size() != 4) throw new IllegalArgumentException();

        var placa = listinha.get(2);
        Veiculo v = Veiculo.procurar(placa);

        var cnh = listinha.get(3);
        Motorista m = Motorista.procurar(cnh);

        return new Vaga(
                Integer.parseInt(listinha.get(0)),
                listinha.get(1),
                v,
                m
        );
    }

    public static List<Vaga> construirTodos(List<String> listona) {
        if (listona.size() % 4 != 0) throw new IllegalArgumentException();
        var resultado = new ArrayList<Vaga>(listona.size() / 4);
        for (int i = 0; i < listona.size(); i += 4) {
            var listinha = listona.subList(i, i + 4);
            var elemento = construir(listinha);
            resultado.add(elemento);
        }
        return resultado;
    }

    public static List<String> desconstruirTodos(List<Vaga> todos) {
        var listona = new ArrayList<String>(todos.size() * 4);
        for (var v : todos) {
            listona.addAll(v.desconstruir());
        }
        return listona;
    }

    public static List<Vaga> lerTudo() {
        var listona = Arquivo.lerLinhas(ARQUIVO);
        return Vaga.construirTodos(listona);
    }

    public static void salvarTudo(List<Vaga> tudo) {
        var listona = Vaga.desconstruirTodos(tudo);
        Arquivo.escreverLinhas(listona, ARQUIVO);
    }

    public static Vaga procurar(int numero) {
        for (var v : lerTudo()) {
            if (v.numero() == numero) {
                return v;
            }
        }
        return null;
    }

    public static Vaga procurarVeiculo(String placa) {
        for (var v : lerTudo()) {
            if (v.placaVeiculo().equals(placa)) {
                return v;
            }
        }
        return null;
    }
}
