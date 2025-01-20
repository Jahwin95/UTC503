RECETTES: Dict[str, List[str]] = {
    'Pizza': ['farine', 'eau', 'sel', 'levure', 'tomate', 'fromage'],
    'Salade': ['laitue', 'tomate', 'concombre', 'vinaigre', 'huile'],
    'Pates Carbonara': ['pates', 'creme', 'lardons', 'fromage', 'sel', 'poivre'],
    'Omelette': ['oeufs', 'sel', 'poivre', 'fromage'],
    'Sandwich': ['pain', 'beurre', 'jambon']
}

def verifier_ingredients(ingredients_necessaires: List[str], ingredients_disponibles: List[str]) -> bool:
    return all(ingredient in ingredients_disponibles for ingredient in ingredients_necessaires)

def trouver_recettes_possibles(ingredients_disponibles: List[str]) -> List[str]:
    return list(filter(
        lambda nom_recette: verifier_ingredients(RECETTES[nom_recette], ingredients_disponibles),
        RECETTES.keys()
    ))

def afficher_resultats(ingredients: List[str], recettes: List[str]) -> None:
    print(f"\nIngrédients disponibles: {', '.join(ingredients)}")
    print("Recettes possibles:")
    print("\n".join(map(lambda x: f"- {x}", recettes)) if recettes else "Aucune recette possible")

if name == "main":
    ingredients_test = ['sel', 'poivre', 'oeufs', 'fromage']
    recettes_trouvees = trouver_recettes_possibles(ingredients_test)
    afficher_resultats(ingredients_test, recettes_trouvees)