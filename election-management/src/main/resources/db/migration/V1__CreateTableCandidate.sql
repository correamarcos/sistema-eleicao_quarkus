CREATE TABLE candidates(
    id varchar(40) NOT NULL,
    photo varchar(255) DEFAULT NULL,
    given_name varchar(50) NOT NULL,
    family_name varchar(50) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(50) DEFAULT NULL,
    job_title varchar(50) DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id));