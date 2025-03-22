-- Désactiver les contraintes de clé étrangère
SET FOREIGN_KEY_CHECKS = 0;

-- Supprimer les données des tables dans l'ordre inverse des dépendances
DELETE FROM `file`;
DELETE FROM `google_drive_file`;
DELETE FROM `lead_action`;
DELETE FROM `lead_settings`;
DELETE FROM `ticket_settings`;
DELETE FROM `expense_settings`;
DELETE FROM `contract_settings`;
DELETE FROM `trigger_contract`;
DELETE FROM `trigger_ticket`;
DELETE FROM `trigger_lead`;
DELETE FROM `customer_budget`;
DELETE FROM `expense_alert`;
DELETE FROM `customer_expense`;
DELETE FROM `customer`;
DELETE FROM `customer_login_info`;
DELETE FROM `email_template`;
DELETE FROM `employee`;
DELETE FROM `user_roles`;
DELETE FROM `user_profile`;
DELETE FROM `oauth_users`;
DELETE FROM `users`;
DELETE FROM `currency`;



-- Réactiver les contraintes de clé étrangère
SET FOREIGN_KEY_CHECKS = 1;

-- Réinsérer les données par défaut
-- [ ROLES ]
INSERT IGNORE INTO `roles` (id, name)
VALUES (1, 'ROLE_MANAGER'), (2, 'ROLE_EMPLOYEE'), (3, 'ROLE_CUSTOMER')
ON DUPLICATE KEY UPDATE id = id;

-- [ CURRENCY ]
INSERT IGNORE INTO `currency` (id, libelle, val)
VALUES (1, 'Euro', 'EUR'), (2, 'Dollar', 'DOL'), (3, 'Arriary', 'Ar')
ON DUPLICATE KEY UPDATE id = id;

-- [ EXPENSE SETTINGS ]
INSERT into `expense_settings` (taux, date_taux) VALUES (100, '2025-01-01');

-- Réactiver les contraintes de clé étrangère si nécessaire
SET FOREIGN_KEY_CHECKS = 1;
