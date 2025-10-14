INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Maria', 'Brow','maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Lucas', 'Almeida','lucas@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Joana', 'Morais', 'joana.morais@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Beto', 'Freitas', 'beto.freitas@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Ana', 'Silva', 'ana.silva@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Carlos', 'Souza', 'carlos.souza@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Tati', 'Nascimento', 'tati.nascimento@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');


INSERT INTO tb_role (authority) VALUES ('ROLE_ALUNO');
INSERT INTO tb_role (authority) VALUES ('ROLE_PROFESSOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

-- Maria (Aluna)
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
-- Eu
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
-- Joana (Aluna)
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

-- Beto (Aluna)
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);

-- Ana (professora e aluna)
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 1);

-- Carlos (professor)
INSERT INTO tb_user_role (user_id, role_id) VALUES (6, 2);

-- Tati (admin e professora)
INSERT INTO tb_user_role (user_id, role_id) VALUES (7, 2);

-- CURSOS ONLINE

INSERT INTO tb_curso (nome, descricao, carga_horaria, preco, certificado_disponivel, professor_id) VALUES ('Java Moderno Online', 'Aprenda Java do básico ao avançado em Java.', 60, 350.00, TRUE, 5);
-- supondo id=1:
INSERT INTO tb_curso_online (id, validade_dias, qtd_aulas, qtd_capitulos) VALUES (1, 180, 45, 12);

INSERT INTO tb_curso (nome, descricao, carga_horaria, preco, certificado_disponivel, professor_id) VALUES ('Python para Dados', 'Data Science, Machine Learning e Python.', 80, 440.00, TRUE, 7);
-- supondo id=2:
INSERT INTO tb_curso_online (id, validade_dias, qtd_aulas, qtd_capitulos) VALUES (2, 300, 80, 20);

INSERT INTO tb_curso (nome, descricao, carga_horaria, preco, certificado_disponivel, professor_id) VALUES ('Matemática Aplicada', 'Matemática do dia a dia para engenharias.', 50, 210.00, FALSE, 2);
-- supondo id=3:
INSERT INTO tb_curso_presencial (id, local, data_inicio, data_fim, min_alunos, max_alunos) VALUES (3, 'Auditório Central', '2025-10-22 09:00:00', '2025-11-30 12:00:00', 10, 40);

INSERT INTO tb_curso (nome, descricao, carga_horaria, preco, certificado_disponivel, professor_id) VALUES ('Robótica Hands-On', 'Robótica e automação, projeto prático.', 70, 399.00, TRUE, 6);
-- supondo id=4:
INSERT INTO tb_curso_presencial (id, local, data_inicio, data_fim, min_alunos, max_alunos) VALUES (4, 'Lab 2 - Bloco B', '2025-11-03 10:00:00', '2025-11-25 17:00:00', 8, 35);

-- MATRICULA
INSERT INTO tb_matricula (aluno_id, curso_id, data_matricula, status) VALUES ( 1, 1, '2025-10-01 16:20:45', 0);
INSERT INTO tb_matricula (aluno_id, curso_id, data_matricula, status) VALUES ( 3, 1, '2025-10-03 10:22:30', 0);
INSERT INTO tb_matricula (aluno_id, curso_id, data_matricula, status) VALUES ( 4, 4, '2025-10-10 12:11:00', 1);

-- PAGAMENTOS
INSERT INTO tb_pagamento (moment, status, matricula_id, preco) VALUES('2025-10-01 17:02:15', 1, 1, 350.00);
INSERT INTO tb_pagamento (moment, status, matricula_id, preco) VALUES('2025-10-03 11:05:32', 1, 2, 350.00);
INSERT INTO tb_pagamento (status, matricula_id, preco) VALUES( 0, 3, 399.00);