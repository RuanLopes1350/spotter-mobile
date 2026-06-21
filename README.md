# Spotter — Aplicativo Android de Controle de Atividades Físicas

Aplicativo Android da plataforma **Spotter**, desenvolvido em Kotlin com Jetpack Compose. Conecta personal trainers e alunos em um único ecossistema de gestão de treinos.

---

## O que é

O **Spotter** é uma solução integrada para gerenciamento de atividades físicas. O app permite prescrição de treinos personalizados, execução com registro de séries e cargas, chat em tempo real, histórico de progresso com gráficos, notificações push e vinculação entre aluno e treinador.

> **Recomendação:** O app aponta para o ambiente **QA**, que está sempre atualizado com a versão em desenvolvimento ativa. Use o QA para testar funcionalidades.
>
> **API QA:** `https://atividadesfisicas-api-qa.yuriprojects.dpdns.org`

---

## Funcionalidades

### Aluno
- Login, cadastro e recuperação de senha
- Autenticação biométrica (impressão digital)
- Visualizar e executar treinos prescritos pelo treinador
- Criar treinos próprios com exercícios personalizados
- Registrar séries, repetições e cargas durante a sessão de treino
- Acompanhar histórico de sessões e gráficos de evolução de carga
- Registrar e visualizar histórico de peso corporal com gráfico
- Chat em tempo real com o treinador
- Buscar treinadores e solicitar vinculação
- Visualizar GIFs demonstrativos dos exercícios
- Badge de mensagens não lidas no ícone de Chat
- Gerenciar perfil e informações pessoais

### Treinador
- Painel com visão geral de alunos e sessões do dia
- Criar, editar e atribuir treinos aos alunos
- Acompanhar evolução e histórico de cada aluno
- Chat em tempo real com alunos
- Gerenciar solicitações de vinculação de alunos
- Tela de notificações in-app (histórico de pushs recebidos em sessão)
- Badge de mensagens não lidas no ícone de Chat

### Geral
- Notificações push via Firebase Cloud Messaging (FCM)
- Deep link ao tocar em notificação → navega direto para a tela correta
- Design system próprio com tema verde/cinza e suporte a modo escuro/claro

---

## Tecnologias

| Tecnologia | Versão | Uso |
|-----------|--------|-----|
| Kotlin | 1.9+ | Linguagem principal |
| Jetpack Compose | BOM 2024.09.00 | UI declarativa |
| Material3 | latest | Design system e componentes |
| Retrofit2 | 2.9.0 | Cliente HTTP para a API |
| OkHttp3 | 4.12.0 | Interceptores e logging HTTP |
| Socket.IO Client | 2.1.1 | Chat em tempo real |
| Coil | 2.5.0 | Carregamento de imagens, GIFs e SVGs |
| ExoPlayer / Media3 | 1.3.1 | Reprodução de vídeos de exercícios |
| Firebase Cloud Messaging | BOM 33.7.0 | Notificações push |
| Navigation Compose | 2.7.7 | Navegação entre telas |
| ViewModel + StateFlow | 2.7.0 | Arquitetura MVVM |
| DataStore Preferences | 1.0.0 | Persistência local de preferências |
| CameraX | 1.3.1 | Câmera para leitura de QR Code |
| ML Kit Barcode | 17.2.0 | Decodificação de QR Code |
| Biometric API | 1.2.0-alpha05 | Autenticação biométrica |
| PersistentCookieJar | 1.0.1 | Gerenciamento de sessão HTTP |
| Reorderable | 2.4.0 | Drag-and-drop de exercícios em listas |
| Security Crypto | 1.1.0-alpha06 | Armazenamento seguro local |

---

## Referências externas

### workout-planner (adanzan)

