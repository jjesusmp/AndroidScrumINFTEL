package model;


import java.sql.Date;
import java.util.Arrays;

/**
 * Created by Asus on 06/04/2016.
 */
public class Project {
    String name;
    String description;
    int idProject;
    int idAdmin;
    Date dateStart;
    String[] chat;
    String[] estados;



    public Project(){

    }

    public Project(String name, String description, int idProject, int idAdmin, Date dateStart,String[] chat, String[] estados){
        this.name = name;
        this.description = description;
        this.idProject = idProject;
        this.idAdmin = idAdmin;
        this.dateStart = dateStart;
        this.estados=estados;
        this.chat=chat;
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

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", idProject=" + idProject +
                ", idAdmin=" + idAdmin +
                ", dateStart=" + dateStart +
                ", chat=" + Arrays.toString(chat) +
                ", estados=" + Arrays.toString(estados) +
                '}';
    }
}
