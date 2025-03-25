-- CREATE TABLE currency(
--    id INT AUTO_INCREMENT,
--    libelle VARCHAR(100) ,
--    val VARCHAR(100) ,
--    PRIMARY KEY(id)
-- );

CREATE TABLE customer_budget(
   budget_id INT AUTO_INCREMENT,
   amount DECIMAL(15,2) NOT NULL,
   description TEXT,
   customer_id INT unsigned NOT NULL,
   created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(budget_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);


CREATE TABLE customer_expense(
   expense_id INT AUTO_INCREMENT,
   date_expense DATE NOT NULL,
   amount DECIMAL(15,2) NOT NULL,
   ticket_id INT unsigned NULL,
   lead_id INT unsigned  NULL,
   customer_id INT unsigned NOT NULL,
   PRIMARY KEY(expense_id),
   FOREIGN KEY(ticket_id) REFERENCES trigger_ticket(ticket_id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

-- ALTER TABLE customer_expense MODIFY COLUMN lead_id INT unsigned NULL;
-- ALTER TABLE customer_expense MODIFY COLUMN 
-- ALTER TABLE customer_expense MODIFY COLUMN budget_id INT NULL;


-- CREATE TABLE alert_type(
--    id INT AUTO_INCREMENT,
--    val VARCHAR(100) ,
--    desce VARCHAR(100) ,
--    PRIMARY KEY(id)
-- );


-- CREATE TABLE expense_alert(
--    alert_id INT AUTO_INCREMENT,
--    budget_amount DECIMAL(15,2)  ,
--    taux DECIMAL(15,2)  ,
--    message TEXT,
--    expense_amount DECIMAL(15,2)  ,
--    date_alert DATE NOT NULL,
--    status INT,
--    budget_id INT NOT NULL,
--    expense_id INT NOT NULL,
--    alert_type_id INT NOT NULL,
--    PRIMARY KEY(alert_id),
--    FOREIGN KEY(budget_id) REFERENCES customer_budget(budget_id),
--    FOREIGN KEY(expense_id) REFERENCES customer_expense(expense_id),
--    FOREIGN KEY(alert_type_id) REFERENCES alert_type(id)
-- );


CREATE TABLE expense_settings(
   id INT AUTO_INCREMENT,
   taux DECIMAL(15,2)  ,
   date_taux datetime NOT NULL ,
   PRIMARY KEY(id)
);