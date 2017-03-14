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
Editors               | Eclipse, Sublime ... | 
Framework             | Spring               |


### 추가된 기능(v3.13)
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

