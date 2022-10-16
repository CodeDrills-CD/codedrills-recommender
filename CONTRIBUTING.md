# Contribution Guidelines

## Local development instructions

To run the project locally, you first need to fork the repository and clone the repository locally. The project uses
MySQL 5 as a database. You need to install Docker to run the project. Follow
the [link](https://docs.docker.com/get-docker/) to install Docker. After installing docker the following steps are
required.

### Run the containerised MySQL database

1. To start the database in docker run this command in the root path of the project.
    ```
    docker-compose up -d
    ```
   The details can also be found in the `docker-compose.yml` file. It also runs `adminer` a lightweight tool that we can
   use to access the data stored in the database using a GUI.
2. To stop the database running in docker run this command in the root path of the project.
   ```
   docker-compose rm -a -s -f -v
   ```
   `-a` flag marks all the services started in docker for removal.
   `-s` flag stops the services started in docker.
   `-f` flag force stops the services started in docker.
   `-v` flag removes all the associated volumes used by the services stopped in docker.

### Configuring the editor

You can use any editor of your choice which supports development in Java but recommended IDE is Intellij IDEA. To run
the project in dev mode you need to add `-Dspring.profiles.active=dev` to the VM options. This is recommended for local
development.

After performing all the steps run the project locally using Intellij using the run button. The project runs on
port `8080` by default, although it can be changed if required by editing the `application.properties` file present in
the `resources` folder.