# Dictionnaire de recettes et leurs ingrédients
recettes = {
    "pizza": ["farine", "eau", "sel", "levure", "tomate", "fromage"],
    "salade": ["laitue", "tomate", "concombre", "vinaigre", "huile"],
    "pates_carbonara": ["pates", "creme", "lardons", "fromage", "sel", "poivre"],
    "omelette": ["oeufs", "sel", "poivre", "fromage", "herbes"],
    "sandwich_jambon_beurre": ["pain", "beurre", "jambon", "salade"]
}

# Fonction pour trouver les recettes réalisables
def recettes_possibles(ingredients_disponibles):
    recettes_realisees = []
    for recette, ingredients in recettes.items():
        # Vérifie si tous les ingrédients d'une recette sont dans la liste fournie
        if all(ingredient in ingredients_disponibles for ingredient in ingredients):
            recettes_realisees.append(recette)
    return recettes_realisees

# Exemple d'utilisation
if __name__ == "__main__":
    # Liste des ingrédients disponibles
    ingredients_utilisateur = ["farine", "eau", "sel", "levure", "tomate", "fromage", "huile"]

    # Affiche les recettes possibles
    resultat = recettes_possibles(ingredients_utilisateur)
    print("Recettes possibles :", resultat)
