# cooperativismo
Tecnologias ultilizadas no projeto:
- [JDK 11: Necessário para executar o projeto Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk11-downloads.html)
- [Maven 3.6: Necessário para realizar o build do projeto Java](http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)
- [Banco h2 em memoria ](https://www.h2database.com/html/main.html)
- [SpringBoot 2.3.2](https://spring.io/projects/spring-boot)
## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/socrammol/cooperativismo.git
```
### Iniciando
### Antes de fazer o build e necessario indicar onde o arquivo do banco de dados sera criado
### No arquivo application.properties , indicar o caminho na linha a seguir
````shell
spring.datasource.url=jdbc:h2:file:/home/marcos/dbh2teset
````
### Caso queiro mudar usuario e sneha tambem pode ser feito atraves da configuração a seguir
````shell
spring.datasource.username=sa
spring.datasource.password=
````
### Construção
#### Back
Para construir o projeto Back com o Maven, executar os comando abaixo:
Acesse a raiz do projeto
```shell
mvn clean install
```
Apos isso rode um java - jar mais o arquivo gerado
ou
```shell
mvn spring-boot:run
```
ou pela sua ide de preferencia
#### Foi gerado um json do postam que se encontra na raiz do projeto
#### para acessar a documentação
##### Foi ultilizado o OpenApi 1.2.32 , foi feito teste de integração via openapi 
1. Verssão grafica do swagger [swagger](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config).
##### Observaçoes
1. Primeiro tem que se criar os usuarios
2. Depois adiciona as pautas
3. Entao podem se abrir a votação , e efetuar os votos 

