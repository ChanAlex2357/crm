# Documentation des Controllers

## AccountConnectionController

- **GET /connect-accounts** : Affiche le formulaire de connexion des comptes.
- **POST /connect-accounts** : Connecte un compte régulier à un compte Google.
- **POST /discard-accounts** : Permet de refuser la connexion des comptes.

## LoginController

- **REQUEST /login** : Affiche la page de connexion.
- **GET /change-password** : Affiche le formulaire de confirmation de nom d'utilisateur.
- **POST /change-password** : Permet de changer le mot de passe de l'utilisateur.

## LogoutController

- **POST /logout** : Déconnecte l'utilisateur et redirige vers la page de connexion.

## ManagerController

- **GET /manager/all-users** : Affiche tous les utilisateurs.
- **GET /manager/show-user/{id}** : Affiche les informations d'un utilisateur spécifique.
- **GET /manager/register-user** : Affiche le formulaire d'inscription pour un nouvel utilisateur.
- **POST /manager/register-user** : Crée un nouvel utilisateur par le manager.
- **GET /manager/update-user/{id}** : Affiche le formulaire de mise à jour d'un utilisateur.
- **POST /manager/update-user** : Met à jour les informations d'un utilisateur.
- **GET & POST /set-employee-password** : Affiche le formulaire de création du mot de passe et le traite.
