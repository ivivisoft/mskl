package com.mskl.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BaseController {

    @RequestMapping("/hello")
    public  Person getHello(){
        Person p = new Person("描述","点点滴滴","andy");
        return p;
    }

    @RequestMapping("/get")
    public String getSomeInfo(@RequestBody Person1 pe){
        System.out.print(pe.toString());
        return "true";
    }

   private class Person{
       private String name;
       private String username;
       private String description;
       private List<Classroom> classrooms;

       public Person(String description, String name, String username) {
           this.description = description;
           this.name = name;
           this.username = username;
           classrooms = new ArrayList<Classroom>();
           classrooms.add(new Classroom("班级1"));
           classrooms.add(new Classroom("班级2"));
           classrooms.add(new Classroom("班级3"));
       }

       public List<Classroom> getClassrooms() {
           return classrooms;
       }

       public void setClassrooms(List<Classroom> classrooms) {
           this.classrooms = classrooms;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getUsername() {
           return username;
       }

       public void setUsername(String username) {
           this.username = username;
       }
   }

    private class Classroom{
        private String name;

        public Classroom(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
