package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.repository.*;

import java.util.*;

@Service
public class GaleryService {
    @Autowired
    GaleryRepository galeryRepository;


    public List<Galery> findAllByReference(UUID reference){
    return galeryRepository.findAllByReference(reference);


    }

    public Boolean insert(UUID reference, Set<String> images,UUID id){

        images.forEach(img->{
            Galery galery= new Galery(reference,img);
            galery.setCriadoPor(id);
            galery.setAlteradoPor(id);
            galeryRepository.save(galery);
        });

        return true;
    }


}