O repositório [adanzan/workout-planner](https://github.com/adanzan/workout-planner) foi utilizado como **referência de design, organização de componentes e padrões de UX** para telas de treino em Jetpack Compose.

- **Repositório:** https://github.com/adanzan/workout-planner
- **Licença:** [MIT License](https://github.com/adanzan/workout-planner/blob/main/LICENSE)
- **Uso:** referência de interface e arquitetura de componentes — nenhum código foi copiado diretamente

---

## Arquitetura

O app segue o padrão **MVVM** (Model-View-ViewModel) com `StateFlow` e `sealed classes` para gerenciamento de estado de UI. A navegação é gerenciada pelo Navigation Compose com rotas tipadas.

```
app/src/main/java/dev/fslab/academia/
├── MainActivity.kt               # Ponto de entrada; ViewModels de escopo Activity
├── model/                        # Data classes (DTOs de API)
├── network/                      # Interfaces Retrofit + RetrofitClient
├── service/                      # FCM, DeepLinkManager, NotificationInboxManager
├── navigation/                   # NavGraph + Screen sealed class
└── ui/
    ├── components/               # Componentes reutilizáveis (AppBar, NavBar, Cards)
    ├── screens/
    │   ├── auth/                 # Login, Cadastro, Recuperação de senha
    │   ├── aluno/                # Treinos, Histórico, Chat, Sessão, Exercícios
    │   ├── treinador/            # Painel, Alunos, Treinos do treinador
    │   ├── chat/                 # Chat e detalhe de conversa
    │   ├── ConfiguracoesScreen.kt
    │   ├── NotificacoesScreen.kt
    │   └── ProfileScreen.kt
    ├── theme/                    # Cores, tipografia, Dimens (espaçamentos responsivos)
    ├── util/                     # Extensões e Motion (animações)
    └── viewmodel/                # ViewModels por domínio de tela
```

---

## Como rodar

### Pré-requisitos

- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 17+
- Dispositivo ou emulador Android API 26+ (Android 8.0)

### Via Android Studio

```bash
# 1. Clonar o repositório
git clone <url-do-repositório>

# 2. Abrir no Android Studio
# File → Open → selecionar a pasta do projeto

# 3. Aguardar sincronização do Gradle (automática)

# 4. Executar no dispositivo/emulador
# Run → Run 'app'  (Shift+F10)
```

O app já está configurado para apontar para a API de **QA**. Nenhuma configuração adicional é necessária.

### Via linha de comando

```bash
# Build debug
./gradlew assembleDebug

# Instalar em dispositivo conectado via USB
./gradlew installDebug

# Build release
./gradlew assembleRelease
```

---

## Usuários seed para teste (ambiente QA)

| Tipo | E-mail | Senha |
|------|--------|-------|
| Treinador | `marcos.rocha@personalfit.com` | `Treinador@2026!` |
| Treinador | `fernanda.almeida@personalfit.com` | `Treinador@2026!` |
| Aluno (com treinador vinculado) | `carlos.silva@gmail.com` | `Aluno@2026!` |
| Aluno (sem treinador) | `juliana.lima@outlook.com` | `Aluno@2026!` |

---

## Ambientes de API

| Ambiente | URL | Branch da API |
|----------|-----|---------------|
| **QA** (em uso pelo app) | `https://atividadesfisicas-api-qa.yuriprojects.dpdns.org` | `develop` |
| **Produção** | `https://atividadesfisicas-api.yuriprojects.dpdns.org` | `main` |

URL configurada em `RetrofitClient.kt`:
```kotlin
const val BASE_URL = "https://atividadesfisicas-api-qa.yuriprojects.dpdns.org/api/"
```

Repositório da API: `fabrica-4-api-controle-de-atividades-fisicas`

---

## Equipe

Projeto acadêmico desenvolvido no **IFRO** — Instituto Federal de Rondônia, disciplina Fábrica de Software IV (2026).

| Nome | Papel | Contato |
|------|-------|---------|
| Yuri Zetoles | Desenvolvedor | yurizetoles0123@gmail.com |
| Lucca Livino | Desenvolvedor | lucca.f.livino@gmail.com |
| Ruan Lopes | Desenvolvedor | intel.spec.lopes@gmail.com |
| José Lucas Brandão Montes | Professor / Cliente | lucas.montes@ifro.edu.br |

---

## Licença

Este projeto é de uso acadêmico e não possui licença de distribuição pública. Todos os direitos reservados aos autores.

A biblioteca referenciada [adanzan/workout-planner](https://github.com/adanzan/workout-planner) é distribuída sob [licença MIT](https://github.com/adanzan/workout-planner/blob/main/LICENSE).
