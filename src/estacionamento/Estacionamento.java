package estacionamento;
// Projeto integrador: Derick Bueno Silva, Izabela Pereira Andrade Cruz e Ryan da Silva - TURMA E
import java.util.Scanner;

public class Estacionamento {
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("====== MENU DE OPCOES ======");
            System.out.println("1 - Cadastrar Vagas");
            System.out.println("2 - Cadastro de Condutores");
            System.out.println("3 - Cadastro de veiculos");
            System.out.println("4 - Estacionar veiculo");
            System.out.println("5 - Retirar veiculo");
            System.out.println("6 - Listar condutores");
            System.out.println("7 - Listar veiculos");
            System.out.println("8 - Procurar veiculo");
            System.out.println("9 - Procurar motoristas");
            System.out.println("10 - Listar vagas");
            System.out.println("11 - Procurar vagas");
            System.out.println("0 - Sair");
            int opcaoMenu = Integer.parseInt(entrada.nextLine());
            switch (opcaoMenu) {
                case 1:
                    cadastrarVagas();
                    break;
                case 2:
                    cadastrarCondutor();
                    break;
                case 3:
                    cadastrarVeiculo();
                    break;
              
                case 4:
                    estacionar();
                    break;
                case 5:
                    retirar();
                    break;
                case 6:
                    listarCondutores();
                    break;
                case 7:
                    listarVeiculos();
                    break;
                case 8:
                    procurarVeiculo();
                    break;
                case 9:
                    procurarMotorista();
                    break;
                case 10:
                    listarVagas();
                    break;
                case 11:
                    procurarVaga();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    /*private static void cadastrarVagas() {
        System.out.println("Digite a CNH do condutor: ");
        int cnh = entrada.nextInt();
        System.out.println("Digite a placa do veículo: ");
        String placa = entrada.next();
    }*/
 
    private static void cadastrarVagas() {
        System.out.println("Digite o número da vaga:");
        int numero = Integer.parseInt(entrada.nextLine());
        System.out.println("Digite o andar: ");
        String andar = entrada.nextLine();
        var novaVaga = new Vaga(numero, andar, null, null);
        var vagas = Vaga.lerTudo();
        vagas.add(novaVaga);
        Vaga.salvarTudo(vagas);
    }

    private static void listarVagas() {
        var vagas = Vaga.lerTudo();
        if (vagas.isEmpty()) {
            System.out.println("Não há vagas cadastradas.");
        } else {
            for (var v : vagas) {
                if (v.ocupada()) {
                    System.out.println(v.numero() + " - " + v.andar() + " - " + v.nomeMotorista() + " - " + v.cnhMotorista() + " - " + v.placaVeiculo());
                } else {
                    System.out.println(v.numero() + " - " + v.andar() + " - [VAZIA]");
                }
            }
        }
    }
 
    private static void cadastrarCondutor() {
        System.out.println("Digite o nome do condutor:");
        String nome = entrada.nextLine();
        System.out.println("Digite o cnh do condutor:");
        String cnh = entrada.nextLine().toUpperCase();
        var motorista = new Motorista(nome, cnh);
        var motoristas = Motorista.lerTudo();
        motoristas.add(motorista);
        Motorista.salvarTudo(motoristas);
    }
 
    private static void listarCondutores() {
        var motoristas = Motorista.lerTudo();
        if (motoristas.isEmpty()) {
            System.out.println("Não há condutores cadastrados.");
        } else {
            for (var m : motoristas) {
                System.out.println(m.nome() + " - " + m.cnh());
            }
        }
    }
 
    private static void cadastrarVeiculo() {
        System.out.println("Digite a placa do veiculo:");
        var placa = entrada.nextLine().toUpperCase();
        var encontrado = Veiculo.procurar(placa);
        if (encontrado != null) {
            System.out.println("Já existe esse veículo");
            return;
        }
        System.out.println("Digite o modelo do veiculo :");
        String modelo = entrada.nextLine();
        System.out.println("digite a cor do veiculo :");
        String cor = entrada.nextLine();
        var carro = new Veiculo(placa, cor, modelo);
        var veiculos = Veiculo.lerTudo();
        veiculos.add(carro);
        Veiculo.salvarTudo(veiculos);
    }
    
    private static void listarVeiculos() {
        var veiculos = Veiculo.lerTudo();
        if (veiculos.isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
        } else {
            for (var v : veiculos) {
                System.out.println(v.placa() + " - " + v.modelo() + " de cor " + v.cor());
            }
        }
    }

    private static void procurarVeiculo() {
        System.out.println("Digite a placa do veiculo:");
        String placa = entrada.nextLine().toUpperCase();
        var v = Veiculo.procurar(placa);
        if (v != null) {
            System.out.println(v.placa() + " - " + v.modelo() + " de cor " + v.cor());
        } else {
            System.out.println("Não achei.");
        }
    }

    private static void procurarMotorista() {
        System.out.println("Digite a cnh:");
        String cnh = entrada.nextLine().toUpperCase();
        var m = Motorista.procurar(cnh);
        if (m != null) {
            System.out.println(m.cnh() + " - " + m.nome());
        } else {
            System.out.println("Não achei.");
        }
    }

    private static void procurarVaga() {
        System.out.println("Digite o número:");
        int numero = Integer.parseInt(entrada.nextLine());
        var v = Vaga.procurar(numero);
        if (v == null) {
            System.out.println("Não achei.");
        } else if (v.ocupada()) {
            System.out.println(v.numero() + " - " + v.andar() + " - " + v.nomeMotorista() + " - " + v.cnhMotorista() + " - " + v.placaVeiculo());
        } else {
            System.out.println(v.numero() + " - " + v.andar() + " - [VAZIA]");
        }
    }

    private static void estacionar() {
        System.out.println("Digite o número da vaga:");
        int numero = Integer.parseInt(entrada.nextLine());
        var vaga = Vaga.procurar(numero);
        if (vaga == null) {
            System.out.println("Não existe essa vaga.");
            return;
        }
        if (vaga.ocupada()) {
            System.out.println("Essa vaga já está ocupada.");
            return;
        }

        System.out.println("Digite a placa do veiculo:");
        String placa = entrada.nextLine().toUpperCase();
        var veiculo = Veiculo.procurar(placa);
        if (veiculo == null) {
            System.out.println("Não existe esse veículo.");
            return;
        }
        var outraVaga = Vaga.procurarVeiculo(placa);
        if (outraVaga != null) {
            System.out.println("Este veículo já está estacionado.");
            return;
        }
        
        System.out.println("Digite a cnh:");
        String cnh = entrada.nextLine().toUpperCase();
        var motorista = Motorista.procurar(cnh);
        if (motorista == null) {
            System.out.println("Não existe esse motorista.");
            return;
        }

        var vagas = Vaga.lerTudo();
        var vagaPreenchida = new Vaga(vaga.numero(), vaga.andar(), veiculo, motorista);
        vagas.remove(vaga);
        vagas.add(vagaPreenchida);
        Vaga.salvarTudo(vagas);
    }

    private static void retirar() {
        System.out.println("Digite o número da vaga:");
        int numero = Integer.parseInt(entrada.nextLine());
        var vaga = Vaga.procurar(numero);
        if (vaga == null) {
            System.out.println("Não existe essa vaga.");
            return;
        }
        if (!vaga.ocupada()) {
            System.out.println("Essa vaga já está vazia.");
            return;
        }

        System.out.println(vaga.numero() + " - " + vaga.andar() + " - " + vaga.nomeMotorista() + " - " + vaga.cnhMotorista() + " - " + vaga.placaVeiculo());

        var vagas = Vaga.lerTudo();
        var vagaEsvaziada = new Vaga(vaga.numero(), vaga.andar(), null, null);
        vagas.remove(vaga);
        vagas.add(vagaEsvaziada);
        Vaga.salvarTudo(vagas);
    }
}
/*
        System.out.println("O veículo possui mais de um condutor? Se sim, digite o nome: ");
        String nomeCondutor2 = entrada.next();
    }*/
 
/*
        System.out.println("O veículo possui mais de um condutor? Se sim, digite o nome: ");
        String nomeCondutor2 = entrada.next();
    }
*/