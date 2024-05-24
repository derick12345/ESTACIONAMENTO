package estacionamento;

import java.util.ArrayList;
import java.util.List;

public record Motorista(
    String nome,
    String cnh) {

    private static final String ARQUIVO = "motorista.txt";

    public List<String> desconstruir() {
        return List.of(nome, cnh);
    }

    public static Motorista construir(List<String> listinha) {
        if (listinha.size() != 2) throw new IllegalArgumentException();
        return new Motorista(
                listinha.get(0),
                listinha.get(1)
        );
    }
    public static List<Motorista> construirTodos(List<String> listona) {
        if (listona.size() % 2 != 0) throw new IllegalArgumentException();
        var resultado = new ArrayList<Motorista>(listona.size() / 2);
        for (int i = 0; i < listona.size(); i += 2) {
            var listinha = listona.subList(i, i + 2);
            var elemento = construir(listinha);
            resultado.add(elemento);
        }
        return resultado;
    }

    public static List<String> desconstruirTodos(List<Motorista> todos) {
        var listona = new ArrayList<String>(todos.size() * 2);
        for (var v : todos) {
            listona.addAll(v.desconstruir());
        }
        return listona;
    }

    public static List<Motorista> lerTudo() {
        var listona = Arquivo.lerLinhas(ARQUIVO);
        return Motorista.construirTodos(listona);
    }

    public static void salvarTudo(List<Motorista> tudo) {
        var listona = Motorista.desconstruirTodos(tudo);
        Arquivo.escreverLinhas(listona, ARQUIVO);
    }

    public static Motorista procurar(String cnh) {
        for (var m : lerTudo()) {
            if (m.cnh().equals(cnh)) {
                return m;
            }
        }
        return null;
    }
}
