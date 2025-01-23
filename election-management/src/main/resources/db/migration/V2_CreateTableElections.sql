CREATE TABLE elections(
    id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id));

CREATE TABLE election_candidate(
    election_id VARCHAR(40) NOT NULL,
    candidate_id VARCHAR(40) NOT NULL,
    votes INTEGER default 0,
PRIMARY KEY (election_id, candidate_id));

INSERT INTO candidates(id, photo, given_name, family_name, email, phone, job_title) VALUES
('00000001','http://dummyimage.com/161x100.png/cc0000/ffffff','Laurence','Canter','lcanter0@tamu.edu','132-789-7720','Mechanical Systems Engineer'),
('00000002','http://dummyimage.com/122x100.png/ff4444/ffffff','Reinhard','Scougal','rscougal1@weather.com','267-844-8937','Executive Secretary'),
('00000003','http://dummyimage.com/118x100.png/5fa2dd/ffffff','Bill','Pirri','bpirri2@ifeng.com','710-797-5495','Recruiting Manager'),
('00000004','http://dummyimage.com/132x100.png/cc0000/ffffff','Henka','Leathart','hleathart3@infoseek.co.jp','237-115-4427','Human Resources Manager'),
('00000005','http://dummyimage.com/158x100.png/dddddd/000000','Eal','Rosebotham','erosebotham4@blog.com','217-214-4922','Marketing Assistant');

