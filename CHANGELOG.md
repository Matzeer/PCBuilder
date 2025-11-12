# PCBuilder

## Changelog

### V1.0.1 - 12/11/2025

#### Ajouts

#### Documentation
- Ajout du README.md pour expliquer le projet et son architecture.
- Ajout du DIAGRAMME.md pour illustrer l'architecture du projet.

#### Refactor

- Déplacement de la view et presenters dans le package presentation

### V1.0.0 - 3/11/2025

#### Ajouts

#### Use Cases

- Ajout d'un use case pour lister les configurations de PC sauvegardées.
- Ajout d'un use case pour sauvegarder une configuration de PC.
- Ajout d'un use case pour créer un pc vide

#### entities
- Ajout d'un id sur l'entité Computer.

#### DTO
- Ajout de mappers pour convertir des dto en entités et inversement.
- Adaptation du DTO ComputerDto pour inclure l'id.

#### Gateway
- Ajout de l'interface view

#### Controller

- Ajout du controller principal pour gérer les configurations de PC.

#### Presenter

- Ajout d'un helper pour traduire les champs en français et centrer les tableaux
- Ajout d'une liste d'attribut pour la console
- Ajout d'un presenter pour le menu principal
- Ajout d'un presenter pour afficher la configuration du pc
- Ajout d'un presenter pour afficher la liste d'un type de composant
- Ajout d'un presenter pour afficher la configuration des filtres
- Ajout d'un presenter pour configurer le tri de la liste
- Ajout d'un presenter pour configurer la limite
- Ajout d'un presenter pour charger une configuration de pc existante
- Ajout d'un presenter pour exporter les configurations de pc

#### view
- Ajout d'une view en console de dev




### V0.1.0 - 31/10/2025

#### Ajouts

#### entities
- Implémentation des entités principales : `Computer`, `Cpu`, `Gpu`, `MotherBoard`, `Ram`, `Storage`, `PowerSupply`, `Case`.

#### enums 
- Ajout des catégories de composants (`Categories`).
- Ajout des paramères de tri (`QueryParams`).

#### Use Cases
- `ListComponentUseCase` : liste les composants filtrés et triés.
- `SelectionComponentUseCase` : sélectionne et assigne un composant au PC courant.
- `ExportComputerUseCase` : exporte la configuration au format JSON.
- `SelectFiltersUseCase` : gère les filtres de recherche des composants.

#### DTO 
- Création des DTO pour chaque entité et d’un `ComputerDto` regroupant les composants.
- Création d'un DTO `FiltersDto` pour la gestion des filtres.

#### Gateway
- Création de l’interface `ComputerGateway` et d’un dépôt mémoire (`InMemoryRepository`).
- Création de l'interface `ComponentGateway` et d’un dépôt CSV (`CsvComponentRepository`).

#### Tests
- Ajout de tests unitaires pour nos use cases.
- Ajout de tests unitaires nos entities.
- Ajout de tests unitaires pour les gateways.
- Création de factories pour faciliter la création d’objets dans les tests.

#### Divers
- Création de l’architecture du projet selon les principes de Clean Architecture (domain, application, infrastructure, interface).
- Intégration du fichier CSV de composants.
- Ajout d'une CI/CD pour lancer les tests automatiquement.
- Nettoyage du code et respect des conventions Java.

