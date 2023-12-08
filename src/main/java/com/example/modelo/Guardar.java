package com.example.modelo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Guardar {
    public static void main(String[] args) {






       /* SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Informacion.class).buildSessionFactory();

        Session mySesion = miFactory.openSession();
        List<Referencia> referencias = new ArrayList<>();

        try {


            mySesion.beginTransaction();

            String hql = "FROM Referencia";
            Query query = mySesion.createQuery(hql);
            List<?> results = query.list();

            for (Object result : results) {
                if (result.getClass().equals(Referencia.class)) {
                    Referencia referencia = (Referencia) result;
                    referencias.add(referencia);
                }
            }



            // Aquí puedes hacer lo que necesites con la lista de referencias, como almacenarla en un array o realizar alguna otra operación.

            for (Referencia referencia : referencias) {
                System.out.println(referencia.getReferencia());
            }
            mySesion.getTransaction().commit();

            System.out.println("registro insertado correctamente");

            mySesion.close();
        } finally {
            miFactory.close();
        }
*/

        /*Session mySesion = ConexionBD.getConexion();

        List<Object[]> results;
        List<String> idFechaInformacion = new ArrayList<>();
        try {
            mySesion.beginTransaction();

            String hql = "SELECT referencia, fecha FROM Informacion";
            Query query = mySesion.createQuery(hql);
            results = query.list();

            for (Object[] result : results) {
                String referencia = (String) result[0];
                LocalDate fechaCreacion = (LocalDate) result[1];
                String IdFecha= referencia +" "+ fechaCreacion;
                System.out.println("Referencia: " + referencia + ", Fecha de Creación: " + fechaCreacion);
                idFechaInformacion.add(IdFecha);

            }

            System.out.println("segundo bucle");
            for (String idyFecha:
                    idFechaInformacion) {

                System.out.println(idyFecha);

            }
            mySesion.getTransaction().commit();

            System.out.println("Visualizar lista de referencias 2");

        } finally {
            mySesion.close();
        }*/

        Session mySesion = ConexionBD.getConexion();

        List<Informacion> results;

        try {
            Informacion info = new Informacion();

            mySesion.beginTransaction();

            String hql = "FROM Informacion WHERE id = :id";
            Query query = mySesion.createQuery(hql);
            query.setParameter("id", 10);
            results = query.list();

            System.out.println("Informacion");

            for (Object result : results
            ) {
                System.out.println(result);
            }


            mySesion.getTransaction().commit();

            System.out.println("visualizar lista referencias 2");

            mySesion.close();
        } finally {
            mySesion.close();
        }




    }
}
