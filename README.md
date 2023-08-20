# Projeto-de-Compiladores-2023
## Integrantes
+ Bruno Gabriel Nascimento Pina - RA:11201921643 - Turma A1 <br />
+ João Pedro Genga - RA:11201810740 - Turma A1 <br />
+ Lucas Chaves Batista - RA:11201921079 - Turma A2 <br />
+ Matheus dos Santos Cavaião - RA:11201921622  - Turma A2 <br />
+ Tiago Luiz Silva de Araujo Pereira RA:11013316 - Turma A1

## Projeto de Compiladores
VERSÕES: O projeto foi realizado utilizando as versões JAVA SE 17 e ANTLR 4.7.1.

LINK DO VIDEO: https://youtu.be/VYlzjVS4eZ4

O projeto trata-se da criação de um compilador (ou Transpilador nesse caso) que reconhece uma linguagem criada (IsiLanguage) e a traduz para a linguagem JAVA. Essa tradução é feita utilizando o analisador (parser) gerado pelo ANTLR com base na gramatica criada. A geração de codigo (Tradução) é feita através dos códigos presentes em AST e Datastructs.

Execução: O programa MainClass.java (pacote main) recebe o arquivo input.isi como entrada e compila em um arquivo isiLangTranspilada.java. Sinta-se livre para editar o arquivo input.isi.
OBS: Certifique-se de que o pacote parser está atualizado. É possivel gerar ele novamente usando o antlr.

Os seguintes itens do checklist foram entregados:
+ Possui 2 tipos de variáveis
+ Possui a estrutura If.. else
+ Possui estrutura de controle while/do while
+ Operações Aritméticas executadas corretamente
+ Atribuições realizadas corretamente
+ Possui operações de Entrada e Saída
+ Aceita números decimais
+ Verificar se a variável já foi previamente declarada
+ Verificar se a variável foi declarada e não foi usada
+ Verificar se uma variável está sendo usada sem ter valor inicial 

## Gramatica Utilizada
```
prog        -> 'programa' declara bloco 'fimprog'
declara     -> (declaravar)+
declaravar  -> tipo ID (, ID)* ;
tipo        -> 'numero' | 'texto' | 'booleano'
bloco       -> (cmd)+
cmd         -> cmdleitura | cmdescrita | cmdattr | cmdselecao | cmdenquanto | cmdfazenquanto
cmdleitura  -> 'leia'(ID);
cmdescrita  -> 'escreva'(ID | NUMBER | TEXT | BOOLEAN);
cmdattr     -> ID '=' expr;
cmdselecao  -> 'se'(ID OPREL (ID | NUMBER)) { (cmd)+ } 
               ('senao'{ (cmd)+ })?
cmdenquanto -> 'enquanto'(ID OPREL (ID | NUMBER)) { (cmd)+ }
cmdfazenquanto -> 'faca'{ (cmd)+ }
                  'enquanto'(expr OPREL expr);
expr    -> TEXT | termo (OP termo)* | BOOLEAN
termo   -> ID | NUMBER
OP      -> '+' | '-' | '*' | '/'
OPREL   -> '>' | '<' | '>=' | '<=' | '==' | '!='
ID      -> [a-z] ([a-z] | [A-Z] | [0-9])*
NUMBER  -> [0-9]+ ('.' [0-9]+)?
TEXT    -> ([a-z] | [A-Z] | [0-9] | ' ')*
BOOLEAN -> 'Verdadeiro' | 'Falso'
```
