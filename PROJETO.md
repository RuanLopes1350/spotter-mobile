# PROJETO DE SOFTWARE — SPOTTER (Mobile)

## Stakeholders

| Nome | Cargo / Papel | Contato |
|------|--------------|---------|
| José Lucas Brandão Montes | Professor / Cliente | lucas.montes@ifro.edu.br |

## Equipe de Desenvolvimento

| Nome | Cargo / Papel | Contato |
|------|--------------|---------|
| Yuri Zetoles | Desenvolvedor | yurizetoles0123@gmail.com |
| Lucca Livino | Desenvolvedor | lucca.f.livino@gmail.com |
| Ruan Lopes | Desenvolvedor | intel.spec.lopes@gmail.com |

---

## Resumo do Projeto

| | |
|--|--|
| **Nome** | Spotter |
| **Componente** | Aplicativo Android |
| **Objetivo principal** | Plataforma mobile de gestão de treinos e comunicação entre personal trainers e alunos |
| **Benefícios esperados** | Facilitar o acompanhamento de treinos, melhorar a comunicação instrutor-aluno e centralizar progresso em um único app |
| **Período** | 10/02/2026 – 23/06/2026 |
| **Instituição** | IFRO — Instituto Federal de Rondônia |
| **Disciplina** | Fábrica de Software IV |

---

## Introdução

O **Spotter Mobile** é o aplicativo Android da plataforma Spotter, desenvolvido em Kotlin com Jetpack Compose. Consome a API REST e comunica-se via WebSocket (Socket.IO) para chat em tempo real.

O app foi concebido para ser utilizado **antes, durante e após o treino** — tanto pelo treinador (prescrição e acompanhamento) quanto pelo aluno (execução e monitoramento de progresso). A identidade visual adota paleta **verde e cinza** com suporte a tema escuro e claro, e o design system foi construído com Material3.

---

## Repositórios do Projeto

| Componente | Repositório |
|-----------|-------------|
| Aplicativo Android | `fabrica-4-mobile-controle-de-atividades-fisicas` (este repositório) |
| API (backend) | `fabrica-4-api-controle-de-atividades-fisicas` |

---

## Arquitetura

O aplicativo segue o padrão **MVVM** (Model-View-ViewModel) com as seguintes camadas:

```
┌──────────────────────────────────────────────────────┐
│              Jetpack Compose (UI)                    │
│   Screens + Components — estado via StateFlow        │
├──────────────────────────────────────────────────────┤
│              ViewModels (lógica de UI)               │
│   StateFlow + sealed classes + coroutines            │
├──────────────────────────────────────────────────────┤
│              Network Layer                           │
│   Retrofit2 + OkHttp3 + Socket.IO                   │
├──────────────────────────────────────────────────────┤
│         API REST + WebSocket (Backend)               │
│   https://atividadesfisicas-api-qa.yuriprojects.dpdns.org │
└──────────────────────────────────────────────────────┘
```

### Decisões arquiteturais relevantes

- **MVVM com StateFlow:** estado de UI imutável, fácil de testar e de rastrear. `sealed classes` para estados Loading/Success/Error.
- **Activity-scoped ViewModels:** `ChatViewModel` e `ConversasViewModel` vivem no escopo da `MainActivity` para compartilhar estado de badge entre telas.
- **DeepLinkManager:** singleton `StateFlow` que armazena a rota pendente de navegação ao tocar em uma notificação push, garantindo que a navegação ocorra apenas após o login do usuário.
- **NotificationInboxManager:** singleton in-memory que captura pushes FCM recebidos enquanto o app está em primeiro plano, populando a tela de notificações sem depender de endpoint de API.
- **Navigation Compose:** todas as rotas são tipadas via `Screen sealed class`. Extensão `navigateSafely()` previne crashes de navegação duplicada.
- **LocalDimens:** sistema de espaçamentos responsivos via `CompositionLocal`, garantindo consistência visual em diferentes densidades de tela.

---

## Stack Tecnológico

| Tecnologia | Versão | Propósito |
|-----------|--------|-----------|
| Kotlin | 1.9+ | Linguagem |
| Jetpack Compose | BOM 2024.09.00 | UI declarativa |
| Material3 | latest | Design system |
| Retrofit2 | 2.9.0 | Cliente HTTP REST |
| OkHttp3 | 4.12.0 | HTTP e interceptores |
| Socket.IO Client | 2.1.1 | WebSocket para chat |
| Coil | 2.5.0 | Imagens, GIFs e SVGs |
| ExoPlayer / Media3 | 1.3.1 | Reprodução de vídeo |
| Firebase Cloud Messaging | BOM 33.7.0 | Notificações push |
| Navigation Compose | 2.7.7 | Roteamento entre telas |
| ViewModel + StateFlow | 2.7.0 | Estado de UI |
| DataStore Preferences | 1.0.0 | Preferências locais |
| CameraX | 1.3.1 | Câmera (QR Code) |
| ML Kit Barcode | 17.2.0 | Leitura de QR Code |
| Biometric API | 1.2.0-alpha05 | Autenticação biométrica |
| PersistentCookieJar | 1.0.1 | Sessões HTTP persistentes |
| Reorderable | 2.4.0 | Drag-and-drop de exercícios |
| Security Crypto | 1.1.0-alpha06 | Armazenamento seguro |

