CREATE TABLE currency(
   id INT AUTO_INCREMENT,
   libelle VARCHAR(100) ,
   val VARCHAR(100) ,
   PRIMARY KEY(id)
);

CREATE TABLE customer_budget(
   budget_id INT AUTO_INCREMENT,
   amount DECIMAL(15,2)   NOT NULL,
   start_date DATE,
   end_date DATE,
   description TEXT,
   currency INT NOT NULL,
   customer_id INT unsigned NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(budget_id),
   FOREIGN KEY(currency) REFERENCES currency(id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);


CREATE TABLE customer_expense(
   expense_id INT AUTO_INCREMENT,
   date_expense DATE NOT NULL,
   amount DECIMAL(15,2)   NOT NULL,
   created_at DATETIME,
   updated_at DATETIME,
   ticket_id INT NOT NULL,
   lead_id INT NOT NULL,
   budget_id INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(expense_id),
   FOREIGN KEY(ticket_id) REFERENCES trigger_ticket(ticket_id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id),
   FOREIGN KEY(budget_id) REFERENCES customer_budget(budget_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);