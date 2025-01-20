% Définition des faits de base
homme(jacques).
homme(marc).
homme(paul).
femme(marie).
femme(sophie).
femme(julie).

% Relations parent
parent(jacques, marc).    % Jacques est le père de Marc
parent(marie, marc).     % Marie est la mère de Marc
parent(marc, paul).      % Marc est le père de Paul
parent(julie, paul).     % Julie est la mère de Paul
parent(marc, sophie).    % Marc est le père de Sophie
parent(julie, sophie).   % Julie est la mère de Sophie

% Règles pour père et mère
pere(X, Y) :- homme(X), parent(X, Y).
mere(X, Y) :- femme(X), parent(X, Y).

% Règle pour les grands-parents
grand_parent(X, Z) :- parent(X, Y), parent(Y, Z).

% Règle pour les frères et sœurs
% Deux personnes sont frères/sœurs si elles ont un parent en commun et sont différentes
frere_soeur(X, Y) :- parent(P, X), parent(P, Y), X \= Y.

% Règle pour les oncles et tantes
% Une personne est oncle/tante si elle est frère/sœur d'un parent
oncle_tante(X, Y) :- parent(P, Y), frere_soeur(X, P).

% Règles pour manipuler les listes
% Calcul de la longueur d'une liste
longueur([], 0).
longueur([_|Queue], N) :- longueur(Queue, N1), N is N1 + 1.

% Vérifier si un élément est dans une liste
membre(X, [X|_]).
membre(X, [_|Queue]) :- membre(X, Queue).