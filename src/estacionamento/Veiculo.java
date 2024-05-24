package estacionamento;

import java.util.ArrayList;
import java.util.List;

public record Veiculo(
    String placa,
    String cor,
    String modelo) {

    private static final String ARQUIVO = "veiculo.txt";

    public List<String> desconstruir() {
        return List.of(placa, cor, modelo);
    }

    public static Veiculo construir(List<String> listinha) {
        if (listinha.size() != 3) throw new IllegalArgumentException();
        return new Veiculo(
                listinha.get(0),
                listinha.get(1),
                listinha.get(2)
        );
    }

    public static List<Veiculo> construirTodos(List<String> listona) {
        if (listona.size() % 3 != 0) throw new IllegalArgumentException();
        var resultado = new ArrayList<Veiculo>(listona.size() / 3);
        for (int i = 0; i < listona.size(); i += 3) {
            var listinha = listona.subList(i, i + 3);
            var elemento = construir(listinha);
            resultado.add(elemento);
        }
        return resultado;
    }

    public static List<String> desconstruirTodos(List<Veiculo> todos) {
        var listona = new ArrayList<String>(todos.size() * 3);
        for (var v : todos) {
            listona.addAll(v.desconstruir());
        }
        return listona;
    }

    public static List<Veiculo> lerTudo() {
        var listona = Arquivo.lerLinhas(ARQUIVO);
        return Veiculo.construirTodos(listona);
    }

    public static void salvarTudo(List<Veiculo> tudo) {
        var listona = Veiculo.desconstruirTodos(tudo);
        Arquivo.escreverLinhas(listona, ARQUIVO);
    }

    public static Veiculo procurar(String placa) {
        for (var v : lerTudo()) {
            if (v.placa().equals(placa)) {
                return v;
            }
        }
        return null;
    }
}
