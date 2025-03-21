# Documentation du Projet

## Structure du Projet

Le projet suit la structure standard d'une application Spring Boot :

- **src/main/java** : Contient le code source de l'application.
  - **controllers** : Gère les requêtes HTTP et la logique de routage.
  - **services** : Contient la logique métier et les traitements application.
  - **repositories** : Gère l'accès aux données et les interactions avec la base de données.
- **src/main/resources** : Contient les fichiers de configuration (comme application.properties), les templates, et les ressources statiques.

## Design Patterns Utilisés

Les principaux design patterns appliqués sont :

- **Modèle-Vue-Contrôleur (MVC)** : Sépare la logique de présentation et la logique métier.
- **Service/Repository Pattern** : Isolent la logique business et l'accès aux données.
- **Singleton (si applicable)**: Pour les composants nécessitant une instance unique.
- **Factory (si applicable)**: Pour la création d'instances complexes.

## Converter

Le converter est responsable de la transformation des données d'un format à un autre. Par exemple, il peut convertir des objets Data Transfer Objects (DTO) en entités métier ou inversement, facilitant ainsi la séparation entre la couche de présentation et la logique métier.

## Méthodes Générales

Les méthodes génériques incluent :

- **Gestion des erreurs et exceptions** : Méthodes centralisées pour le traitement des erreurs.
- **Validations** : Vérifications communes des entrées utilisateur avant de passer à la logique business.
- **Utilitaires** : Fonctions communes pour le formatage et le parsing des données.
