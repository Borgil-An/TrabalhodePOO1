public class LembretesApp {

     public static void main(String[] args) {
        BlocoDeLembretes bloco = new BlocoDeLembretes();
        
        bloco.inserir(new Lembrete("Prova de POO", new Data(15, 8, 2023)));
        bloco.inserir(new Lembrete("Trabalho de POO", new Data(30, 8, 2023)));
        bloco.inserir(new Lembrete("Aula de Coleções", new Data(20, 8, 2023)));
        bloco.inserir(new Lembrete("Aula importante", new Data(10, 9, 2023)));
        bloco.inserir(new Lembrete("Aniversário", new Data(25, 9, 2023)));
    
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            
            do {
                opcao = menu();
                switch (opcao) {
                    case 1:
                        inserirLembrete(bloco, scanner);
                        break;
                    case 2:
                        excluirLembrete(bloco, scanner);
                        break;
                    case 3:
                        buscarLembretePorSubstring(bloco, scanner);
                        break;
                    case 4:
                        buscarLembretesPorMes(bloco, scanner);
                        break;
                    case 5:
                        buscarLembretesPorDia(bloco, scanner);
                        break;
                    case 6:
                        imprimirTodosLembretes(bloco);
                        break;
                    case 7:
                        imprimirLembretesPorMes(bloco, scanner);
                        break;
                    case 8:
                        imprimirLembretesPorDia(bloco, scanner);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 9);
        }
    }

    private static int menu() {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("### BEM-VINDO AO SEU APP DE LEMBRETES ###");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar um novo lembrete");
            System.out.println("2 - Excluir um lembrete");
            System.out.println("3 - Buscar um lembrete a partir de uma palavra");
            System.out.println("4 - Buscar lembretes a partir do mes");
            System.out.println("5 - Buscar lembretes a partir do dia");
            System.out.println("6 - Imprimir todos os lembretes");
            System.out.println("7 - Imprimir todos os lembretes de um mess");
            System.out.println("8 - Imprimir todos os lembretes de um dia");
            System.out.println("9 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();
        } while (opcao < 1 || opcao > 9);
        return opcao;
    }

    private static void inserirLembrete(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o lembrete: ");
        String lembreteDescricao = scanner.nextLine();
    
        System.out.print("Digite o dia do lembrete: ");
        int dia = scanner.nextInt();
    
        System.out.print("Digite o mês do lembrete (Digite 0 na frente caso seu mês seja menor que 10): ");
        int mes = scanner.nextInt();
    
        System.out.print("Digite o ano do lembrete: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        Data data = new Data(dia, mes, ano);
        bloco.inserir(new Lembrete(lembreteDescricao, data));
        System.out.println("Lembrete adicionado!");
    }
   
    private static void excluirLembrete(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o índice: ");
        int indiceExcluir = scanner.nextInt();
        bloco.remover(indiceExcluir - 1);
        System.out.println("Lembrete removido!");
    }

    private static void buscarLembretePorSubstring(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite a palavra: ");
        String substring = scanner.nextLine();
        List<Lembrete> resultados = bloco.buscarPorPalavra(substring);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum lembrete encontrado.");
        } else {
            System.out.println("Lembretes encontrados:");
            for (Lembrete lembrete : resultados) {
                System.out.println(lembrete);
            }
        }
    }
    
    private static void buscarLembretesPorMes(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o mês a ser buscado (Digite 0 na frente caso seu mês seja menor que 10): ");
        int mes = scanner.nextInt();

        List<Lembrete> resultados = bloco.buscarPorMes(mes);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum lembrete encontrado para o mês fornecido.");
        } 
        else {
            System.out.println("Lembretes encontrados para o mês " + Data.nomeDoMes(mes) + ":");
            for (int i = 0; i < resultados.size(); i++) {
                Lembrete lembrete = resultados.get(i);
                System.out.println((i + 1) + " - " + lembrete);
            }
        }
    }
    
    private static void buscarLembretesPorDia(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o dia:");
        int dia = scanner.nextInt();
        List<Lembrete> resultados = bloco.buscarPorDia(dia);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum lembrete encontrado");
        } else {
            System.out.println("Lembretes encontrados para o dia " + dia + ":");
            for (Lembrete lembrete : resultados) {
                System.out.println(lembrete);
            }
          }
    }

    private static void imprimirTodosLembretes(BlocoDeLembretes bloco) {
        System.out.println("Todos os lembretes:");
        List<Lembrete> lembretes = bloco.getLembretes();
        
        for (int i = 0; i < lembretes.size(); i++) {
            Lembrete lembrete = lembretes.get(i);
            System.out.println((i + 1) + " - " + lembrete);
        }   
    }

    private static void imprimirLembretesPorMes(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o mes(Digite 0 na frente caso seu mês seja menor que 10): ");
        int mes = scanner.nextInt();
       
        List<Lembrete> resultados = bloco.buscarPorMes(mes);
        Collections.sort(resultados);
        System.out.println("Lembretes do mes " + Data.nomeDoMes(mes) + ":");
            
        for (int i = 0; i < resultados.size(); i++) {
                Lembrete lembrete = resultados.get(i);
                System.out.println((i + 1) + " - " + lembrete);
        }
    }

    private static void imprimirLembretesPorDia(BlocoDeLembretes bloco, Scanner scanner) {
        System.out.print("Digite o dia: ");
        int dia = scanner.nextInt();
        
        List<Lembrete> resultados = bloco.buscarPorDia(dia);
        Collections.sort(resultados);
        System.out.println("Lembretes do dia " + dia + ":");
        
        for (int i = 0; i < resultados.size(); i++) {
            Lembrete lembrete = resultados.get(i);
            System.out.println((i + 1) + " - " + lembrete);
        }
    }   
}
