package com.objectapi.objectapiencrypt.service;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.objectapi.objectapiencrypt.dto.PersonDto;
import com.objectapi.objectapiencrypt.entity.Person;
import com.objectapi.objectapiencrypt.repository.PersonRepository;
import com.objectapi.objectapiencrypt.util.AESUtil;

@Service
public class PersonService {

    private SecretKey key;
    private byte[] iv;

   public PersonService() throws NoSuchAlgorithmException{
    this.key = AESUtil.generateAESKey();
    this.iv = AESUtil.generateIV();
   }

    @Autowired
    private PersonRepository personRepository;
    public void savePersonData(PersonDto person) {
        Person p = new Person();
        p.setName(person.getName());
        p.setAge(person.getAge());

        try {
            // SecretKey key = AESUtil.generateAESKey();
            // byte[] iv = AESUtil.generateIV();
            p.setCardDetails(
                    Base64.getEncoder().encodeToString(AESUtil.encryptAES_CBC(person.getCardDetails(), key, iv)));
            personRepository.save(p);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public Person findById(Long id) throws Exception {
        // SecretKey key = AESUtil.generateAESKey();
        // byte[] iv = AESUtil.generateIV();
        Person person = personRepository.findById(id).orElse(null);
        person.setCardDetails(AESUtil.decryptAES_CBC(Base64.getDecoder().decode(person.getCardDetails()), key, iv));
        return person;
    }
 
}