---

## Referências Externas

### workout-planner (adanzan)

- **Repositório:** [https://github.com/adanzan/workout-planner](https://github.com/adanzan/workout-planner)
- **Licença:** MIT License — uso livre para fins educacionais e comerciais, desde que mantida a atribuição
- **Uso no projeto:** referência de padrões de UI, organização de componentes Compose e experiência de usuário em telas de treino. Nenhum código foi copiado diretamente.

---

## Ambientes e Deploy

| Ambiente | URL da API | Branch |
|----------|-----------|--------|
| **QA** (em uso pelo app) | `https://atividadesfisicas-api-qa.yuriprojects.dpdns.org` | `develop` (API) |
| **Produção** | `https://atividadesfisicas-api.yuriprojects.dpdns.org` | `main` (API) |

O aplicativo Android em uso aponta para o **QA**, que reflete a versão em desenvolvimento da API. A build do app para teste é gerada via `./gradlew installDebug` e instalada diretamente via USB.

Não há pipeline de distribuição automática do APK — a distribuição é feita manualmente durante o desenvolvimento acadêmico.

---

## Usuários Seed (ambiente QA)

| Tipo | E-mail | Senha |
|------|--------|-------|
| Treinador | `marcos.rocha@personalfit.com` | `Treinador@2026!` |
| Treinador | `fernanda.almeida@personalfit.com` | `Treinador@2026!` |
| Aluno (com treinador) | `carlos.silva@gmail.com` | `Aluno@2026!` |
| Aluno (sem treinador) | `juliana.lima@outlook.com` | `Aluno@2026!` |

---

## Requisitos Funcionais

| ID | Nome | Descrição | Prioridade |
|----|------|-----------|-----------|
| RF001 | Autenticação | Login, cadastro, recuperação de senha e login biométrico | Alta |
| RF002 | Perfil do usuário | Visualização e edição de dados pessoais e foto de perfil | Alta |
| RF003 | Treinos (aluno) | Visualizar, executar e criar treinos; registrar séries e cargas | Alta |
| RF004 | Treinos (treinador) | Criar, editar, atribuir e remover treinos dos alunos | Alta |
| RF005 | Sessão de treino | Registro em tempo real de séries, repetições e cargas por exercício | Alta |
| RF006 | Histórico de sessões | Consulta de sessões realizadas com estatísticas e gráficos | Alta |
| RF007 | Histórico de peso | Registro e gráfico de evolução do peso corporal do aluno | Média |
| RF008 | Chat | Mensagens em tempo real entre aluno e treinador via Socket.IO | Alta |
| RF009 | Notificações push | Receber pushs FCM com deep link para a tela correta | Média |
| RF010 | Inbox de notificações | Tela de histórico de notificações recebidas em sessão | Média |
| RF011 | Badge de não-lidas | Contador de mensagens não lidas no ícone de Chat | Média |
| RF012 | Busca de treinador | Aluno pesquisa e solicita vinculação com treinador | Alta |
| RF013 | Perfil do treinador | Aluno visualiza especializações e dados do treinador | Média |
| RF014 | Gestão de alunos | Treinador visualiza lista e dados de cada aluno vinculado | Alta |
| RF015 | Demonstração de exercícios | GIFs animados e vídeos demonstrativos via ExerciseDB | Alta |
| RF016 | Configurações | Tema, dados da conta, logout e navegação para perfil | Baixa |

## Requisitos Não Funcionais

| ID | Nome | Descrição |
|----|------|-----------|
| RNF001 | Responsividade | UI adaptada a diferentes densidades e tamanhos de tela |
| RNF002 | Performance | Composables otimizados com `remember`, `derivedStateOf` e lazy lists |
| RNF003 | Acessibilidade | `contentDescription` em todos os ícones e elementos interativos |
| RNF004 | Segurança | Sessões via cookies persistentes; armazenamento seguro com Security Crypto |
| RNF005 | Offline | Mensagem de erro amigável quando sem conexão |

---

## Metodologia

Desenvolvimento ágil com **Kanban** via GitLab Issues e Merge Requests. Cada funcionalidade é desenvolvida em branch própria (nomeada com o número da issue), revisada via MR e mesclada à `main` após aprovação.

---

## Licença

Este projeto é de uso acadêmico. Todos os direitos reservados aos autores.

A biblioteca referenciada [adanzan/workout-planner](https://github.com/adanzan/workout-planner) é distribuída sob [MIT License](https://github.com/adanzan/workout-planner/blob/main/LICENSE).
