# Crud Not Simple

<img width=250 src="https://i.ibb.co/BVj8mPp/over-engenniring-1.gif"></img> 
over-engineering...

[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

## 🚀 Description

There's a lot of talk about project complexity, project standards and best development practices, but what if I told you that you could end up over-engineering simple problems?! Well, this project aims to create a reflection on how unnecessary over-engineering can be for a crud of people that technically should have a simple implementation, but has a large load of complex abstractions.

In this project you will see:

- Clean architecture
- Domain-driven design
- Docker
- Design patterns
- Automated testing
- etc.

**translate to portuguese**:

Muito se fala sobre complexidade de projetos, sobre padrões de projetos, sobre melhores práticas de desenvolvimento, mas e se eu te falar que você pode acabar criando um excesso de engenharia para problemas simples?! Pois bem, este projeto visa criar uma reflexão sobre o quão pode ser desnecessário excesso de engenharia para um crud de pessoas que tecnicamente deveria ter uma implementação simples, mas possui uma grande carga de complexidade de abstrações.

Neste projeto você verá:

- Arquitetura limpa
- Design dirigido por domínio
- Docker
- Padrões de projeto
- Testes automatizados
- etc.

## Technologies

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)

#### Requirements to run:
- docker

- docker compose

#### Requirements to developement:
- jdk 21
- docker
- docker compose

## Features

- ![status](https://img.shields.io/badge/Saving_a_new_person-OK-greene)
- ![status](https://img.shields.io/badge/Logically_delete_a_person-OK-greene)
- ![pendding](https://img.shields.io/badge/Find_people_by_year_range-PENDDING-orange)
- ![status](https://img.shields.io/badge/Find_a_person_by_id-OK-greene)
- ![pendding](https://img.shields.io/badge/Find_people_by_name-PENDDING-orange)
- ![status](https://img.shields.io/badge/Update_person,_locating_by_id-OK-greene)
- ![pendding](https://img.shields.io/badge/Sending_an_email_after_saving_a_person_in_DB-OK-greene)

## Running locally

Clone project

```bash
  git clone https://link-para-o-projeto
```

Go into the project directory

```bash
  cd my-project
```

init server

```bash
  docker compose up -d --build
```

Access the application in the browser

```bash
  http://localhost:8091
```

> PS: The application will be running on port 8091, but you want run project with IDE:
> - step 1 - stop containers with `docker compose down`
> - step 2 - open IDE and run project
> - step 3 - access the application in the browser `http://localhost:8080`

> PS2: The database will be running on port 5555

> PS3: The rabbitmq interface will be running on port 15672

> PS4: The mailhog interface will be running on port 8025

## Running tests

To run the tests, run the following command

```bash
  mvn test
```

## Author

| Lucas Fernandes                                                                                                  |
|------------------------------------------------------------------------------------------------------------------|
| <img src="https://avatars.githubusercontent.com/u/76585138?v=4" style="width: 130px; border-radios: 50%"></img>  |





