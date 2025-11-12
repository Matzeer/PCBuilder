# PCBuilder
 
PCBuilder est une application Java en console permettant de composer, enregistrer et exporter des configurations PC à partir d'un catalogue de composants maintenu dans un fichier CSV. L'application propose une interface textuelle guidée qui accompagne l'utilisateur dans la sélection de chaque pièce, avec des outils de tri, de filtrage et de limitation des résultats.
 
## 🚀 Fonctionnalités principales

- **Parcours guidé** : menu interactif pour créer une nouvelle configuration ou gérer les configurations sauvegardées.
- **Catalogue riche** : plus de 36 000 références issues de `src/main/resources/data/components.csv`, couvrant CPU, GPU, RAM, stockage, cartes mères, boîtiers et alimentations.
- **Filtres dynamiques** : sélection d'une catégorie puis application de tris (croissant/décroissant) sur les attributs pertinents (prix, fréquence, capacité, etc.) et limitation du nombre de résultats.
- **Sauvegarde en mémoire** : conservation temporaire des PC construits durant la session, avec possibilité de les recharger pour continuer les modifications.
- **Export JSON** : génération d'un fichier JSON regroupant toutes les configurations sauvegardées via l'exportateur `JsonComputerExporter`.

## 🧱 Architecture du projet
 
Le code suit une séparation en couches inspirée de l'architecture hexagonale :

- `domain/` : entités métier immutables (CPU, GPU, Computer, etc.).
- `application/` : cas d'usage orchestrant les interactions (sélection de composants, ajout d'une configuration, export...).
- `infrastructure/` : implémentations techniques (lecture du CSV, export JSON, contrôleur principal).
- `presentation/` : présentateurs et vues console responsables de l'affichage et de la collecte des entrées utilisateur.
- `shared/` : définitions communes, notamment les énumérations de catégories et paramètres de requête.
 Cette organisation permet de remplacer facilement une source de données (CSV → API) ou une vue (console → interface graphique) sans impacter le cœur métier.
 
## 📦 Prérequis
 
- Java 21 (ou version compatible avec le paramètre `maven.compiler.target` du `pom.xml`).
- Apache Maven 3.9+.
 
## 🔧 Installation
 
1. Cloner le dépôt :
   ```bash
   git clone <URL_DU_DEPOT>
   cd PCBuilder
   ```
2. Télécharger les dépendances et compiler :
   ```bash
   mvn clean package
   ```
 
## 🖥️ Utilisation
 
Lancer l'application console depuis l'artefact construit :
```bash
java -cp target/PCBuilder-1.0-SNAPSHOT.jar fr.esiea.pcbuilder.Main
```
 
### Parcours de configuration
 
1. Sélectionner `c` pour créer/configurer un PC.
2. Choisir un composant (1 à 7) parmi CPU, GPU, RAM, Stockage, Carte mère, Alimentation ou Boîtier.
3. Ajuster les filtres :
   - `f` pour modifier la liste des tris disponibles sur la catégorie courante (prix, fréquence, capacité...).
   - `l` pour changer la limite de résultats (par défaut 10).
4. Saisir le numéro du composant souhaité pour l'ajouter à la configuration courante.
5. Répéter pour chaque catégorie, puis :
   - `s` pour sauvegarder la configuration dans la session en cours.
   - `c` pour charger une configuration précédemment sauvegardée.
   - `z` pour revenir au menu principal.
 
### Export des configurations
 
1. Depuis le menu principal, choisir `e`.
2. Indiquer le chemin du fichier de sortie (l'extension `.json` est ajoutée automatiquement si absente).
3. Le fichier JSON contiendra toutes les configurations sauvegardées durant la session.
 
## 🗂️ Données des composants
 
Le catalogue par défaut est un fichier CSV (`src/main/resources/data/components.csv`) comprenant les colonnes suivantes (extrait) :
 
| Colonne             | Description                                                |
|---------------------|------------------------------------------------------------|
| `category`          | Type de composant (case, cpu, video_card, etc.).           |
| `name`              | Nom commercial du composant.                               |
| `price`             | Prix indicatif en euros.                                   |
| `grade`             | Note agrégée provenant de la source d'origine.             |
| Champs spécifiques  | Fréquences (`core_clock`, `boost_clock`), capacités, etc., selon la catégorie. |
 
+Vous pouvez enrichir ou remplacer ce fichier pour utiliser votre propre catalogue. La lecture est assurée par `CsvComponentRepository`, basé sur Apache Commons CSV.
 
## 🧪 Tests
 
Le projet inclut une couverture de tests unitaires et d'intégration (JUnit 4/5, Mockito) pour valider les cas d'usage, les entités et les opérations d'import/export. Pour les exécuter :
 
```bash
mvn test
```
 
## 📁 Structure du dépôt
 
```
PCBuilder/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/fr/esiea/pcbuilder/
    │   │   ├── application/
    │   │   ├── domain/
    │   │   ├── infrastructure/
    │   │   ├── presentation/
    │   │   └── shared/
    │   └── resources/data/components.csv
    └── test/java/fr/esiea/pcbuilder/

```