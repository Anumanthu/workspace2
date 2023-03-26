package src.pojo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Student {
    private String id;
    private Set<String> subjects;

    public Student(String id) {
        this.id = id;
        subjects = new HashSet<>(Arrays.asList("Maths", "Science"));
    }

    private Student() {
    }

    @Override
    public String toString() {
        return Arrays.asList(id, subjects.toString()).toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<String> sub) {
        this.subjects = sub;
    }
}

class Person {
    private String name;
    private int age;
    private Student student;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

        student = new Student(name.replaceAll("\\s+", "_") + "_" +
                String.valueOf(age));
    }

    private Person() {
    }

    @Override
    public String toString() {
        return Arrays.asList(name, String.valueOf(age), student.toString())
                .toString();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

// Demonstrate serialization/deserialization of Java objects using
// Jackson library
class JacksonLibraySerializationDeserializationGeeksForGeeks {
    public static void main(String[] args) {
        Person person = new Person("Jon Snow", 22);

        String jsonString = null;
        Person p = null;
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Serialize object � Convert `Person` object to JSON string
            jsonString = mapper.writeValueAsString(person);
            System.out.println("Converting Person object to JSON string:\n"
                    + jsonString + "\n");

            // Unserialize object � Convert JSON string to `Person` object
            p = mapper.readValue(jsonString, Person.class);
            System.out.println("Converting JSON string to Person object:\n"
                    + p.toString());
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
