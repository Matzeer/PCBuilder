# PCBuilder

## Changelog

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

