CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE Users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email TEXT  NOT NULL,
    password TEXT NOT NULL,
    token TEXT,
    token_expiration TIMESTAMP,
    cpf TEXT NOT NULL UNIQUE,
    rg TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    role TEXT NOT NULL,
    document TEXT NOT NULL UNIQUE,
    edukoins INT DEFAULT 0 NOT NULL,
    avatar TEXT NULL,
    points INT DEFAULT 0
);


CREATE TABLE Levels (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    points INT NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE Classes (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    semester INT NOT NULL
);

CREATE TABLE Classes_Users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    class_id UUID REFERENCES Classes(id) NOT NULL,
    user_id UUID REFERENCES Users(id) NOT NULL
);

CREATE TABLE Subjects (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT,
    teacher_id UUID REFERENCES Users(id) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    week_day INT NOT NULL,
    google_code TEXT NULL
);

CREATE TABLE Classes_Subjects (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    subject_id UUID REFERENCES Subjects(id) NOT NULL,
    class_id UUID REFERENCES Classes(id) NOT NULL
);



CREATE TABLE Lessons (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date DATE NOT NULL,
    description TEXT NULL,
    subject_id UUID REFERENCES Subjects(id) NOT NULL,
    active BOOLEAN DEFAULT true,
    justification TEXT NULL,
    exam BOOLEAN DEFAULT false
);

CREATE TABLE Users_Lessons (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID REFERENCES Users(id) NOT NULL,
    lesson_id UUID REFERENCES Lessons(id) NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE Products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_name TEXT NOT NULL,
    price INT NOT NULL,
    image TEXT NOT NULL
);

CREATE TABLE User_Shop (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID REFERENCES Users(id) NOT NULL,
    product_id UUID REFERENCES Products(id) NOT NULL,
    created_at DATE DEFAULT now() NOT NULL
);

