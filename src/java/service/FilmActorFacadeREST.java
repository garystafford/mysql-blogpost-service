/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.entities.FilmActor;
import com.mysql.entities.FilmActorPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author gstaffor
 */
@Stateless
@Path("com.mysql.entities.filmactor")
public class FilmActorFacadeREST extends AbstractFacade<FilmActor> {
    @PersistenceContext(unitName = "MySQLDemoServicePU")
    private EntityManager em;

    private FilmActorPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;actorId=actorIdValue;filmId=filmIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.mysql.entities.FilmActorPK key = new com.mysql.entities.FilmActorPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> actorId = map.get("actorId");
        if (actorId != null && !actorId.isEmpty()) {
            key.setActorId(new java.lang.Short(actorId.get(0)));
        }
        java.util.List<String> filmId = map.get("filmId");
        if (filmId != null && !filmId.isEmpty()) {
            key.setFilmId(new java.lang.Short(filmId.get(0)));
        }
        return key;
    }

    public FilmActorFacadeREST() {
        super(FilmActor.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(FilmActor entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(FilmActor entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.mysql.entities.FilmActorPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public FilmActor find(@PathParam("id") PathSegment id) {
        com.mysql.entities.FilmActorPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<FilmActor> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<FilmActor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
