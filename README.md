![hire us](https://www.softelevation.com/wp-content/uploads/2021/05/Untitled-design-18-2.png)


<h1 align="center">🎯 Sobre o projeto</h1>

O projeto é capaz de registrar valores referentes à remunerações dos funcionários, com os devidos descontos na folha de pagamento. Com validade legal para comprovação de renda e atividade remunerada. O documento tem função contábil e fiscal, além de sua importância operacional.

<h1 align="center">📦 Desenvolvimento</h1>

Realizado com framework SpringBoot em Java 11, gestor de dependência com Maven - uso de DevTools para agilidade durante a produção de desenvolvimento. Hibernate e JPA para persistir os dados no banco de dados MySQL, Lombok para perfomance e diminuir boiler plate code. Auxílio do ModelMapper para criação do DTO. Possibilidade de negociação de conteúdo (XML/JSON) com fasterxml e com interface amigável na documentação do projeto e dos devidos endpoints com Swagger.

<h1 align="center"> 📋 Diagrama UML</h1>


<div align="center">
<img src="https://user-images.githubusercontent.com/53881848/167413273-7405175f-b038-4ad8-893e-186b4cda62de.jpg" width="650px" />
</div>

<h1 align="center"> 💻 Requisitos de inicialização do projeto</h1>

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento.

Consulte *Implantação* para saber como implantar o projeto.

### 📋 Pré-requisitos

De que coisas você precisa para instalar o software e como instalá-lo?

- [Maven](http://maven.apache.org)
- [JDK](https://www.oracle.com/java/technologies/downloads/)

<h3>Unix</h3>

` 
sudo apt install openjdk-11-jre-headless
`

 <h1 align="center">📍 Rodando a aplicação</h1>

```
bash

 Clone este repositório
$ git clone https://github.com/laersonjr/project-java-with-spring-cerc

 Acesse a pasta do projeto no terminal/cmd
$ cd README-nomeprojeto

 Vá para a pasta nomeprojeto
$ cd project-nomeprojeto

 Instale as dependências
$ mvn install
$ mvn clean package

 Execute a aplicação em modo de desenvolvimento
$ javac -nomedoprojeto.jar

 O servidor iniciará na porta:8080 - acesse http://localhost:8080 
```
<h1 align="center">✔️ Vericando projeto ON</h1>

A tela da sua aplicação deve estar:

<div align="center">
<img src="https://user-images.githubusercontent.com/53881848/167270301-3d8ef2f6-ef70-43d8-b04a-2fc29203280b.jpeg" width="680px" />
</div>

<h1 align="center">🧩 Consumindo API </h1>




<h3> CURL - cargos</h3>

```
curl -X GET "http://localhost:8080/cargos" -H "accept: /"


```
<h3> CURL - funcionários</h3>

```
curl -X GET "http://localhost:8080/funcionarios" -H "accept: /"


```
<h3> CURL - pagamento</h3>

```

curl -X GET "http://localhost:8080/pagamento" -H "accept: /"

```

<h1 align="center">💼 Negociação de Conteúdo</h1>
 
 A aplicação pode ser consumida em XML e em JSON.
 
 <h3> Modelo XML </3>
 <br>
 <section align="center">
  
 <img src="https://user-images.githubusercontent.com/53881848/167270646-955694a6-c662-450b-8163-40c852906a84.jpeg" width="500px" />
  <br>
 <img src="https://user-images.githubusercontent.com/53881848/167270654-2fa47549-e337-45e1-a73a-a5a1dfbad880.jpeg" width="500px" />
  <br>
 <img src="https://user-images.githubusercontent.com/53881848/167270663-12774f56-41c4-4376-b070-fb98516bb27b.jpeg" width="500px" />
</section>
 
 <h3> Modelo schema json </h3>

<div align="center">
<img src="https://user-images.githubusercontent.com/53881848/167270672-6950423a-c55d-476a-97b0-ca354b057f43.jpeg" width="700px" />
</div>

<h1 align="center">📌 Documentação </h1>

A documentação foi realizada com Swagger com interface amigável para documentação e testes dos endpoints.

<div align="center">
<img src="https://user-images.githubusercontent.com/53881848/167272171-9eeca6c2-bf64-4121-837a-612ddb9db7ec.jpeg" width="700px" />
</div>


<h1 align="center">🔨 Funcionalidades do projeto </h1>

- `Funcionalidade 1` `salvarCargo`: Cadastra um cargo com suas devidas informações no sistema.

- `Funcionalidade 2` `salvarFuncionario`: Cadastra um funcionário e seu respectivo cargo no sistema.
 
- `Funcionalidade 3` `gerarFolha`: Cadastra a folha de pagamento relacionado a um funcionário no sistema.

- `Funcionalidade 4` `buscarPagamento`: Lista um funcionário por nome.

- `Funcionalidade 5` `folhaPagamento`: Lista um funcionário por matrícula.

- `Funcionalidade 6` `atualizarFuncionario`: Atualiza os dados refentes: cargo, funcionário e folha de pagamento.

- `Funcionalidade 7` `desligarFuncionario`: Desativa um funcionário do sistema de pagamento.


<h1 align="center">🛠️ Construído com as tecnologias  </h1>


* [Springboot](https://spring.io/projects/spring-boot)
* [Maven](http://maven.apache.org) 
* [Lombok](https://projectlombok.org/download) 
* [ModelMapper](http://modelmapper.org/) 
* [FasterXML](https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/latest/index.html)
* [Swagger](https://swagger.io/)
* [Hibernate](https://hibernate.org/) 
* [MySQL](https://dev.mysql.com/downloads/workbench/)
* [DevTools](https://www.quickdev.org/?gclid=Cj0KCQjwsdiTBhD5ARIsAIpW8CLGWx99TFjAY-Xgu5fSe-Kno98jVPCa0ZxdyOvnbCZT3bDrFZgbxF0aAs3sEALw_wcB)

<h1 align="center">📚 Features em desenvolvimento</h1>

- [ ] Docker
- [ ] Flyway
- [ ] Deploy Google Cloud (GCP)
- [ ] TDD JUnit

<h1 align="center">💪 Como contribuir com o projeto </h1>

1. Faça um *fork* do projeto.
3. Crie uma nova branch com as suas alterações: `git checkout -b nomedoarquivo`
4. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "atualização"`
5. Envie as suas alterações: `git push origin nomedoarquivo`

<h1 align="center">🎁 Considerações Finais</h1>

* Agradecimento:
* A squad pelo trabalho incrível, conhecimentos adquiridos durante a jornada da Academia.
* A Gama Academy, instrutora [Marianne](https://github.com/mariannesalomao?tab=repositories) e supervisora Débora.
* A CERC pelo ambiente gerado e maestria do onboarding.  


 <h1 align="center">✒️  Desenvolvedores </h1>

| [<img src="https://avatars.githubusercontent.com/u/73408388?v=4" width=115><br><sub>Bruno Brito</sub>](hhttps://github.com/brunopbrito31) |  [<img src="https://avatars.githubusercontent.com/u/82125551?v=4" width=115><br><sub>Gabriel Moreira</sub>](https://github.com/Gabriel-kopke-jr) |  [<img src="https://avatars.githubusercontent.com/u/58311661?v=4" width=115><br><sub>Laerson</sub>](https://github.com/laersonjr) |  [<img src="https://avatars.githubusercontent.com/u/53881848?v=4" width=115><br><sub>Leonardo</sub>](https://github.com/LeonardoMeloTI) |  [<img src="https://avatars.githubusercontent.com/u/97760233?v=4" width=115><br><sub>Marcklen Guimarães</sub>](https://github.com/Marcklen) |  [<img src="https://avatars.githubusercontent.com/u/59845047?v=4" width=115><br><sub>Valdeir Camargo</sub>](https://github.com/Camargovf)
| :---: | :---: | :---: | :---: | :---: | :---: | 

