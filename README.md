# Projeto-de-Compiladores-2023
Projeto de Compiladores

# Gramatica 
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
