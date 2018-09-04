# casadocodigo-JSF
Utilizando JSF, CDI, JPA para fazer o site da casa do codigo

Documentação: https://docs.oracle.com/javaee/7/javaserver-faces-2-2/vdldocs-facelets/index.html



 1 -Sistema utilizando Widfly 13.0

 2- Banco de dados MySql 5.3

 3- É necessário configurar o standalone-full.xml do servidor com o seguinte codigo:


      <datasource jndi-name="java:jboss/datasources/casadocodigoDS"
        pool-name="casadocodigoDS" enabled="true" use-java-context="true">
        <connection-url>jdbc:mysql://localhost:3306/casadocodigo</connection-url>
        <connection-property name="DatabaseName">
          casadocodigo
        </connection-property>
        <driver>com.mysql</driver>
        <pool>
          <min-pool-size>10</min-pool-size>
          <max-pool-size>100</max-pool-size>
          <prefill>true</prefill>
        </pool>
        <security>
          <user-name>root</user-name>
          <password>root</password>
        </security>
      </datasource>
       
  .
  
        <driver name="com.mysql" module="com.mysql">
          <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlDataSource</xa-datasource-class>
        </driver>


4- Internamente o Widfly organiza seus módulos em pacotes, por isso devemos navegar para a pasta modules/com. Dentro da pasta com criaremos uma nova pasta mysql e dentro dela uma pasta main. Dentro da pasta main colocaremos o arquivo modules.XML e o JAR (hierarquia final das pastas: jboss/modules/com/mysql/main)

O arquivo modules.xml deve conter o seguinte texto:
      <?xml version="1.0" encoding="UTF-8"?>

      <module xmlns="urn:jboss:module:1.3" name="com.mysql">
        <resources>
          <resource-root path="mysql-connector-java-5.1.35.jar"/>
        </resources>
        <dependencies>
          <module name="javax.api"/>
        </dependencies>
      </module>

o arquivo jar é o mysql-connector-java-5.1.35.jar

5- Deve ser criado o database "casadocodigo"


# Certificado Alura

Curso Java EE parte 1: Crie sua loja online com CDI, JSF, JPA

https://cursos.alura.com.br/certificate/c9b76532-4f86-4c86-80af-fe49587f464c

