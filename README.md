# TestAPI - Testes de API com Postman e k6

## Descrição

O **TestAPI** é um projeto dedicado à validação do comportamento e desempenho de uma API. Ele utiliza o **Postman** para realizar testes funcionais e o **k6** para avaliar o desempenho e a carga da API. O projeto serve para estudo de testes em API, utilizando o **VS Code** como ambiente de desenvolvimento.

Os testes são realizados sobre a API do [nestjs-cinema](https://github.com/juniorschmitz/nestjs-cinema/tree/main), uma API que simula um sistema de cinema, oferecendo operações de CRUD (criar, ler, atualizar e excluir) sobre filmes, sessões, ingressos e outros componentes relacionados.

## Tecnologias utilizadas

- **[Postman](https://www.postman.com/)**
- **[k6](https://k6.io/)**
- **[VS Code](https://code.visualstudio.com/)**

### Configuração do Ambiente

## Como executar os testes

### 1. Testes com Postman

- **Passo 1**: Abra o **Postman**.
- **Passo 2**: Importe a coleção de testes da pasta **postman**.
- **Passo 3**: Selecione o ambiente de teste desejado (ex: **env-teste**).
- **Passo 4**: Execute os testes clicando em "Run".
- **Passo 5**: Verifique os resultados na interface do Postman.

### 2. Testes com k6

- **Passo 1**: Abra o terminal do **VS Code**.
- **Passo 2**: Importe a coleção de testes da pasta **k6**.
- **Passo 3**: Navegue até o script de teste (ex: **k6/testeDeCarga.js**).
- **Passo 4**: Execute o comando:

```bash
k6 run testeDeDesempenho.js
```

O **k6** fornecerá métricas detalhadas sobre o desempenho da API.


## Contribuindo

Contribuições são bem-vindas! Se você deseja contribuir com este projeto, siga os passos abaixo:

1. Faça um **Fork** deste repositório e clone seu fork:
   
```bash
git clone https://gitlab.com/seu-usuario/testapi.git
```

3. Crie uma nova branch para suas alterações:
   
```bash
git checkout -b minha-nova-feature
```

4. Adicione suas alterações e faça o commit:
   
```bash
git add .
git commit -m "Descrição da minha contribuição"
```

5. Envie suas alterações para o seu fork:
   
```bash
git push origin minha-nova-feature
```

6. Abra um **Pull Request** explicando as mudanças e melhorias feitas.

## Licença

Este projeto está licenciado sob a **MIT License**. Consulte o arquivo LICENSE para mais detalhes.

---

