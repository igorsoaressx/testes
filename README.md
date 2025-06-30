# Projeto Final LPOO - Sistema de Barbearia (BarberShop)

 trabalho final para a disciplina de Linguagem de Programação Orientada a Objetos (LPOO).
O sistema criado foi um **gerenciamento de uma barbearia**, com foco no controle de clientes, barbeiros e agendamento de serviços.

## Descrição do Sistema

### Funcionalidades Principais:

* **Gerenciamento de Clientes:** Interface para listar, cadastrar, editar e remover clientes.
* **Gerenciamento de Barbeiros:** Interface para listar, cadastrar, editar e remover barbeiros, incluindo suas especialidades.
* **Inicialização de Serviços:** Os serviços oferecidos pela barbearia (ex: Corte, Barba) são pré-definidos e carregados automaticamente no banco de dados na inicialização do sistema.
* **Gerenciamento de Agendamentos:**
    * Visualização de todos os agendamentos em uma tabela.
    * Criação de novos agendamentos, permitindo selecionar um cliente, um barbeiro e um ou mais serviços de listas pré-carregadas.
    * Funcionalidade para cancelar agendamentos existentes.

## Diagrama de Classes UML
[Visualizar Diagrama de Classes em PDF](Diagrama%20de%20Classes%20UML%20(1).pdf)

## Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21+)
* **Interface Gráfica:** Swing
* **Persistência de Dados:** JPA (Java Persistence API) com a implementação do Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Dependências:** Maven
