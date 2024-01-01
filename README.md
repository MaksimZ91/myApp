# Системы сборки Maven и Gradle для разработки Java приложений.
Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.  
Задание:  
1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
3. Создайте класс Person с полями firstName, lastName и age.
4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.

![1](https://github.com/MaksimZ91/myApp/assets/72209139/b92f38c8-20a4-4d76-b3e0-50851cd24c47)
![2](https://github.com/MaksimZ91/myApp/assets/72209139/12a9db2b-4166-46f0-9469-5764963ba715)

## Pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>my.project</groupId>
    <artifactId>myApp</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>myApp</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.12.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.6</version>
        </dependency>
    </dependencies>
</project>
```
## Class Person
```java
package my.project;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private  int age;

    public Person(){}
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new EqualsBuilder().append(age, person.age).append(firstName, person.firstName).append(lastName, person.lastName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(firstName).append(lastName).append(age).toHashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
## Class Serialize
```java
package my.project;
import com.google.gson.Gson;

public class Serialize {
    private Gson gson;

    public Serialize() {
        this.gson = new Gson();
    }

    public   String serialize(Object object){
        return gson.toJson(object);
    }

    public Object deSerialize(String json, Class clazz){
        return gson.fromJson(json, clazz);    }
}
```
## Class App
```java
package my.project;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Serialize serialize = new Serialize();
        Person person = new Person("Mike", "Kotov", 29);
        String json = serialize.serialize(person);
        System.out.println(json);
        System.out.println(serialize.deSerialize(json, Person.class));
    }
}
```
