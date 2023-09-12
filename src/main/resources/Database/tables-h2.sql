-- Criar tabela Users
CREATE TABLE Users (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    token_expiration TIMESTAMP,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    rg VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    document VARCHAR(255) NOT NULL UNIQUE,
    edukoins INT DEFAULT 0 NOT NULL,
    avatar VARCHAR(255),
    points INT DEFAULT 0
);

-- Criar tabela Levels
CREATE TABLE Levels (
    id VARCHAR(36) PRIMARY KEY,
    points INT NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- Criar tabela Classes
CREATE TABLE Classes (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    semester INT NOT NULL
);

-- Criar tabela Classes_Users
CREATE TABLE Classes_Users (
    id VARCHAR(36) PRIMARY KEY,
    class_id VARCHAR(36),
    user_id VARCHAR(36),
    FOREIGN KEY (class_id) REFERENCES Classes(id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Criar tabela Subjects
CREATE TABLE Subjects (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    teacher_id VARCHAR(36) NOT NULL,
    google_code VARCHAR(255)
);

-- Criar tabela Classes_Subjects
CREATE TABLE Classes_Subjects (
    id VARCHAR(36) PRIMARY KEY,
    subject_id VARCHAR(36),
    class_id VARCHAR(36),
    FOREIGN KEY (subject_id) REFERENCES Subjects(id),
    FOREIGN KEY (class_id) REFERENCES Classes(id)
);

-- Criar tabela Lessons
CREATE TABLE Lessons (
    id VARCHAR(36) PRIMARY KEY,
    date DATE NOT NULL,
    subject_id VARCHAR(36) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    justification VARCHAR(255),
    exam BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (subject_id) REFERENCES Subjects(id)
);

-- Criar tabela Users_Lessons
CREATE TABLE Users_Lessons (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    lesson_id VARCHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
);

-- Criar tabela Products
CREATE TABLE Products (
    id VARCHAR(36) PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image VARCHAR(255) NOT NULL
);

-- Criar tabela User_Shop
CREATE TABLE User_Shop (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    product_id VARCHAR(36),
    created_at DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);
