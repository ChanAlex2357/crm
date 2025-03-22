CREATE TABLE Users(
   id INT AUTO_INCREMENT,
   email VARCHAR(100)  NOT NULL,
   hire_date DATETIME NOT NULL,
   password VARCHAR(255)  NOT NULL,
   created_at temestamp NOT NULL,
   updated_at temestamp NOT NULL,
   username VARCHAR(50) ,
   status VARCHAR(255) ,
   token VARCHAR(500) ,
   is_password_set TINYINT (1),
   PRIMARY KEY(id)
);

CREATE TABLE roles(
   id INT AUTO_INCREMENT,
   name VARCHAR(255) ,
   PRIMARY KEY(id)
);

CREATE TABLE file(
   file_id INT AUTO_INCREMENT,
   file_name VARCHAR(100) ,
   file_data BLOB,
   file_type VARCHAR(255) ,
   lead_id_ INT,
   contract_id INT,
   PRIMARY KEY(file_id)
);

CREATE TABLE google_drive_file(
   id INT AUTO_INCREMENT,
   drive_file_id VARCHAR(255) ,
   drive_folder_id VARCHAR(255) ,
   lead_id INT,
   contract_id INT,
   PRIMARY KEY(id)
);

CREATE TABLE employee(
   id INT AUTO_INCREMENT,
   username VARCHAR(45) ,
   last_name VARCHAR(45) ,
   first_name VARCHAR(45) ,
   email VARCHAR(45) ,
   password VARCHAR(80) ,
   provider VARCHAR(45) ,
   PRIMARY KEY(id)
);

CREATE TABLE user_profile(
   id INT AUTO_INCREMENT,
   first_name VARCHAR(50) ,
   last_name VARCHAR(50) ,
   phone VARCHAR(50) ,
   departement VARCHAR(255) ,
   salary DECIMAL(10,2)  ,
   status VARCHAR(255) ,
   oauth_user_image_link VARCHAR(255) ,
   user_image blob,
   bio TEXT,
   youtube VARCHAR(255) ,
   twitter VARCHAR(255) ,
   facebook VARCHAR(255) ,
   country VARCHAR(255) ,
   positio VARCHAR(255) ,
   adresse VARCHAR(255) ,
   user_id INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(user_id) REFERENCES Users(id)
);

CREATE TABLE email_template(
   template_id INT,
   name VARCHAR(255) ,
   content TEXT,
   json_design TEXT,
   created_at DATETIME,
   user_id INT NOT NULL,
   PRIMARY KEY(template_id),
   FOREIGN KEY(user_id) REFERENCES Users(id)
);

CREATE TABLE customer_login_info(
   id INT AUTO_INCREMENT,
   password VARCHAR(255) ,
   username VARCHAR(255) ,
   token VARCHAR(500) ,
   password_set TINYINT (1),
   PRIMARY KEY(id),
   UNIQUE(token)
);

CREATE TABLE currency(
   id INT AUTO_INCREMENT,
   libelle VARCHAR(100) ,
   val VARCHAR(100) ,
   PRIMARY KEY(id)
);

CREATE TABLE customer(
   customer_id INT AUTO_INCREMENT,
   name VARCHAR(255) ,
   phone VARCHAR(20) ,
   adresse VARCHAR(255) ,
   city VARCHAR(255) ,
   state VARCHAR(255) ,
   country VARCHAR(255) ,
   description TEXT,
   positio VARCHAR(255) ,
   twitter VARCHAR(255) ,
   facebook VARCHAR(255) ,
   youtube VARCHAR(255) ,
   email VARCHAR(255) ,
   created_at DATETIME,
   profile_id INT NOT NULL,
   user_id INT NOT NULL,
   PRIMARY KEY(customer_id),
   FOREIGN KEY(profile_id) REFERENCES customer_login_info(id),
   FOREIGN KEY(user_id) REFERENCES Users(id)
);

CREATE TABLE trigger_lead(
   lead_id INT AUTO_INCREMENT,
   name VARCHAR(255) ,
   phone VARCHAR(20) ,
   status VARCHAR(50) ,
   meeting_id VARCHAR(255) ,
   google_drive TINYINT (1),
   google_drive_folder_link VARCHAR(255) ,
   created_at DATETIME,
   customer_id INT NOT NULL,
   employee_id INT NOT NULL,
   PRIMARY KEY(lead_id),
   UNIQUE(meeting_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(employee_id) REFERENCES employee(id)
);

CREATE TABLE trigger_contract(
   contract_id INT,
   subject VARCHAR(255) ,
   status VARCHAR(100) ,
   description TEXT,
   start_date DATE,
   end_date DATE,
   amount DECIMAL(10,2)  ,
   google_drive TINYINT (1),
   google_drive_folder_link VARCHAR(255) ,
   created_at DATETIME,
   lead_id INT NOT NULL,
   user_id INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(contract_id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id),
   FOREIGN KEY(user_id) REFERENCES Users(id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE contract_settings(
   id INT AUTO_INCREMENT,
   amount TINYINT (1),
   subject TINYINT (1),
   description TINYINT (1),
   end_date TINYINT (1),
   start_date TINYINT (1),
   status TINYINT (1),
   customer_id INT NOT NULL,
   start_email_template INT NOT NULL,
   end_email_template INT NOT NULL,
   description_email_template INT NOT NULL,
   subject_email_template INT NOT NULL,
   amount_email_template INT NOT NULL,
   status_email_template INT NOT NULL,
   user_id INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(start_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(end_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(description_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(subject_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(amount_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(status_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(user_id) REFERENCES Users(id)
);

CREATE TABLE lead_settings(
   id INT AUTO_INCREMENT,
   status TINYINT (1),
   meeting TINYINT (1),
   phone TINYINT (1),
   name TINYINT (1),
   user_id INT NOT NULL,
   status_email_template INT NOT NULL,
   phone_email_template INT NOT NULL,
   meeting_email_template INT NOT NULL,
   name_email_template INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(user_id) REFERENCES Users(id),
   FOREIGN KEY(status_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(phone_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(meeting_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(name_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE ticket_settings(
   id INT AUTO_INCREMENT,
   priority TINYINT (1),
   subject TINYINT (1),
   description TINYINT (1),
   status TINYINT (1),
   status_email_template INT NOT NULL,
   subject_email_template INT NOT NULL,
   priority_email_template INT NOT NULL,
   description_email_template INT NOT NULL,
   user_id INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(status_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(subject_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(priority_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(description_email_template) REFERENCES email_template(template_id),
   FOREIGN KEY(user_id) REFERENCES Users(id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE customer_budget(
   budget_id INT AUTO_INCREMENT,
   amount DECIMAL(15,2)   NOT NULL,
   start_date DATE,
   end_date DATE,
   description TEXT,
   currency INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(budget_id),
   FOREIGN KEY(currency) REFERENCES currency(id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE lead_action(
   id INT AUTO_INCREMENT,
   action VARCHAR(255) ,
   date_time DATETIME,
   lead_id INT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id)
);

CREATE TABLE user_roles(
   user_id INT,
   role_id INT,
   PRIMARY KEY(user_id, role_id),
   FOREIGN KEY(user_id) REFERENCES Users(id),
   FOREIGN KEY(role_id) REFERENCES roles(id)
);
