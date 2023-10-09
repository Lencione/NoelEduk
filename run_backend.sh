#Buildar a aplicacao java
./mvnw clean package -DskipTests

#Buildar a imagem do docker e executar o container
docker-compose up --build -d