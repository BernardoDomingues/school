# Sistema de Matrículas da Universidade

## Descrição
Sistema Java desktop para gerenciamento de matrículas universitárias com persistência em arquivos. O sistema permite que alunos se matriculem em disciplinas, professores visualizem turmas, e a secretaria gerencie cursos e períodos de matrícula.

## Funcionalidades Principais

### Para Alunos:
- Matrícula em até 4 disciplinas obrigatórias (1ª opção)
- Matrícula em até 2 disciplinas optativas (2ª opção)
- Cancelamento de matrículas durante o período de matrícula
- Visualização de disciplinas matriculadas

### Para Professores:
- Visualização de alunos matriculados em suas disciplinas
- Acesso às informações das disciplinas ministradas

### Para Secretaria:
- Gerenciamento de cursos e disciplinas
- Controle de períodos de matrícula
- Verificação de disciplinas ativas (mínimo 3 alunos)
- Integração com sistema de cobrança

## Regras de Negócio

1. **Limite de Matrículas**: Alunos podem se matricular em 4 disciplinas obrigatórias e 2 optativas
2. **Disciplinas Ativas**: Só ficam ativas se tiverem pelo menos 3 alunos matriculados
3. **Limite de Vagas**: Máximo de 60 alunos por disciplina
4. **Períodos de Matrícula**: Sistema controla períodos específicos para matrículas
5. **Sistema de Cobrança**: Notificação automática para geração de cobranças

## Estrutura do Projeto

### Classes Principais:
- `SistemaMatriculas`: Classe principal que gerencia todo o sistema
- `Usuario`: Classe abstrata base para todos os usuários
- `Aluno`: Representa alunos do sistema
- `Professor`: Representa professores
- `Curso`: Representa cursos da universidade
- `Disciplina`: Representa disciplinas dos cursos
- `Matricula`: Representa matrícula de aluno em disciplina
- `PeriodoMatricula`: Gerencia períodos de matrícula
- `SistemaCobranca`: Sistema de cobrança integrado
- `Cobranca`: Representa uma cobrança individual
- `GerenciadorArquivos`: Persistência em arquivos

### Persistência:
- **Sistema de arquivos totalmente funcional** implementado na classe `GerenciadorArquivos`
- Dados salvos em arquivos de texto na pasta `dados/`
- Arquivos separados por entidade (alunos.txt, professores.txt, etc.)
- Formato de dados: campos separados por pipe (|)
- **Carregamento e salvamento automático** de todos os dados
- **Relacionamentos preservados** entre entidades

## Como Executar

1. Compile todos os arquivos Java:
```bash
javac *.java
```

2. Execute a classe Main para demonstração básica:
```bash
java Main
```

3. Execute a classe TesteSistema para teste completo com persistência:
```bash
java TesteSistema
```

## Arquivos de Dados

O sistema cria automaticamente a pasta `dados/` com os seguintes arquivos:
- `alunos.txt`: Lista de alunos
- `professores.txt`: Lista de professores
- `cursos.txt`: Lista de cursos
- `disciplinas.txt`: Lista de disciplinas
- `matriculas.txt`: Lista de matrículas
- `periodos.txt`: Lista de períodos de matrícula
- `cobrancas.txt`: Lista de cobranças
