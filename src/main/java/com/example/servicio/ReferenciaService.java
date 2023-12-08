package com.example.servicio;

import com.example.modelo.ConexionBD;
import com.example.modelo.Referencia;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ReferenciaService {

    public List<Referencia> getRerefencias() {

        Session mySesion = ConexionBD.getConexion();

        List<Referencia> referenciasBD = new ArrayList<>();

        try {

            mySesion.beginTransaction();

            String hql = "FROM Referencia";
            Query query = mySesion.createQuery(hql);
            List<?> results = query.list();

            for (Object result : results) {
                if (result.getClass().equals(Referencia.class)) {
                    Referencia referencia = (Referencia) result;
                    referenciasBD.add(referencia);
                }
            }

            // Aquí puedes hacer lo que necesites con la lista de referencias, como almacenarla en un array o realizar alguna otra operación.

            for (Referencia referencia : referenciasBD) {
                System.out.println(referencia.getReferencia());
            }


            mySesion.getTransaction().commit();

            System.out.println("referencias obtenidas");

            mySesion.close();
        } finally {
            mySesion.close();
        }

        return referenciasBD;
    }
}
