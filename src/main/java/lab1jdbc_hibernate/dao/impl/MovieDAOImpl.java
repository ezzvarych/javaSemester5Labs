package lab1jdbc_hibernate.dao.impl;

import lab1jdbc_hibernate.DBConnection;
import lab1jdbc_hibernate.HibernateUtil;
import lab1jdbc_hibernate.dao.MovieDAO;
import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;
import org.hibernate.Session;

import javax.transaction.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {


    private Movie fulfillObjectFromResultSet(ResultSet rs) {
        Movie movie = new Movie();
        try {
            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setLength(rs.getInt("length"));
            movie.setYear(rs.getInt("year"));
            movie.setShortDescr(rs.getString("short_descr"));
            Director director = new Director();
            director.setId(rs.getInt("director_id"));
            director.setName(rs.getString("director_name"));
            movie.setDirector(director);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Movies", Movie.class).list();
        }
    }

    @Override
    public Movie getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Movie.class, id);
        }
    }

    @Override
    public Movie insert(Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(movie);
            session.getTransaction().commit();
            return movie;
        }
    }

    @Override
    public Movie update(Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(movie);
            session.getTransaction().commit();
            return movie;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Movie movie = session.load(Movie.class, id);
            if (movie != null) {
                session.beginTransaction();
                session.delete(movie);
                session.flush();
                session.getTransaction().commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public Movie getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Movie) (session.createQuery("from Movies where title = :ttl")
                    .setParameter("ttl", title).uniqueResult());
        }
    }

}
