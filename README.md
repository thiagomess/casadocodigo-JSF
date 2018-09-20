# casadocodigo-JSF
Utilizando JSF, CDI, JPA para fazer o site da casa do codigo

Documentação: https://docs.oracle.com/javaee/7/javaserver-faces-2-2/vdldocs-facelets/index.html

*************************************************************************************
# ADCIONADO VÁRIAS MELHORIAS QUE NAO HAVIA NO CURSO

* Criado uma tela de login utilizando bootstrap na url 
	http://localhost:8080/casadocodigo/usuarios/login.xhtml

* Criado tela para o cadastro de novos usuarios e de lista: 
	http://localhost:8080/casadocodigo/livros/usuario.xhtml
	http://localhost:8080/casadocodigo/livros/listaUsuarios.xhtml

* Alterado a forma que era inserido a ROLE, foi criado um ENUM para exibir na tela.
* Alterado os acessos as paginas, usuario com role ADMIN tem acesso a algumas paginas e usuario com Role USER tem acesso a outras.
* Adiciona botão para remover livro da lista e adicionado metodo para apagar o arquivo da pasta do SO
* Adicionado mensagens de erro caso tente inserir uma capa de livro que já exista
* Adicionado Modal em bootstrap  para a o pop-up da promoção
* Adicionado template em todas as paginas web
* Adicionado regra de negocio, apos o admin lançar a promoção é adicionado um tempo que ela será válida e uma query ira alterar no banco e depois do tempo terminado ele volta o preço que estava anteriormente com serviço assincrono.



Commits das melhorias: 
https://github.com/thiagomess/casadocodigo-JSF/commit/d786e2d56c8ad5269b7381722492ef54307fcc10

https://github.com/thiagomess/casadocodigo-JSF/commit/4eb52d700909f25de89ca75fd954962fa7a365fc

https://github.com/thiagomess/casadocodigo-JSF/commit/7694acbecd74a0897b07a3516e10a03dcb5561a8


*************************************************************************************

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

6- Para o envio de email funcionar, é necessário configurar o Standalone do wildfly
		
		 <mail-session name="gmail" jndi-name="java:jboss/mail/gmail">
        <smtp-server outbound-socket-binding-ref="mail-smtp-gmail"
            ssl="true" username="SEU-NOME-DE-USUARIO" password="SUA-SENHA" />
   		 </mail-session>
   		 
   E adicione o mail-smtp-gmail:
   
	   	<outbound-socket-binding name="mail-smtp-gmail">
	        <remote-destination host="smtp.gmail.com" port="465" />
	    </outbound-socket-binding>
7- Adicionar no	 <subsystem xmlns="urn:jboss:domain:security:2.0"> para poder validar o usuario na area administrativa http://localhost:8080/casadocodigo/usuarios/login.xhtml
	    
	    
	         <subsystem xmlns="urn:jboss:domain:security:2.0">
            		<security-domains>
			<!-- Adicionar esse bloco, inicio --> 
			
				  <security-domain name="database-login" cache-type="default">
				    <authentication>
					<login-module code="Database" flag="required">
					    <module-option name="dsJndiName" value="java:jboss/datasources/casadocodigoDS"/>
					    <module-option name="principalsQuery" value="select senha from SystemUser where email = ?"/>
					    <module-option name="rolesQuery" value="select sysRole.roles_name, 'Roles' from SystemUser_SystemRole sysRole inner join SystemUser su on sysRole.SystemUser_id = su.id where su.email = ?"/>
					    <module-option name="hashAlgorithm" value="SHA-256"/>
					    <module-option name="hashEncoding" value="base64"/>
					</login-module>
				    </authentication>
				</security-domain>
				
				<!-- fim --> 
			</security-domains>
		</subsystem>


# Certificado Alura

Curso Java EE parte 1: Crie sua loja online com CDI, JSF, JPA

https://cursos.alura.com.br/certificate/c9b76532-4f86-4c86-80af-fe49587f464c

Java EE parte 2: Sua loja online com HTML, REST e Cache

https://cursos.alura.com.br/certificate/af3209e6-1e52-40ec-b83c-c4d1b73290e8

Curso Java EE parte 3: Finalizando sua loja com REST, JMS, JAAS e WebSockets

https://cursos.alura.com.br/certificate/e7af2402-986c-4d6f-ac6f-3475ec5d3d29

