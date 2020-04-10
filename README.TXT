

# jagger-HEURTEBIZE 
 
## HEURTEBIZE Théo

### Rendu du projet :

#### Fonctionnalités implémentées :

 - make marche
 - make check marche
 - Les flottants avec les bonnes priorités
	 - Pretty printer
 - Une primitive print
 - Support de if-then-else
 - Support des chaînes de caractères
	 - Type checker
 - Support des variables et scopes
	 - Déclaration
	 - Binder
	 - Renamer
 - Affectation
 - Support de while
 - Support de for
 - And beyond


----------

###  Langage du projet :


Etant donné qu'il fallait un opérateur par lot, mon Jagger prend uniquement des fichiers en entrée.
J'ai fait en sorte de respecter les énoncés, la fonctionnement est donc le suivant:

#### Flottants avec les bonnes priorités :
Les unaires "+" et "-" sont prioritaires sur les binaires "*" et "/", eux mêmes prioritaires sur les binaires "+" et "-", qui sont eux prioritaires sur toutes les comparaisons.

Les parenthèses permettent de prioriser une expression.


#### Primitive print :
 Pour pouvoir afficher le résultat d'évaluation d'une expression, il faut la mettre dans la fonction prédéfinie "print(expression)".
	La fontion "print()" prend obligatoirement une expression de type FLOAT ou STRING, et est de type VOID.
 
Même sans la commande "print()" le Pretty_Printer affichera le code de manière à mettre en évidence les priorités.
  

#### Support de if-then-else :
 Pour le "if...then...else", seul une expression évaluée en FLOAT peut être mise en condition du "if", une erreur est remontée dans le cas contraire.
L'expression évaluée en sortie du est "else" si la condition du "if" est évaluée à la valeur 0.	 

  Si les expressions de "then" et "else" n'ont pas le même type, une erreur est remontée.
  
#### Support des chaînes de caractères :
Les expressions de type STRING peuvent être concaténées entre elle ou avec un FLOAT avec le binaire "+". C'est la seule opération binaire que supporte le type STRING.

  Les comparaisons ne peuvent se faire qu'entre expressions du même type, à l'exception des VOID qui ne peuvent pas être comparées.  

 - ##### Type Checker:
	  La vérification d'erreurs de typage est fait pas le visiteur VisitorTypeChecker de manière statique avant l'évaluation. Si une erreur est détectée, l'évaluation n'a pas lieu.


#### Support des variables et scopes :
Un scope peut être créé comme suit :
- "let" suivi de la déclaration des variables, il n'y a pas de séparateur (",") entre les déclarations;
- "in" suivi des instructions à évaluer;
- "end" pour fermer le scope.
	
Le type des scopes est VOID.	
 Les déclarations sont stockées dans une LinkedHasmap<String,VariableDecl>. L'avantage d'une LinkedHashMap c'est qu'elle garde une   relation d'ordre.  
Les instruction sont enregistrées dans une ArrayList\<Expression> en attribut d'un objet Instructions.
Chaque instructions doivent être séparées par le caractère ",".

- ##### Déclaration :
	La déclaration de variables ne ce fait qu'entre un "let" et un "in", et s'écrit:
	"\<VARNAME> := Expression" 
	 Comme il n'était pas préciser si les variables peuvent être de type VOID, j'ai décidé de lever une erreur dans 	ce cas. 	Ainsi les variables ne peuvent être que de type FLOAT ou STRING.
  
	 Si deux variables du même nom sont déclarées, seule la première est retenue, une erreur est remontée et l'évaluation est annulée.
 	Une variable ne peut pas être déclarée sans lui attribuer une expression.

Pour pouvoir utiliser des scopes, notamment imbriqués, il a fallut créer un Binder et un Renamer.

- ##### Binder :
	 Le visiteur VisitorBinder est exécuté après le visiteur VisitorPretty_Printer, il s'occupe de lier les variables à leur déclarations.
  Pour cela il est créé pour chaque scope une LinkedHashMap contenant le nom des variables déclarées pairées avec leur déclaration.
  
	 Quand on entre dans un nouveau scope, on met la LinkedHashMap dans une pile d'environnements.
	 On va ensuite regarder pour chaque variable dans les instructions entre "in" et "end" si une variable ayant le même nom a été déclarée dans le scope courant.
  
	 - Si oui on ajoute la délcaration dans les attributs de la variable.
  - Si non on regarde dans le scope parent (environnement suivant dans  la pile) si une variable du même nom	a été déclarée, et on recommence jusqu'à avoir une correspondance.
  - Si jamais on parcours tous les environnements sans trouver de déclaration du même nom de variable, on remonte une erreur et l'évaluation ne peut pas avoir lieu.
  
  Quand on a fini de binder un scope, on dépile ses déclaration de la pile environnements.

- ##### Renamer :
	Le visiteur VisitorRenamer est exécuté après le visiteur VisitorBinder, il s'occupe de renommer les noms des déclarations de variables. Comme les variables et leur déclaration sont déjà liées, il n'y pas de risque de perte.

 	La déclaration est renommée en fonction de sa position d'apparition dans le total des déclarations.	Ainsi, la variable est renommée comme suit : *nomdéclaré_position*.
  
 	Cela permet d'éviter les confusions entre deux variables ayant été déclarées dans différents scopes.

