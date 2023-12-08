package com.example.servicio;

import com.example.modelo.ConexionBD;
import com.example.modelo.Informacion;
import org.hibernate.Query;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InformacionService {

    public List<Informacion> getInformacion(String referencia) {

        Session mySesion = ConexionBD.getConexion();

        List<Informacion> results;

        try {
            Informacion info = new Informacion();

            mySesion.beginTransaction();

            String hql = "FROM Informacion WHERE referencia = :referencia";
            Query query = mySesion.createQuery(hql);
            query.setParameter("referencia", referencia);
            results = query.list();

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

        return results;
    }

    public List<Informacion> getInformacion(int id) {

        Session mySesion = ConexionBD.getConexion();

        List<Informacion> results;

        try {
            Informacion info = new Informacion();

            mySesion.beginTransaction();

            String hql = "FROM Informacion WHERE id = :id";
            Query query = mySesion.createQuery(hql);
            query.setParameter("id", id);
            results = query.list();

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

        return results;
    }

    public List<Object[]> getReferenciaYFechaInformacion() {

        Session mySesion = ConexionBD.getConexion();

        List<Object[]> results;
        List<String> idFechaEInformacion = new ArrayList<>();

        try {
            mySesion.beginTransaction();

            String hql = "SELECT id,referencia, fecha FROM Informacion";
            Query query = mySesion.createQuery(hql);
            results = query.list();

            for (Object[] result : results) {
                String referencia = (String) result[1];
                LocalDate fechaCreacion = (LocalDate) result[2];
                String referenciaYFecha= referencia +" "+ fechaCreacion;
                idFechaEInformacion.add(referenciaYFecha);

            }

            mySesion.getTransaction().commit();

        } finally {
            mySesion.close();
        }

        return results;
    }
}
