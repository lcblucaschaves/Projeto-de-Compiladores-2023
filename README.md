# Projeto-de-Compiladores-2023
## Integrantes
Bruno Gabriel Nascimento Pina - RA:11201921643 - Turma A1 <br />
João Pedro Genga - RA:11201810740 - Turma A1 <br />
Lucas Chaves Batista - RA:11201921079 - Turma A2 <br />
Matheus dos Santos Cavaião - RA:11201921622  - Turma A2 <br />

## Projeto de Compiladores
VERSÕES: O projeto foi realizado utilizando as versões JAVA SE 17 e ANTLR 4.7.1.
LINK DO VIDEO: 

O projeto trata-se da criação de um compilador (ou Transpilador nesse caso) que reconhece uma linguagem criada (IsiLanguage) e a traduz para a linguagem JAVA. Essa tradução é feita utilizando o analisador (parser) gerado pelo ANTLR com base na gramatica criada. A geração de codigo (Tradução) é feita através dos códigos presentes em AST e Datastructs.

Execução: O programa MainClass.java (pacote main) recebe o arquivo input.isi como entrada e compila em um arquivo isiLangTranspilada.java. Sinta-se livre para editar o arquivo input.isi.
OBS: Certifique-se de que o pacote parser está atualizado. É possivel gerar ele novamente usando o antlr.

Os seguintes itens do checklist foram entregados:
+ Possui 2 tipos de variáveis
- [x] Possui a estrutura If.. else
- [x] Possui estrutura de controle while/do while
- [x] Operações Aritméticas executadas corretamente
- [x] Atribuições realizadas corretamente
- [x] Possui operações de Entrada e Saída
- [x] Aceita números decimais
- [x] Verificar se a variável já foi previamente declarada
- [x] Verificar se a variável foi declarada e não foi usada
- [x] Verificar se uma variável está sendo usada sem ter valor inicial 

## Gramatica Utilizada
prog        -> **'programa'** declara bloco **'fimprog'**  <br />
declara     -> (declaravar)+ <br /> 
declaravar  -> tipo ID (**,** ID)* **;** <br />
tipo        -> **'numero'** | **'texto'** | **'booleano'** <br />
bloco       -> (cmd)+ <br />
cmd         -> cmdleitura | cmdescrita | cmdattr | cmdselecao | cmdenquanto | cmdfazenquanto <br />
cmdleitura  -> **'leia'(** ID **);** <br />
cmdescrita  -> **'escreva'(** ID | NUMBER | TEXT | BOOLEAN **);** <br />
cmdattr     -> ID **'='** expr **;** <br />
cmdselecao  -> '**se'(** ID OPREL (ID | NUMBER) **) {** (cmd)+ **}** <br />
               ( **'senao'{** (cmd)+ **}** )? <br />
cmdenquanto -> **'enquanto'(** ID OPREL (ID | NUMBER) **) {** (cmd)+ **}** <br />
cmdfazenquanto -> **'faca'{** (cmd)+ **}** <br />
                  **'enquanto'(** expr OPREL expr **);** <br />
expr    -> TEXT | termo (OP termo)* | BOOLEAN <br />
termo   -> ID | NUMBER <br />
OP      -> **'+'** | **'-'** | **'*'** | **'/'** <br />
OPREL   -> **'>'** | **'<'** | **'>='** | **'<='** | **'=='** | **'!='** <br />
ID      -> [a-z] ([a-z] | [A-Z] | [0-9])* <br />
NUMBER  -> [0-9]+ ('.' [0-9]+)? <br />
TEXT    -> ([a-z] | [A-Z] | [0-9] | ' ')* <br />
BOOLEAN -> **'Verdadeiro'** | **'Falso'** <br />
