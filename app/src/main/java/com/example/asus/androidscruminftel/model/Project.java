package com.example.asus.androidscruminftel.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Asus on 06/04/2016.
 */
public class Project {
    String id;
    String name;
    String description;
    int idProject;
    int idAdmin;
    Date dateStart;
    String[] chat;
    ArrayList<String> estados;
    ArrayList<Task> tasks;

    public Project(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getIdProject (){
        return idProject;
    }

    public void setIdProject(int idProject){
        this.idProject = idProject;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String[] getChat() {
        return chat;
    }

    public void setChat(String[] chat) {
        this.chat = chat;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (idProject != project.idProject) return false;
        if (idAdmin != project.idAdmin) return false;
        if (!id.equals(project.id)) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null)
            return false;
        if (dateStart != null ? !dateStart.equals(project.dateStart) : project.dateStart != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(chat, project.chat)) return false;
        if (estados != null ? !estados.equals(project.estados) : project.estados != null)
            return false;
        return !(tasks != null ? !tasks.equals(project.tasks) : project.tasks != null);

    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", idProject=" + idProject +
                ", idAdmin=" + idAdmin +
                ", dateStart=" + dateStart +
                ", chat=" + Arrays.toString(chat) +
                ", estados=" + estados +
                ", tasks=" + tasks +
                '}';
    }
}
