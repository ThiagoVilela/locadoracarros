# Locadora de Carros

Esse projeto tem como intuito ser um teste avaliativo para a vaga de desenvolvedor de java, no primeiro semestre do ano de 2019.
Ele consiste em ler um arquivo com um intervalo de datas pré-definido, o número de passageiros, e retornar ao usuário (por meio de outro arquivo),
a locadora que possui o menor custo e atende as suas necessidades.

### Como funciona

Você precisa ter uma versão atual do Java no ambiente de execução.
Preencher o arquivo no modelo corretamente, seguindo o tema:

```
01Jun2019 (qua),02Jul2019 (sex):2
```

É importante que as datas estejam em ordem crescente (sendo intuitivo para leitura de um usuário), e que sejam maiores ou iguais ao dia em que o programa for executado (impossível alguém alugar um carro no passado). É permitido o usuário também informar o dia da semana em questão, mas o programa parte do pressuposto que isso não é obrigatório, valida e recalcula a informação.

```
01Jun2019,02Jul2019:2
```
Também é aceito. A saída representa o tipo de carro que a locadora fornece e o nome da locadora.

```
Carro Compacto:SouthCar
```
## Testes

Uma série de testes foi configurada para este programam, utilizando o jUnit, testando suas ações e métodos.

### COM.TESTS

A bateria de testes se encontram no pacote COM.TESTS, e podem ser executados a partir de lá. 
Também existem arquivos que influenciam no teste, todos no formato .txt e facilmente encontrados. 

```
A primeiro momento os arquivos são: entradateste.txt e saidateste.txt
```

## Autor

* **Thiago Vilela** - *Tem dúvidas? Fale comigo em oisouothiagovilela@gmail.com*
