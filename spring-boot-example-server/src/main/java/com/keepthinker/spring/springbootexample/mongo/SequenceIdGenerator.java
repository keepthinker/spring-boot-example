package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceIdGenerator implements IdGenerator{

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * generate increment id for specified object.
     * Example of id: 1, 2, 3 ...
     * @param name name of object
     * @return auto-increment id
     */
    @Override
    public int generateId(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(name));
        Update update = new Update();
        update.inc("seq");
        FindAndModifyOptions options = FindAndModifyOptions.options();
        options.upsert(true);
        options.returnNew(true);
        /*
         refrence:
         https://www.mongodb.com/docs/manual/reference/method/db.collection.findAndModify/
         https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
         */
        Sequence seq = mongoTemplate.findAndModify(query, update, options, Sequence.class);
        if (seq == null) {
            throw new RuntimeException("failed to findAndModify");
        }
        return seq.getSeq();
    }

}
