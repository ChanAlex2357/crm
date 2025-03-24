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

ALTER TABLE  customer_budget add COLUMN `reste` DECIMAL(15,2);


CREATE TABLE customer_expense(
   expense_id INT AUTO_INCREMENT,
   date_expense DATE NOT NULL,
   amount DECIMAL(15,2)   NOT NULL,
   created_at DATETIME,
   updated_at DATETIME,
   ticket_id INT unsigned NOT NULL,
   lead_id INT unsigned  NOT NULL,
   budget_id INT NOT NULL,
   customer_id INT unsigned NOT NULL,
   PRIMARY KEY(expense_id),
   FOREIGN KEY(ticket_id) REFERENCES trigger_ticket(ticket_id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id),
   FOREIGN KEY(budget_id) REFERENCES customer_budget(budget_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

ALTER TABLE customer_expense ADD COLUMN `lead` TINYINT(1);
alter TABLE customer_expense ADD COLUMN `ticket` TINYINT(1);
ALTER TABLE customer_expense MODIFY COLUMN lead_id INT unsigned NULL;
ALTER TABLE customer_expense MODIFY COLUMN ticket_id INT unsigned NULL;


CREATE TABLE alert_type(
   id INT AUTO_INCREMENT,
   val VARCHAR(100) ,
   desce VARCHAR(100) ,
   PRIMARY KEY(id)
);


CREATE TABLE expense_alert(
   alert_id INT AUTO_INCREMENT,
   budget_amount DECIMAL(15,2)  ,
   taux DECIMAL(15,2)  ,
   message TEXT,
   date_alert DATE NOT NULL,
   status INT,
   budget_id INT NOT NULL,
   expense_id INT NOT NULL,
   alert_type_id INT NOT NULL,
   PRIMARY KEY(alert_id),
   FOREIGN KEY(budget_id) REFERENCES customer_budget(budget_id),
   FOREIGN KEY(expense_id) REFERENCES customer_expense(expense_id),
   FOREIGN KEY(alert_type_id) REFERENCES alert_type(id)
);


ALTER TABLE expense_alert ADD COLUMN expense_amount DECIMAL(15,2);
ALTER TABLE expense_alert DROP COLUMN expense ;


CREATE TABLE expense_settings(
   id INT AUTO_INCREMENT,
   taux DECIMAL(15,2)  ,
   date_taux DATETIME NOT NULL ,
   PRIMARY KEY(id)
);

CREATE or REPLACE VIEW  v_etat_budget AS
SELECT bu.budget_id,bu.amount,be.expense, (bu.amount - be.expense) as reste
FROM customer_budget bu 
JOIN (
   SELECT budget_id,SUM(amount) as expense
   FROM customer_expense ce
   GROUP BY ce.budget_id
) as be ON bu.budget_id = be.budget_id;

CREATE OR REPLACE VIEW customer_expense_cpl AS
SELECT 
    ce.expense_id,
    ce.date_expense,
    ce.amount,
    ce.created_at,
    ce.updated_at,
    ce.lead,
    ce.ticket,
    -- Customer information
    c.customer_id,
    c.name as customer_name,
    c.email as customer_email,
    -- Lead information
    l.lead_id,
    l.name as lead_name,
    l.status as lead_status,
    -- Ticket information
    t.ticket_id,
    t.subject as ticket_subject,
    t.status as ticket_status,
    t.priority as ticket_priority,
    -- Budget information
    cb.b
    cb.amount as budget_amount,
    cb.start_date as budget_start_date,
    cb.end_date as budget_end_date,
    -- Currency information
    cur.id as currency_id,
    cur.libelle as currency_name,
    cur.val as currency_value
FROM 
    customer_expense ce
    LEFT JOIN customer c ON ce.customer_id = c.customer_id
    LEFT JOIN trigger_lead l ON ce.lead_id = l.lead_id
    LEFT JOIN trigger_ticket t ON ce.ticket_id = t.ticket_id
    LEFT JOIN customer_budget cb ON ce.budget_id = cb.budget_id
    LEFT JOIN currency cur ON cb.currency = cur.id;