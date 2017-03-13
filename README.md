SpringProject by KimTaeGuk
===================

###  Available Skills
Languages             | Skills           |
----------------------|------------------|
Web                   | Jsp(Spring), php |
Javascript            | Jquery, Ajax     | 
CSS                   | CSS3, Bootstrap  |
HTML                  | HTML5            |

#### Etc
Etc                   | Skills               |
----------------------|----------------------|
OS                    | Linux, Unix, Windows |
Editors               | Eclipse(STS)         | 

### web-xml
filter-name의 이름은 반드시 **springSecurityFilterChain**이어야 합니다.

>>  **springSecurityFilterChain**
>DelegatingFilterProxy 클래스는 setTargetBeanName(String)이라는 메서드를 갖고 있는데 이 메서드는 실제 요청을 처리할 필터를 주입받습니다. 이 클래스는 기본값으로 filter-name의 값과 동일한 빈이 스프링 컨텍스트에 존재하는지를 검색하게 됩니다. 그리고 springSecurityFilterChain은 스프링 시큐리티의 inner bean이기 때문에 자동으로 생성이 됩니다.

    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
	  	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
	  	<filter-name>springSecurityFilterChain</filter-name>
	  	<url-pattern>/*</url-pattern>
	</filter-mapping>


###security-context.xml

비밀번호를 bcrypt방식으로 암호화시켜줍니다.

    <security:intercept-url pattern="/welcome" access="hasRole('ROLE_ADMIN')"/>	


maven의 버전과 xml 스키마의 버전이 일치해야 합니다.

    <security:authentication-manager>
        <security:authentication-provider>
 	    
	    <!--암호화 참조입니다.-->
	    <security:password-encoder ref="encoder" />
 				
 	    <!-- 유저 확인과 그 유저가 가지고 있는 권한을 확인합니다. --> 
 	    <security:jdbc-user-service data-source-ref="dataSource"
	        users-by-username-query="select userId as username, userPassword as password, enabled from user where userId=?"
	        authorities-by-username-query="select userId as username, authority from user_auth where userId=?"
	    />

        </security:authentication-provider>
    </security:authentication-manager>



### 추가된 기능
- 암호화된 비밀번호로 인해 비밀번호를 찾을 시 새로운 비밀번호를 이메일로 전송했습니다.

**spring-mail.xml**
```
    <!-- 
        maven을 통하여 javax.mail(v.1.4.7)를 받습니다.
        username  자신의 이메일을 입력합니다.
        password  자신의 이메일 비밀번호를 입력합니다.
    -->
    <beans:bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
        
	<beans:property name="host" value="smtp.gmail.com" />
        <beans:property name="port" value="587"/>
        <beans:property name="defaultEncoding" value="utf-8"/>
        <beans:property name="username" value="myEmailAddress" />
        <beans:property name="password" value="myEmailPassword" />
		
        <beans:property name="javaMailProperties">
	
            <beans:props>
                <beans:prop key="mail.smtp.starttls.enable">true</beans:prop>
                <beans:prop key="mail.smtp.auth">true</beans:prop>
            </beans:props>
	
        </beans:property>
    </beans:bean>
```

### 추가되는 기능
- 소셜로그인(공부 중입니다.)
- 각종 게시판 기능들

