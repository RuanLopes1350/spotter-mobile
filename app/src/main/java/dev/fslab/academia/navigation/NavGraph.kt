package dev.fslab.academia.navigation

sealed class Screen(val route: String) {
    // Auth
    data object Login : Screen("login")
    data object Cadastro : Screen("cadastro")

    // Comum
    data object Home : Screen("home")
    data object Perfil : Screen("perfil")
    data object Configuracoes : Screen("configuracoes")
    data object Chat : Screen("chat")
    data object ChatDetalhe : Screen("chat_detalhe")
    data object Notificacoes : Screen("notificacoes")

    // Aluno
    data object Treinos : Screen("treinos")
    data object TreinoDetalhe : Screen("treino_detalhe")
    data object SessaoAtiva : Screen("sessao_ativa")
    data object Historico : Screen("historico")
    data object HistoricoProgressao : Screen("historico_progressao")
    data object ExercicioCatalogo : Screen("exercicio_catalogo")

    // Treinador
    data object TreinadorAlunos : Screen("treinador_alunos")
    data object TreinadorAlunoDetalhe : Screen("treinador_aluno_detalhe")
    data object TreinadorCriarTreino : Screen("treinador_criar_treino")
}
