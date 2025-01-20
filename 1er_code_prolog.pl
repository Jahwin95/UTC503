% Base de données des recettes et leurs ingrédients
recette(pizza, [farine, eau, sel, levure, tomate, fromage]).
recette(salade, [laitue, tomate, concombre, vinaigre, huile]).
recette(pates_carbonara, [pates, creme, lardons, fromage, sel, poivre]).
recette(omelette, [oeufs, sel, poivre, fromage]).
recette(sandwich_jambon, [pain, beurre, jambon]).

% Ingrédients optionnels pour certaines recettes
optionnel(omelette, herbes).
optionnel(sandwich_jambon, salade).

% Règle vérifiant si tous les ingrédients nécessaires sont disponibles
ingredients_disponibles(Recette, IngredientsDisponibles) :-
    recette(Recette, IngredientsNecessaires),
    tous_ingredients_presents(IngredientsNecessaires, IngredientsDisponibles).

% Règle vérifiant si tous les ingrédients d'une liste sont présents
tous_ingredients_presents([], _).
tous_ingredients_presents([Ingredient|Reste], IngredientsDisponibles) :-
    membre(Ingredient, IngredientsDisponibles),
    tous_ingredients_presents(Reste, IngredientsDisponibles).

% Règle utilitaire pour vérifier si un élément est membre d'une liste
membre(X, [X|_]).
membre(X, [_|Queue]) :- membre(X, Queue).

% Règle principale pour suggérer des recettes
suggerer_recettes(IngredientsDisponibles, RecettesPossibles) :-
    findall(Recette, ingredients_disponibles(Recette, IngredientsDisponibles), RecettesPossibles).

% Règle pour afficher les ingrédients manquants d'une recette
ingredients_manquants(Recette, IngredientsDisponibles, Manquants) :-
    recette(Recette, IngredientsNecessaires),
    findall(Ingredient,
            (membre(Ingredient, IngredientsNecessaires),
             \+ membre(Ingredient, IngredientsDisponibles)),
            Manquants).