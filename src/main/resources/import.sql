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

-- RELACIONAMENTO CURSO X ALUNOS
-- (A tabela é: tb_curso_aluno (curso_id, aluno_id))

-- Maria (1), Joana (3), Beto (4) no Java Moderno Online (curso id=1)
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (1, 1);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (1, 3);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (1, 4);

-- Joana (3) e Beto (4) no Python para Dados (curso id=2)
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (2, 3);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (2, 4);

-- Maria (1) e Joana (3) em Matemática Aplicada (curso id=3)
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (3, 1);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (3, 3);

-- Maria (1), Beto (4) e Joana (3) em Robótica Hands-On (curso id=4)
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (4, 1);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (4, 4);
INSERT INTO tb_curso_aluno (curso_id, aluno_id) VALUES (4, 3);