#### Affectation :
 L'affection de valeur à une variable déclarée ce fait entre "in" et "end". Pour ce faire il faut écrire:
	"nomdéclaré := expression"
	
  L'expression doit impérativement être du même type que la variable déclarée.

#### Support de while :
Pour effectuer une boucle While, il faut écrire "while Comparaison do (instructions)".Si il n'y a qu'une seule instruction, les parenthèses sont optionnelles.
 Pour évaluer "while", on évalue d'abord la condition, puis les instructions. Une fois les instructions évaluées, on réévalue la condition et ainsi de suite jusqu'à ce qu'elle soit égale à 0.
 
 Un while est de type VOID.

#### Support de for :
La boucle For n'a pas nécessité de créer de classe spécifique car elle est réalisable avec un désucrage. Elle marche comme suit :
- "for" suivi de la déclaration d'une variable de type FLOAT.
- "to"	suivi d'une expression de type FLOAT.
- "do" suivi des instructions entre parenthèses. Si n'y en a qu'une, les parenthèses sont optionnelles.
 
Pour créer une boucle For, le compilateur créer un nouveau scope et déclare la variable derrière le "for".
 
Après l'expression pour le "to", une nouveau While est créé avec pour condition que la variable précédemment déclarée soit plus petite ou égale à l'expression du "to".

Après le "do" on récupère les instructions, et on leur ajoute une expression incrémentant la valeur de la variable déclarée. 
On attribut ces instructions au While, puis on créer un nouvel objet Instructions dans lequel on met le While. 
On attribut ce nouvel objet aux Instructions du scope, qu'on retourne enfin.

#### And beyond :
Comme il n'était pas précisé s'il fallait obligatoirement que le code exécuté soit dans un scope, j'ai permis l'exécution d'instruction hors des scopes.


----------


### Grammaire :


| Symbole|Composition  |Description |
|--|--|--|
|mainloop|I \<EOF>|Mainloop|
|I|S (","S)*|Instructions|
|S| P\|ITE\|A\|Scope\|W\|F|Expression|
|P |"print("Comp")"|Print|
|ITE |"if"(Comp\|ITE)"then"S"else"S|If-then-else|
|A | Comp(":="(Comp\|ITE))?|Affectation|
|Scope|let(D)*"in"I"end"|Scope|
|W | \<WHILE>Comp\<DO>"("I")"\|S|Whilestatement|
|For | \<FOR>S\<TO>S\<DO>"("I")"\|S|Forstatement|
|D | var \<VARNAME> := (Comp\|ITE)|Declaration|
|Comp | E("="Comp\|"<>"Comp\|"<"Comp \|">"Comp\|">="Comp\|"<="Comp)*|Comparaison|
|E | T("+"T\|"-"T)*|Binaire "+" "-"|
|T | U("*"U\|"/"U)*	|Binaire "*" "/"|
|U | ("-"F)\|("+"F)\|F|Unaire|
|F | \<VARNAME>\|\<NUMBER>\|\<STRING>\| "(" Comp ")"|Facteur|

<br/><br/>

|Token|Regex|Description|
|--|--|--|
|\<NUMBER>| (\<DIGIT>)+ ("." (\<DIGIT>)*)? |  Nombre décimal|
|\<STRING>| "\\""(~["\\""])*"\\"" |Chaîne de caractère|
|\< DIGIT>| ["0"-"9"] |Chiffre|
|\< IF>| "if" | If|
|\< THEN>|"then" |Then
|\< ELSE>| "else" |Else
|\< PRINT>| "print"|Print
|\< LET>| "let" |Let
|\< IN>| "in" |In
|\< END>| "end"|End
|\< VAR>| "var"|Déclaration
|\<ASSIGN>| ":=" |Affectation
|\<WHILE>| "while"|While
|\<DO>| "do"|Do
|\<FOR>| "for"|For
|\<TO>| "to"|To
|\< VARNAME>| \["a"-"z","A"-"Z"](["a"-"z","A"-"Z","0"-"9"])*|Nom de variable


----------


### Ligne de compilation exécution:
#### Compiler pour tester manuellement :
	1. make
	2. java  Jagger ./<fichier>.txt
#### Compiler et tester :
##### Batterie de tests positifs:
	 1. make check
##### Test négatifs:
	 1. make fail


----------
### Difficultés :

 - Réussir à commencer le scope et les variables a été une grosse difficultés pour moi. Je ne comprenais pas. Quentin Garrido m'a expliqué la logique derrière les scopes et variables et cela m'a permis d'avancer.
 - Permettre de mettre:
 
		let foo:=1
			bar:=1
			baz:=1
		in
			foo, bar*baz
		end
	  Le problème était que commencer une expression par un \<VARNAME> était uniquement considéré comme un 	assignement, donc le parseur attendait:		
	 - "foo:=..." au lieu de "foo,..."
	 - "bar:=..." au lieu de "bar*..."
  
	La solution a été de redéfinir l'assignment dans le parseur, et de le définir comme une comparison avec 	optionnellement un assignment, le token \<VARNAME> étant forcément atteint dans factor. Il a aussi fallu enlever le comparison de statement pour qu'il ne passe plus que par assignement.


----------

### Points non abordés :
Aucun
