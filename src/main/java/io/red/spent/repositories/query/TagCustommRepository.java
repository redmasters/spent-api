package io.red.spent.repositories.query;

import io.red.spent.models.dao.TagDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class TagCustommRepository {
    private final EntityManager manager;

    public TagCustommRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<TagDAO> findAll(UUID id) {
        StringBuilder sql = new StringBuilder();
        Query query;
        sql
                .append("select st.tag_id,  st.tag_description ")
                .append(" from sa_expense ex ")
                .append(" inner join sa_expense_tag s on ex.expense_id = s.fksa_expense_tagexpense_id ")
                .append(" inner join sa_tag st on st.tag_id = s.fksa_expense_tagtag_id ")
                .append(" where ex.expense_id = ?;" );
        query = this.manager.createNativeQuery(sql.toString(), TagDAO.class)
                .setParameter(1, id);
        return (List<TagDAO>) query.getResultList();
    }

    public List<TagDAO> allTags(){
        StringBuilder sql = new StringBuilder();
        Query query;
        sql
                .append(" select tag.tag_id, tag.tag_description")
                .append(" from sa_tag tag;");
        query = this.manager.createNativeQuery(sql.toString(), TagDAO.class);
        return (List<TagDAO>) query.getResultList();
    }
}
