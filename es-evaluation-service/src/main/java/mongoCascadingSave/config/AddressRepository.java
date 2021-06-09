package mongoCascadingSave.config;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wechange.easyschool.esmodel.entity.Address;


public interface AddressRepository extends MongoRepository<Address, String> {

}