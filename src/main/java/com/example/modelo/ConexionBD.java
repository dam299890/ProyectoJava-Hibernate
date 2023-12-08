package com.example.modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionBD {

    static private SessionFactory miFactory =  new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Informacion.class).buildSessionFactory();


    public static Session getConexion(){

        Session mySesion = miFactory.openSession();

        return mySesion;
    }



}
