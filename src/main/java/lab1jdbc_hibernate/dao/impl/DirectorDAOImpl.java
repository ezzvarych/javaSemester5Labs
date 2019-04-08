package lab1jdbc_hibernate.dao.impl;

import lab1jdbc_hibernate.DBConnection;
import lab1jdbc_hibernate.HibernateUtil;
import lab1jdbc_hibernate.dao.DirectorDAO;
import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAOImpl implements DirectorDAO {

    private Director fulfillDirectorObject(ResultSet rs) {
        Director director = null;
        try {
            director = new Director(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return director;
    }

    @Override
    public List<Director> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Directors", Director.class).list();
        }
    }

    @Override
    public Director getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Director.class, id);
        }
    }

    @Override
    public Director getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Director)(session.createQuery("from Directors where name = :nm")
                    .setParameter("nm", name).uniqueResult());
        }
    }

    @Override
    public Director insert(final Director director) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(director);
            session.getTransaction().commit();
            return director;
        }
    }

    @Override
    public Director update(final Director director) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(director);
            session.getTransaction().commit();
            return director;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Director director = session.load(Director.class, id);
            if (director != null) {
                session.delete(director);
                return true;
            }
            return false;
        }
    }

    @Override
    public List<Movie> getDirectorMovies(String directorName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (session.createQuery
                    ("select m " +
                    " from Movies m join Directors d  where d.name= :dir")
                    .setParameter("dir", directorName).list());
        }
        /*List<Movie> movieList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =  connection.prepareStatement("select m.id," +
                     " m.title, m.length, m.year, m.short_descr, d.id as director_id, d.name as director_name" +
                     " from Movies m inner join Directors d on m.director=d.id where d.name=?")) {
            preparedStatement.setString(1, directorName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movie movie = fulfillObjectFromResultSet(rs);
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;*/
    }
}
