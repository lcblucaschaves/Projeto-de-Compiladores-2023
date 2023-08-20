# Projeto-de-Compiladores-2023
##Projeto de Compiladores

## Gramatica 
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
cmdenquanto -> 'enquanto'(ID OPREL (ID | NUMBER)) { (cmd)+ } <br />
cmdfazenquanto -> 'faca'{ (cmd)+ } <br />
                  'enquanto'(expr OPREL expr); <br />
expr    -> TEXT | termo (OP termo)* | BOOLEAN <br />
termo   -> ID | NUMBER <br />
OP      -> '+' | '-' | '*' | '/' <br />
OPREL   -> '>' | '<' | '>=' | '<=' | '==' | '!=' <br />
ID      -> [a-z] ([a-z] | [A-Z] | [0-9])* <br />
NUMBER  -> [0-9]+ ('.' [0-9]+)? <br />
TEXT    -> ([a-z] | [A-Z] | [0-9] | ' ')* <br />
BOOLEAN -> 'Verdadeiro' | 'Falso' <br />
